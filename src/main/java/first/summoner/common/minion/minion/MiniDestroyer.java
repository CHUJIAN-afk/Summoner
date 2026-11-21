package first.summoner.common.minion.minion;

import first.lyra.common.attachmentEntity.AttachmentEntityGoalSelector;
import first.summoner.common.minion.minion.goal.miniDestroyer.MiniDestroyerAttackGoal;
import first.summoner.common.minion.worm.WormAttachmentEntity;
import first.summoner.register.SummonerAttachmentEntityRegister;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class MiniDestroyer extends WormAttachmentEntity {

    public MiniDestroyer() {
        super(SummonerAttachmentEntityRegister.MINI_DESTROYER);
    }

    @Override
    public void registerGoals(AttachmentEntityGoalSelector goalSelector) {
        super.registerGoals(goalSelector);
        goalSelector.addGoal(0, new MiniDestroyerAttackGoal(this));
    }

    @Override
    public void tickPhysics() {
        super.tickPhysics();
        if (getGoalSelector().getCurrentGoal() instanceof MiniDestroyerAttackGoal attackGoal) {
            attackGoal.syncBeamPosition();
        }
    }

    @Override
    public double getSegmentDistance() {
        return 10.0 / 16;
    }

    @Override
    public @NotNull AABB getHitbox() {
        double halfWidth = isHead() ? 5.0 / 16 : 4.0 / 16;
        double minZ = getOrder() == getSameSize() - 1 ? -12.0 / 16 : -5.0 / 16;
        double maxZ = isHead() ? 12.0 / 16 : 5.0 / 16;
        return new AABB(-halfWidth, -2.5 / 16, minZ, halfWidth, 3.5 / 16, maxZ);
    }
}
