package first.summoner.client.attachmentEntityRenderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import first.lyra.client.render.AbstractAttachmentEntityRenderer;
import first.lyra.client.render.ModelContext;
import first.lyra.client.render.RenderContext;
import first.lyra.common.attachmentEntity.PathNode;
import first.summoner.client.render.MoonPortalLaserEffect;
import first.summoner.common.projectile.MoonPortalLaser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class MoonPortalLaserRenderer extends AbstractAttachmentEntityRenderer<MoonPortalLaser> {

    @Override
    protected RenderContext<MoonPortalLaser> createContext(MoonPortalLaser laser, PathNode visualNode, float partialTick, int packedLight) {
        return super.createContext(laser, visualNode, partialTick, packedLight)
                .model(new ModelContext()
                               .alphaDistanceFactor(0)
                               .rotationOffset(180, 0, 0));
    }

    @Override
    protected void render(PoseStack poseStack, MultiBufferSource bufferSource) {
        MoonPortalLaser entity = context.entity;
        float age = entity.getTickCount() + context.partialTick;
        float appear = Mth.clamp(age / 3f, 0f, 1f);
        float disappear = Mth.clamp((entity.getMaxTickCount() - age) / 5f, 0f, 1f);
        float appearEnvelope = appear * appear * (3f - 2f * appear);
        float disappearEnvelope = disappear * disappear * (3f - 2f * disappear);
        float phase = (entity.getUuid().hashCode() & 65535) * (float) (Math.PI / 32768);
        float pulse = 1f + 0.055f * Mth.sin(age * 2.4f + phase) + 0.025f * Mth.sin(age * 5.1f + phase * 1.73f);
        float radius = (float) entity.getHitbox().getXsize() * appearEnvelope * pulse;
        PathNode visualNode = context.visualNode;
        Vec3 pos = visualNode.pos();
        Vec3 direction = Vec3.directionFromRotation(visualNode.pitch(), visualNode.yaw());
        MoonPortalLaserEffect.submit(pos, direction, entity.getLength(context.partialTick), radius, age / 20f + phase, context.color.getAlphaFloat() * disappearEnvelope, 1f, 1f);
    }
}
