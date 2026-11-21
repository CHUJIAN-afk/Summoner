package first.summoner.common.minion.minion;

import first.lyra.api.LyraHelper;
import first.lyra.common.attachment.AttachmentEntityData;
import first.lyra.common.attachment.TargetCache;
import first.lyra.common.attachmentEntity.PathNode;
import first.lyra.common.attachmentEntity.SyncFieldDispatcher;
import first.lyra.common.minion.MomentumMinion;
import first.lyra.common.sound.Playable;
import first.lyra.register.LyraAttachmentRegister;
import first.lyra.utils.LyraStreamCodecs;
import first.summoner.common.minion.worm.WormAttachmentEntity;
import first.summoner.common.projectile.ProbeLaser;
import first.summoner.register.SummonerAttachmentEntityRegister;
import first.summoner.register.SummonerSoundRegister;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.UUID;

public class MiniProbe extends MomentumMinion {

    private Optional<UUID> bodyId = Optional.empty();
    private boolean docked = true;
    private long nextShotTick;

    public MiniProbe() {
        super(SummonerAttachmentEntityRegister.MINI_PROBE);
        setGravity(0);
        setSlotCost(0);
    }

    @Override
    protected void registerSyncFields(SyncFieldDispatcher fields) {
        super.registerSyncFields(fields);
        fields.field(LyraStreamCodecs.OPTIONAL_UUID, () -> bodyId, value -> bodyId = value);
        fields.field(ByteBufCodecs.BOOL, () -> docked, value -> docked = value);
    }

    public void attachTo(MiniDestroyer body) {
        bodyId = Optional.of(body.getUuid());
        setOwner(body.getOwner());
        init(dockNode(body.getCurrentPathNode()));
    }

    @Nullable
    private MiniDestroyer getBody() {
        AttachmentEntityData data = owner.getData(LyraAttachmentRegister.EntityData);
        if (bodyId.isPresent() && data.getEntity(bodyId.get()) instanceof MiniDestroyer body && !body.isRemove() && body.getNextSegment() != null) {
            return body;
        }
        return null;
    }

    private PathNode dockNode(PathNode bodyNode) {
        Vector3f offset = bodyNode.toQuaternion().transform(new Vector3f(0, 3f / 16, -0.5f / 16));
        return bodyNode.modifyPos(bodyNode.pos().add(offset.x(), offset.y(), offset.z()));
    }

    @Override
    public boolean isAlive() {
        return super.isAlive() && getBody() != null;
    }

    @Override
    public int getSearchDistance() {
        return 16;
    }

    @Override
    public LivingEntity searchTarget() {
        return docked ? null : findTarget(getPos());
    }

    private LivingEntity findTarget(Vec3 center) {
        TargetCache cache = getTargetCache();
        return cache.getNewTarget(this, cache.getEntitiesInRadius(center, getSearchDistance(), cache::isTarget), 0, true);
    }

    private void shoot(Vec3 origin, LivingEntity target, long tick) {
        nextShotTick = tick + getRandom().nextIntBetweenInclusive(20, 30);
        Vec3 direction = target.getBoundingBox().getCenter().subtract(origin).normalize();
        ProbeLaser laser = new ProbeLaser();
        laser.setOwner(owner);
        laser.init(new PathNode(origin.add(direction), direction));
        laser.copyAttributes(this);
        laser.setVelocity(direction);
        laser.setDamage(getDamage() * 1.1f);
        LyraHelper.get(owner).add(laser);
        Playable.play(SummonerSoundRegister.Laser, getLevel(), origin, getSoundSource());
    }

    private void dockAt(PathNode anchor) {
        docked = true;
        setPlannedPath(null);
        setCurrentPathNode(anchor);
        setDesiredRotation(anchor.yaw(), anchor.pitch(), anchor.roll());
        setVelocity(Vec3.ZERO);
    }

    @Override
    public void tickPhysics() {
        MiniDestroyer body = getBody();
        if (body != null) {
            PathNode anchor = dockNode(body.getCurrentPathNode());
            LivingEntity target = getTarget();
            long tick = getLevel().getGameTime();
            if (docked) {
                setCurrentPathNode(anchor);
                if (tick >= nextShotTick) {
                    WormAttachmentEntity head = body.getHead();
                    LivingEntity headTarget = head != null ? head.getTarget() : null;
                    if (headTarget != null && headTarget.isAlive()) {
                        shoot(anchor.pos(), headTarget, tick);
                    }
                }
                if (getRandom().nextFloat() < 0.005f) {
                    LivingEntity detachTarget = findTarget(anchor.pos());
                    if (detachTarget != null) {
                        docked = false;
                        setTarget(detachTarget);
                        setDesiredRotation(anchor.yaw(), anchor.pitch(), anchor.roll());
                        setVelocity(body.getCurrentNormal().scale(0.4));
                        super.tickPhysics();
                    }
                }
            } else if (target != null) {
                Vec3 targetPos = target.getBoundingBox().getCenter();
                Vec3 toTarget = targetPos.subtract(getPos());
                double acceleration = Math.clamp((toTarget.length() - 5) * 0.02, -0.05, 0.05);
                addVelocity(toTarget.normalize().scale(acceleration));
                lookAtPos(targetPos);
                if (tick >= nextShotTick) {
                    shoot(getPos(), target, tick);
                }
                super.tickPhysics();
            } else {
                Vec3 toAnchor = anchor.pos().subtract(getPos());
                double distance = toAnchor.length();
                double dockDistance = 0.35;
                if (distance > dockDistance) {
                    double speed = Math.min(distance, 2.5) / getDrag();
                    setVelocity(toAnchor.normalize().scale(speed));
                    lookAtPos(anchor.pos());
                    super.tickPhysics();
                }
                if (getPos().distanceToSqr(anchor.pos()) <= dockDistance * dockDistance) {
                    dockAt(anchor);
                }
            }
        }
    }

    @Override
    public PathNode getRenderNode(float partialTick) {
        MiniDestroyer body = docked ? getBody() : null;
        return body != null ? dockNode(body.getRenderNode(partialTick)) : super.getRenderNode(partialTick);
    }
}
