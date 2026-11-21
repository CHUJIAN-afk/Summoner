package first.summoner.common.minion.minion;

import first.lyra.common.attachmentEntity.AttachmentEntityGoalSelector;
import first.summoner.common.minion.minion.goal.voidEater.VoidEaterAttackGoal;
import first.summoner.common.minion.worm.WormAttachmentEntity;
import first.summoner.register.SummonerAttachmentEntityRegister;

public class VoidEater extends WormAttachmentEntity {

    public VoidEater() {
        super(SummonerAttachmentEntityRegister.VOID_EATER);
        setDrag(0.75f);
    }

    @Override
    public void registerGoals(AttachmentEntityGoalSelector goalSelector) {
        super.registerGoals(goalSelector);
        goalSelector.addGoal(0, new VoidEaterAttackGoal(this));
    }
}
