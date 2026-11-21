package first.summoner.common.projectile;

import first.lyra.common.attachmentEntity.IEntityCollision;
import first.lyra.common.projectile.Projectile;
import first.summoner.common.minion.minion.MiniDestroyer;
import first.summoner.register.SummonerAttachmentEntityRegister;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DestructionOmen extends Projectile implements IEntityCollision<DestructionOmen> {

    private final AABB hitbox = new AABB(-0.15, -0.15, 0, 0.15, 0.15, 48);
    private MiniDestroyer source;

    public DestructionOmen() {
        super(SummonerAttachmentEntityRegister.DESTRUCTION_OMEN);
        setPhysics(false);
        setMaxTickCount(60);
    }

    public void follow(MiniDestroyer source) {
        this.source = source;
    }

    @Override
    public boolean isAlive() {
        return source != null && !source.isRemove();
    }

    @Override
    public boolean canCollideAttack() {
        return !isRemove() && isAlive();
    }

    @Override
    public @NotNull AABB getHitbox() {
        return hitbox;
    }

    @Override
    public void onCollisionAttack(List<HitContext> hitContexts) {
        for (HitContext hit : hitContexts) {
            attack(hit.entity(), getDamage(), 1);
        }
    }
}
