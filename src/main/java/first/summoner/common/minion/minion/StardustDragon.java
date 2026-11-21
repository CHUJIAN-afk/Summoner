package first.summoner.common.minion.minion;

import first.lyra.common.attachmentEntity.AttachmentEntityGoalSelector;
import first.lyra.common.particle.genericParticle.GenericParticleBuilder;
import first.lyra.utils.ParticleHelper;
import first.summoner.common.minion.minion.goal.stardustDragon.StardustDragonAttackGoal;
import first.summoner.common.minion.worm.WormAttachmentEntity;
import first.summoner.register.SummonerAttachmentEntityRegister;

public class StardustDragon extends WormAttachmentEntity {

    public StardustDragon() {
        super(SummonerAttachmentEntityRegister.STARDUST_DRAGON);
    }

    @Override
    public void registerGoals(AttachmentEntityGoalSelector goalSelector) {
        super.registerGoals(goalSelector);
        goalSelector.addGoal(0, new StardustDragonAttackGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (getNextSegment() == null) {
            ParticleHelper.create(getLevel())
                    .generic(GenericParticleBuilder.create()
                                     .centerColor(0x2fb2e1)
                                     .edgeColor(0x33ccff)
                                     .lifetime(5)
                                     .lifetimeRandom(25)
                                     .spin(0.1f)
                                     .spinRandom(0.5F)
                                     .friction(0.75F)
                                     .scale(0.035f)
                                     .scaleRandom(0.005f)
                    )
                    .pos(getPos())
                    .offset(0.2)
                    .velocity(getVelocity().scale(-1))
                    .spread(0)
                    .speed(0)
                    .emit();
        }
    }
}
