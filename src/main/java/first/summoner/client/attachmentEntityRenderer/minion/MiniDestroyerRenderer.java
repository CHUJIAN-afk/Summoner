package first.summoner.client.attachmentEntityRenderer.minion;

import first.lyra.client.render.ModelContext;
import first.lyra.client.render.RenderContext;
import first.lyra.client.render.geo.AbstractAttachmentEntityGeoRenderer;
import first.lyra.common.attachmentEntity.PathNode;
import first.lyra.common.minion.MomentumMinion;
import first.summoner.Summoner;
import first.summoner.common.minion.minion.MiniDestroyer;

public class MiniDestroyerRenderer<T extends MomentumMinion> extends AbstractAttachmentEntityGeoRenderer<T> {

    public MiniDestroyerRenderer() {
        super(context -> {
            if (context.entity instanceof MiniDestroyer destroyer) {
                String part = destroyer.isHead() ? "head" : destroyer.getOrder() == destroyer.getSameSize() - 1 ? "tail" : "body";
                return Summoner.rl("entity/summon/mini_destroyer_" + part);
            }
            return Summoner.rl("entity/summon/mini_probe");
        });
    }

    @Override
    protected RenderContext<T> createContext(T entity, PathNode visualNode, float partialTick, int packedLight) {
        return super.createContext(entity, visualNode, partialTick, packedLight)
                .model(new ModelContext().rotationOffset(90, 0, 0));
    }
}
