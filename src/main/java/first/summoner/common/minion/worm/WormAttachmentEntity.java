package first.summoner.common.minion.worm;

import first.lyra.common.attachment.TargetCache;
import first.lyra.common.attachmentEntity.AttachmentEntity;
import first.lyra.common.attachmentEntity.AttachmentEntityGoalSelector;
import first.lyra.common.attachmentEntity.AttachmentEntityType;
import first.lyra.common.attachmentEntity.IEntityCollision;
import first.lyra.common.minion.MomentumMinion;
import first.lyra.register.LyraAttachmentRegister;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class WormAttachmentEntity extends MomentumMinion implements IEntityCollision<WormAttachmentEntity> {

    private float spiralPhase;

    protected WormAttachmentEntity(Holder<AttachmentEntityType<?>> type) {
        super(type);
        setGravity(0);
    }

    @Override
    public void registerGoals(AttachmentEntityGoalSelector goalSelector) {
        goalSelector.addGoal(1, new WormIdleGoal(this));
        goalSelector.addGoal(2, new WormFollowGoal(this));
    }

    @Override
    public int getSearchDistance() {
        return 32;
    }

    @Override
    public LivingEntity searchTarget() {
        int distance = isHead() ? getSearchDistance() : 0;
        if (distance > 0) {
            TargetCache targetCache = getTargetCache();
            List<LivingEntity> targets = targetCache.getEntitiesInRadius(owner.getBoundingBox().getCenter(), distance, targetCache::isTarget);
            if (!targets.isEmpty()) {
                return targetCache.getNewTarget(this, targets, 0.0F, true);
            }
        }
        return null;
    }

    @Override
    public void onRemove() {
        if (isHead()) {
            owner.getData(LyraAttachmentRegister.EntityData).remove(getType());
        }
    }

    public boolean isHead() {
        return getOrder() == 0;
    }

    @Override
    public boolean isAlive() {
        return super.isAlive() && (isHead() || getPrecedingSegment() != null);
    }

    @Nullable
    public WormAttachmentEntity getHead() {
        return getSegment(0);
    }

    @Nullable
    public WormAttachmentEntity getPrecedingSegment() {
        return isHead() ? null : getSegment(getOrder() - 1);
    }

    @Nullable
    public WormAttachmentEntity getNextSegment() {
        return getSegment(getOrder() + 1);
    }

    @Nullable
    private WormAttachmentEntity getSegment(int index) {
        List<AttachmentEntity> segments = owner.getData(LyraAttachmentRegister.EntityData).getGroups().get(getType());
        if (segments != null && index < segments.size()) {
            WormAttachmentEntity segment = (WormAttachmentEntity) segments.get(index);
            if (!segment.isRemove()) {
                return segment;
            }
        }
        return null;
    }

    @Override
    public @NotNull AABB getHitbox() {
        int segment = getOrder();
        int total = Math.max(1, getSameSize());
        boolean isHead = segment == 0;
        boolean isTail = segment == total - 1;
        double minZ = isHead ? -0.3 : isTail ? -1.45 : -0.35;
        double maxZ = isHead ? 0.65 : isTail ? 0.3 : 0.35;
        return new AABB(-0.25, -0.25, minZ, 0.25, 0.25, maxZ);
    }

    @Override
    public boolean canCollideAttack() {
        WormAttachmentEntity head = getHead();
        if (head != null) {
            return head.getTarget() != null;
        }
        return false;
    }

    @Override
    public void onCollisionAttack(List<HitContext> hitContexts) {
        WormAttachmentEntity head = getHead();
        if (head != null) {
            for (HitContext hit : hitContexts) {
                head.attack(hit.entity(), getDamage(), 2);
            }
        }
    }

    @Override
    public float getDamage() {
        return super.getDamage() * (1 + 0.23f * (getSameSize() - 1));
    }

    public double getSegmentDistance() {
        return 0.65;
    }

    // 限制转向并施加游动加速度，可选螺旋偏移。
    public void orbitToward(Vec3 targetPos, float maxTurnDegrees, double acceleration, boolean spiral) {
        Vec3 currentPos = getPos();
        Vec3 velocity = getVelocity();
        Vec3 toTarget = targetPos.subtract(currentPos);
        double distance = toTarget.length();

        spiralPhase += 0.2f * (float) velocity.length();

        Vec3 forward = getCurrentVelocity();

        double maxTurn;
        WormAttachmentEntity nextSeg = getNextSegment();
        if (nextSeg != null) {
            Vec3 nextDir = nextSeg.getCurrentVelocity();
            double angleToNext = Math.acos(Mth.clamp(forward.dot(nextDir), -1, 1));
            maxTurn = Math.max(Math.toRadians(2.0), Math.toRadians(maxTurnDegrees) - angleToNext);
        } else {
            maxTurn = Math.toRadians(12.0);
        }

        if (distance > 0.01) {
            Vec3 desiredDir = toTarget.normalize();
            double angleDiff = Math.acos(Mth.clamp(forward.dot(desiredDir), -1, 1));
            if (angleDiff > maxTurn) {
                Vec3 rotAxis = forward.cross(desiredDir);
                if (rotAxis.lengthSqr() < 0.0001) {
                    rotAxis = forward.cross(new Vec3(0, 1, 0));
                }
                if (rotAxis.lengthSqr() < 0.0001) {
                    rotAxis = forward.cross(new Vec3(1, 0, 0));
                }
                rotAxis = rotAxis.normalize();
                double cosT = Math.cos(maxTurn), sinT = Math.sin(maxTurn);
                forward = forward.scale(cosT)
                        .add(rotAxis.cross(forward).scale(sinT))
                        .add(rotAxis.scale(rotAxis.dot(forward) * (1 - cosT)))
                        .normalize();
            } else {
                forward = desiredDir;
            }
        }

        double amplitude = spiral ? Math.clamp(distance * 0.08, 0.15, 0.4) : 0;

        if (spiral) {
            Vec3 right = forward.cross(new Vec3(0, 1, 0));
            if (right.lengthSqr() < 0.001) {
                right = new Vec3(1, 0, 0);
            } else {
                right = right.normalize();
            }
            Vec3 up = right.cross(forward).normalize();
            forward = forward
                    .add(right.scale(Math.cos(spiralPhase) * amplitude))
                    .add(up.scale(Math.sin(spiralPhase) * amplitude))
                    .normalize();
        }

        addVelocity(forward.scale(acceleration));
        lookAtDirection(velocity.normalize());
    }

    public void orbitToward(Vec3 targetPos, float maxTurnDegrees, double acceleration) {
        orbitToward(targetPos, maxTurnDegrees, acceleration, true);
    }
}
