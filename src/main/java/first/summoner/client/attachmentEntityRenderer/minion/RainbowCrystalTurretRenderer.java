package first.summoner.client.attachmentEntityRenderer.minion;

import com.mojang.blaze3d.vertex.PoseStack;
import first.lyra.client.render.AbstractAttachmentEntityRenderer;
import first.lyra.client.render.ModelContext;
import first.lyra.client.render.RenderContext;
import first.lyra.common.attachmentEntity.PathNode;
import first.summoner.client.render.RainbowCrystalEffect;
import first.summoner.common.minion.sentry.RainbowCrystal;
import net.minecraft.client.renderer.MultiBufferSource;

/**
 * 彩虹水晶哨兵渲染器。
 */
public class RainbowCrystalTurretRenderer extends AbstractAttachmentEntityRenderer<RainbowCrystal> {

    @Override
    protected RenderContext<RainbowCrystal> createContext(RainbowCrystal crystal, PathNode visualNode, float partialTick, int packedLight) {
        return super.createContext(crystal, visualNode, partialTick, packedLight)
                .model(new ModelContext().alphaDistanceFactor(0));
    }

    @Override
    protected void render(PoseStack poseStack, MultiBufferSource bufferSource) {
        float time = (context.entity.getLevel().getGameTime() % 240000 + context.partialTick) / 20f;
        float phase = (context.entity.getUuid().hashCode() & 65535) * (float) (Math.PI / 32768);
        RainbowCrystalEffect.crystal(poseStack, context.color.getAlphaFloat(), time, phase);
    }
}
