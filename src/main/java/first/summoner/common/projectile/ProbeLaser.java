package first.summoner.common.projectile;

import first.lyra.common.attachmentEntity.IEntityCollision;
import first.lyra.common.projectile.Projectile;
import first.summoner.register.SummonerAttachmentEntityRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProbeLaser extends Projectile implements IEntityCollision<ProbeLaser> {

    public ProbeLaser() {
        super(SummonerAttachmentEntityRegister.PROBE_LASER);
        setDrag(1);
        setGravity(0);
        setMaxTickCount(100);
    }

    @Override
    public void onCollisionAttack(List<HitContext> hitContexts) {
        HitContext hit = hitContexts.getFirst();
        LivingEntity target = hit.entity();
        attack(target, getDamage(), 0);
        currentPathNode = currentPathNode.modifyPos(hit.hitPoint());
        setRemove();
    }

    @Override
    public @NotNull AABB getHitbox() {
        return new AABB(-0.03, -0.03, -1, 0.03, 0.03, 0);
    }
}
