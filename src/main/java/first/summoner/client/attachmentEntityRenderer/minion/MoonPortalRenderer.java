package first.summoner.client.attachmentEntityRenderer.minion;

import com.mojang.blaze3d.vertex.PoseStack;
import first.lyra.client.render.AbstractAttachmentEntityRenderer;
import first.lyra.client.render.ModelContext;
import first.lyra.client.render.RenderContext;
import first.lyra.common.attachmentEntity.PathNode;
import first.summoner.client.render.MoonPortalEffect;
import first.summoner.common.minion.sentry.MoonPortal;
import net.minecraft.client.renderer.MultiBufferSource;

/**
 * 月亮传送门：体积旋涡。
 */
public class MoonPortalRenderer extends AbstractAttachmentEntityRenderer<MoonPortal> {

    @Override
    protected RenderContext<MoonPortal> createContext(MoonPortal portal, PathNode visualNode, float partialTick, int packedLight) {
        return super.createContext(portal, visualNode, partialTick, packedLight)
                .model(new ModelContext()
                               .scale(0.75f)
                               .alphaDistanceFactor(0f));
    }

    @Override
    protected void render(PoseStack poseStack, MultiBufferSource bufferSource) {
        MoonPortal portal = context.entity;
        float time = (portal.getLevel().getGameTime() % 240000 + context.partialTick) / 20f;
        float phase = (portal.getUuid().hashCode() & 65535) * (float) (Math.PI / 32768);
        MoonPortalEffect.submit(poseStack, context.color.getAlphaFloat(), time, phase);
    }
}
