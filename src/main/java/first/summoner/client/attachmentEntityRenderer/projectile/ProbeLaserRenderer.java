package first.summoner.client.attachmentEntityRenderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import first.lyra.client.render.AbstractAttachmentEntityRenderer;
import first.lyra.client.render.rendererHelper.LaserRendererHelper;
import first.summoner.common.projectile.ProbeLaser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.phys.AABB;

public class ProbeLaserRenderer extends AbstractAttachmentEntityRenderer<ProbeLaser> {

    @Override
    protected void render(PoseStack poseStack, MultiBufferSource bufferSource) {
        AABB hitbox = context.entity.getHitbox();
        float length = (float) hitbox.getZsize();
        float width = (float) hitbox.getXsize() * 0.5f;
        LaserRendererHelper.builder()
                .length(length)
                .radius(width, width)
                .layers(4)
                .segments(4)
                .color(0x3399FF)
                .alpha(0.95f)
                .innerRatio(0.15f)
                .render(poseStack, bufferSource);
    }
}
