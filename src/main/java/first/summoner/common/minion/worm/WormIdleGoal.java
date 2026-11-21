package first.summoner.common.minion.worm;

import first.lyra.common.attachmentEntity.AttachmentEntityGoal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

class WormIdleGoal extends AttachmentEntityGoal<WormAttachmentEntity> {

    private Vec3 offset = Vec3.ZERO;

    WormIdleGoal(WormAttachmentEntity minion) {
        super(minion);
    }

    @Override
    public boolean canUse() {
        return minion.isHead() && minion.getTarget() == null;
    }

    @Override
    public void start() {
        LivingEntity owner = minion.getOwner();
        Vec3 ownerCenter = owner.getBoundingBox().getCenter();
        Vec3 center = ownerCenter.add(0, owner.getBbHeight() + 1, 0).add(Vec3.ZERO.offsetRandom(minion.getRandom(), owner.getBbWidth() * 2));
        Vec3 direction = center.subtract(minion.getPos()).normalize();
        offset = center.add(direction.scale(1.25F)).subtract(ownerCenter);
    }

    @Override
    public void tick() {
        LivingEntity owner = minion.getOwner();
        Vec3 targetPos = owner.getBoundingBox().getCenter().add(offset);
        Vec3 minionPos = minion.getPos();
        if (minionPos.distanceTo(targetPos) > 1) {
            minion.orbitToward(targetPos, 64f, 0.02 + minion.getPos().distanceTo(targetPos) * 0.005);
        } else {
            if (minion.getVelocity().length() < 0.1) {
                start();
            }
        }
    }
}
