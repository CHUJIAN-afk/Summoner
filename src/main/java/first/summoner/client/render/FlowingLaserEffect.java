package first.summoner.client.render;

import first.lyra.client.render.IrisCompat;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * 流动激光：沿轴向绘制朝向相机的带状几何，片元着色器按轴向进度与径向比例计算半径 profile、
 * 内芯/边缘颜色与流动光带。参数面与原 Photon 聚光灯版本一致，着色与绘制全部由 Summoner 自己的着色器完成。
 */
public final class FlowingLaserEffect {

    private static final int SEGMENTS = 128;
    private static final List<Laser> LASERS = new ArrayList<>();
    private static ShaderInstance shader;
    private static VertexBuffer ribbon;

    private int innerColor = 0xFFFFFF;
    private int edgeColor = 0xFF3355;
    private float innerRadius = 0.6f;
    private float edgeRadius = 1f;
    private Vec3 start = Vec3.ZERO;
    private Vec3 direction = new Vec3(0, 0, 1);
    private float length = 16;
    private float expansion = 6;
    private float contraction = 6;
    private float startRadius = 0;
    private float processRadius = 2;
    private float endRadius = 0;
    private float flowSpeed = 8;

    private FlowingLaserEffect() {
    }

    public static FlowingLaserEffect builder() {
        return new FlowingLaserEffect();
    }

    public static void setShader(ShaderInstance value) {
        shader = value;
    }

    public FlowingLaserEffect inner(int color, float radius) {
        innerColor = color;
        innerRadius = radius;
        return this;
    }

    public FlowingLaserEffect edge(int color, float radius) {
        edgeColor = color;
        edgeRadius = radius;
        return this;
    }

    public FlowingLaserEffect from(Vec3 start) {
        this.start = start;
        return this;
    }

    public FlowingLaserEffect direction(Vec3 direction) {
        this.direction = direction;
        return this;
    }

    public FlowingLaserEffect length(float length) {
        this.length = length;
        return this;
    }

    public FlowingLaserEffect spread(float expansion, float contraction) {
        this.expansion = expansion;
        this.contraction = contraction;
        return this;
    }

    public FlowingLaserEffect radius(float start, float process, float end) {
        startRadius = start;
        processRadius = process;
        endRadius = end;
        return this;
    }

    public FlowingLaserEffect flowSpeed(float flowSpeed) {
        this.flowSpeed = flowSpeed;
        return this;
    }

    /** 收集本帧参数，由 {@link #render(Camera, Matrix4f, Matrix4f)} 在世界渲染阶段统一绘制。 */
    public void render(float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        float visibleRadius = Math.max(startRadius, Math.max(processRadius, endRadius)) * edgeRadius;
        if (minecraft.level != null && shader != null && !IrisCompat.isShadowPass()
                && length > 0 && visibleRadius > 0 && direction.lengthSqr() > 0) {
            float time = (minecraft.level.getGameTime() % 240000 + partialTick) / 20f;
            LASERS.add(new Laser(start.toVector3f(), direction.normalize().toVector3f(), length, startRadius,
                    processRadius, endRadius, expansion, contraction, innerRadius, edgeRadius,
                    innerColor, edgeColor, flowSpeed, time));
        }
    }

    public static void render(Camera camera, Matrix4f view, Matrix4f projection) {
        if (shader != null && !IrisCompat.isShadowPass() && !LASERS.isEmpty()) {
            ShaderInstance previous = RenderSystem.getShader();
            Vector3f eye = camera.getPosition().toVector3f();
            RenderSystem.disableScissor();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE);
            if (ribbon == null) {
                createRibbon();
            }
            shader.safeGetUniform("BeamProjection").set(projection);
            ribbon.bind();
            for (Laser laser : LASERS) {
                Vector3f origin = view.transformPosition(new Vector3f(laser.start).sub(eye));
                Vector3f axis = view.transformDirection(new Vector3f(laser.direction)).normalize();
                shader.safeGetUniform("BeamOrigin").set(origin);
                shader.safeGetUniform("BeamAxis").set(axis);
                shader.safeGetUniform("BeamRadius").set(laser.startRadius, laser.processRadius, laser.endRadius, laser.length);
                shader.safeGetUniform("BeamShape").set(laser.expansion, laser.contraction, laser.innerRadius, laser.edgeRadius);
                shader.safeGetUniform("BeamInner").set(red(laser.innerColor), green(laser.innerColor), blue(laser.innerColor), laser.flowSpeed);
                shader.safeGetUniform("BeamEdge").set(red(laser.edgeColor), green(laser.edgeColor), blue(laser.edgeColor), laser.time);
                shader.apply();
                ribbon.draw();
                shader.clear();
            }
            VertexBuffer.unbind();
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableCull();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShader(() -> previous);
            LASERS.clear();
        }
    }

    private static void createRibbon() {
        BufferBuilder builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        for (int segment = 0; segment < SEGMENTS; segment++) {
            float first = segment / (float) SEGMENTS;
            float last = (segment + 1) / (float) SEGMENTS;
            builder.addVertex(first, -1, 0);
            builder.addVertex(last, -1, 0);
            builder.addVertex(last, 1, 0);
            builder.addVertex(first, 1, 0);
        }
        ribbon = new VertexBuffer(VertexBuffer.Usage.STATIC);
        ribbon.bind();
        ribbon.upload(builder.buildOrThrow());
    }

    private static float red(int color) {
        return (color >> 16 & 255) / 255f;
    }

    private static float green(int color) {
        return (color >> 8 & 255) / 255f;
    }

    private static float blue(int color) {
        return (color & 255) / 255f;
    }

    public static void close() {
        LASERS.clear();
        if (ribbon != null) {
            ribbon.close();
            ribbon = null;
        }
    }

    private record Laser(Vector3f start, Vector3f direction, float length, float startRadius, float processRadius,
                         float endRadius, float expansion, float contraction, float innerRadius, float edgeRadius,
                         int innerColor, int edgeColor, float flowSpeed, float time) {
    }
}
