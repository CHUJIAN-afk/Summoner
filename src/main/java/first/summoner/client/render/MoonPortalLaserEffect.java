package first.summoner.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import first.lyra.client.render.EffectPipeline;
import first.lyra.client.render.HdrTarget;
import first.lyra.client.render.IrisCompat;
import first.lyra.client.render.RenderState;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.util.ArrayList;
import java.util.List;

public final class MoonPortalLaserEffect {

    private static final List<Laser> LASERS = new ArrayList<>();
    private static ShaderInstance shader;
    private static ShaderInstance resolveShader;
    private static VertexBuffer volume;
    private static HdrTarget effect;

    private MoonPortalLaserEffect() {
    }

    public static void setShader(ShaderInstance value) {
        shader = value;
    }

    public static void setResolveShader(ShaderInstance value) {
        resolveShader = value;
    }

    public static void submit(Vec3 start, Vec3 direction, float length, float radius, float time, float alpha, float headTaperSpeed, float tailTaperSpeed) {
        if (length > 0 && radius > 0 && alpha > 0 && !IrisCompat.isShadowPass()) {
            LASERS.add(new Laser(start, direction.toVector3f(), length, radius, time, alpha, headTaperSpeed, tailTaperSpeed));
        }
    }

    public static void render(Camera camera, Matrix4f view, Matrix4f projection) {
        if (!LASERS.isEmpty()) {
            RenderState state = new RenderState();
            var main = Minecraft.getInstance().getMainRenderTarget();
            RenderSystem.disableScissor();
            RenderSystem.colorMask(true, true, true, true);
            effect = HdrTarget.resize(effect, main.width, main.height, false);
            effect.clear(Minecraft.ON_OSX);
            effect.bindWrite(true);
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendEquation(GL14.GL_FUNC_ADD);
            RenderSystem.blendFuncSeparate(GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE);
            if (volume == null) {
                float[][] positions = {{-1, -1, 0}, {1, -1, 0}, {1, 1, 0}, {-1, 1, 0},
                        {-1, -1, 1}, {1, -1, 1}, {1, 1, 1}, {-1, 1, 1}};
                int[][] faces = {{0, 1, 2, 3}, {4, 7, 6, 5}, {0, 3, 7, 4}, {1, 5, 6, 2}, {0, 4, 5, 1}, {3, 2, 6, 7}};
                BufferBuilder builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
                for (int[] face : faces) {
                    for (int corner : face) {
                        float[] position = positions[corner];
                        builder.addVertex(position[0], position[1], position[2]);
                    }
                }
                volume = new VertexBuffer(VertexBuffer.Usage.STATIC);
                volume.bind();
                volume.upload(builder.buildOrThrow());
            }
            shader.setSampler("SceneDepth", main.getDepthTextureId());
            shader.safeGetUniform("BeamProjection").set(projection);
            shader.safeGetUniform("EffectSize").set((float) main.width, (float) main.height);
            Matrix4f inverseProjection = new Matrix4f(projection).invert();
            volume.bind();
            for (Laser laser : LASERS) {
                Quaternionf rotation = new Quaternionf().rotationTo(0, 0, 1, laser.direction.x, laser.direction.y, laser.direction.z);
                Matrix4f beamToView = new Matrix4f(view).translate(laser.start.subtract(camera.getPosition()).toVector3f()).rotate(rotation);
                shader.safeGetUniform("BeamToView").set(beamToView);
                shader.safeGetUniform("ClipToBeam").set(new Matrix4f(beamToView).invert().mul(inverseProjection));
                shader.safeGetUniform("BeamLength").set(laser.length);
                shader.safeGetUniform("BeamRadius").set(laser.radius);
                shader.safeGetUniform("BeamTaperSpeed").set(laser.headTaperSpeed, laser.tailTaperSpeed);
                shader.safeGetUniform("Time").set(laser.time);
                shader.safeGetUniform("Opacity").set(laser.alpha);
                shader.apply();
                volume.draw();
                shader.clear();
            }
            VertexBuffer.unbind();
            var output = EffectPipeline.bloom(effect);
            main.bindWrite(true);
            RenderSystem.disableDepthTest();
            RenderSystem.disableCull();
            RenderSystem.blendFuncSeparate(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ZERO, GL11.GL_ONE);
            RenderSystem.colorMask(true, true, true, false);
            resolveShader.setSampler("BeamColor", effect.getColorTextureId());
            resolveShader.setSampler("GlowColor", output.getColorTextureId());
            resolveShader.safeGetUniform("EffectSize").set((float) main.width, (float) main.height);
            resolveShader.apply();
            EffectPipeline.drawFullscreenQuad();
            resolveShader.clear();
            state.restore();
            LASERS.clear();
        }
    }

    public static void close() {
        LASERS.clear();
        if (volume != null) {
            volume.close();
            volume = null;
        }
        if (effect != null) {
            effect.destroyBuffers();
            effect = null;
        }
    }

    private record Laser(Vec3 start, Vector3f direction, float length, float radius, float time, float alpha, float headTaperSpeed, float tailTaperSpeed) {
    }
}
