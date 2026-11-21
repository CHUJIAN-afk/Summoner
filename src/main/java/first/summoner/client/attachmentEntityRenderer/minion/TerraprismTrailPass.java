package first.summoner.client.attachmentEntityRenderer.minion;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import first.lyra.client.render.EffectPipeline;
import first.lyra.client.render.HdrTarget;
import first.lyra.client.render.IrisCompat;
import first.lyra.client.render.RenderState;
import first.summoner.Summoner;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class TerraprismTrailPass {

    private static final List<Ribbon> ribbons = new ArrayList<>();
    private static HdrTarget target;

    public static void beginFrame() {
        if (!IrisCompat.isShadowPass()) {
            ribbons.clear();
        }
    }

    public static void submit(List<Vertex> vertices, List<Vertex> highlight) {
        ribbons.add(new Ribbon(vertices, false));
        ribbons.add(new Ribbon(highlight, true));
    }

    public static void render(Camera camera, Matrix4f view, Matrix4f projection) {
        if (!ribbons.isEmpty() && !IrisCompat.isShadowPass()) {
            RenderState state = new RenderState();
            Minecraft minecraft = Minecraft.getInstance();
            var main = minecraft.getMainRenderTarget();
            RenderSystem.disableScissor();
            RenderSystem.colorMask(true, true, true, true);
            RenderSystem.depthMask(true);
            target = HdrTarget.resize(target, main.width, main.height, true);
            if (main.isStencilEnabled() && !target.isStencilEnabled()) {
                target.enableStencil();
            }
            target.clear(Minecraft.ON_OSX);
            target.copyDepthFrom(main);
            target.bindWrite(true);
            RenderSystem.enableDepthTest();
            RenderSystem.depthFunc(GL11.GL_LEQUAL);
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendEquation(GL14.GL_FUNC_ADD);
            ShaderInstance shader = EffectPipeline.textureShader();
            shader.setSampler("Sampler0", minecraft.getTextureManager().getTexture(Summoner.rl("textures/particle/terraprism_trail.png")));
            shader.MODEL_VIEW_MATRIX.set(view);
            shader.PROJECTION_MATRIX.set(projection);
            Vec3 origin = camera.getPosition();
            for (boolean highlight : new boolean[]{false, true}) {
                RenderSystem.blendFuncSeparate(GL11.GL_ONE, highlight ? GL11.GL_ONE : GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
                shader.safeGetUniform("Brightness").set(highlight ? 3f : 1f);
                shader.safeGetUniform("Additive").set(highlight ? 1 : 0);
                List<List<Vertex>> quads = new ArrayList<>();
                for (Ribbon ribbon : ribbons) {
                    if (ribbon.highlight == highlight) {
                        for (int i = 0; i < ribbon.vertices.size(); i += 4) {
                            quads.add(ribbon.vertices.subList(i, i + 4));
                        }
                    }
                }
                quads.sort(Comparator.comparingDouble((List<Vertex> quad) -> quad.stream().mapToDouble(vertex -> vertex.pos.distanceToSqr(origin)).average().orElse(0)).reversed());
                BufferBuilder builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
                for (List<Vertex> quad : quads) {
                    for (Vertex vertex : quad) {
                        Vec3 pos = vertex.pos.subtract(origin);
                        builder.addVertex((float) pos.x, (float) pos.y, (float) pos.z).setUv(vertex.u, vertex.v).setColor(vertex.color);
                    }
                }
                MeshData mesh = builder.build();
                if (mesh != null) {
                    shader.apply();
                    BufferUploader.draw(mesh);
                    shader.clear();
                }
            }
            EffectPipeline.composite(EffectPipeline.bloom(target));
            state.restore();
            ribbons.clear();
        }
    }

    public static void close() {
        ribbons.clear();
        if (target != null) {
            target.destroyBuffers();
            target = null;
        }
    }

    public record Vertex(Vec3 pos, int color, float u, float v) {
    }

    private record Ribbon(List<Vertex> vertices, boolean highlight) {
    }
}
