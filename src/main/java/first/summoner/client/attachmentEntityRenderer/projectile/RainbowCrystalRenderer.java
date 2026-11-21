package first.summoner.client.attachmentEntityRenderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import first.lyra.client.render.AbstractAttachmentEntityRenderer;
import first.lyra.client.render.ModelContext;
import first.lyra.client.render.RenderContext;
import first.lyra.common.attachmentEntity.PathNode;
import first.summoner.client.render.RainbowCrystalEffect;
import first.summoner.common.projectile.MiniRainbowCrystal;
import net.minecraft.client.renderer.MultiBufferSource;

public class RainbowCrystalRenderer extends AbstractAttachmentEntityRenderer<MiniRainbowCrystal> {

    @Override
    protected RenderContext<MiniRainbowCrystal> createContext(MiniRainbowCrystal crystal, PathNode visualNode, float partialTick, int packedLight) {
        return super.createContext(crystal, visualNode, partialTick, packedLight)
                .model(new ModelContext().alphaDistanceFactor(0f));
    }

    @Override
    protected void render(PoseStack poseStack, MultiBufferSource bufferSource) {
        MiniRainbowCrystal crystal = context.entity;
        float age = crystal.getTickCount() + context.partialTick;
        float time = (crystal.getLevel().getGameTime() % 240000 + context.partialTick) / 20f;
        float phase = (crystal.getUuid().hashCode() & 65535) * (float) (Math.PI / 32768);
        RainbowCrystalEffect.projectile(poseStack, crystal.getSourcePosition().subtract(context.visualNode.pos()), age, context.color.getAlphaFloat(), time, phase);
    }
}
