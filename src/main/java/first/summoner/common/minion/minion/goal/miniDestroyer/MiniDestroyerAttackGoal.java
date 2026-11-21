package first.summoner.common.minion.minion.goal.miniDestroyer;

import first.lyra.api.LyraHelper;
import first.lyra.common.attachmentEntity.AttachmentEntityGoal;
import first.lyra.common.attachmentEntity.PathNode;
import first.summoner.common.minion.minion.MiniDestroyer;
import first.summoner.common.projectile.DestructionOmen;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MiniDestroyerAttackGoal extends AttachmentEntityGoal<MiniDestroyer> {

    private int cooldown = 1200;
    private int chargeTicks;
    private double chargeStartSpeed;
    private DestructionOmen beam;

    public MiniDestroyerAttackGoal(MiniDestroyer minion) {
        super(minion);
    }

    @Override
    public boolean canUse() {
        return minion.isHead() && minion.getTarget() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return minion.isHead() && (minion.getTarget() != null || beam != null && !beam.isRemove());
    }

    @Override
    public void tick() {
        LivingEntity target = minion.getTarget();
        if (target != null && cooldown > 0) {
            cooldown = 1200;
        }
        if (beam != null && beam.isRemove()) {
            beam = null;
        }
        double beamSpeed = 0.06;
        if (beam != null) {
            if (target != null) {
                aimAt(target.getBoundingBox().getCenter(), 0.5f);
            }
            minion.setVelocity(minion.getLookAngle().scale(beamSpeed));
        } else if (target != null) {
            Vec3 targetPos = target.getBoundingBox().getCenter();
            double distanceSquared = minion.getPos().distanceToSqr(targetPos);
            if (chargeTicks > 0 || cooldown == 0 && distanceSquared <= 16 * 16) {
                if (chargeTicks == 0) {
                    chargeStartSpeed = Math.max(beamSpeed, minion.getVelocity().length());
                }
                aimAt(targetPos, 12);
                double speed = Mth.lerp(++chargeTicks / 20.0, chargeStartSpeed, beamSpeed);
                minion.setVelocity(minion.getLookAngle().scale(speed));
                if (chargeTicks == 20) {
                    beam = new DestructionOmen();
                    beam.setOwner(minion.getOwner());
                    beam.follow(minion);
                    beam.init(minion.getCurrentPathNode());
                    beam.copyAttributes(minion);
                    LyraHelper.get(minion.getOwner()).add(beam);
                    chargeTicks = 0;
                    cooldown = 600;
                }
            } else {
                minion.orbitToward(targetPos, 125f, 0.1 + Math.min(Math.sqrt(distanceSquared) * 0.01, 0.05));
            }
        }
    }

    @Override
    public void stop() {
        chargeTicks = 0;
        if (beam != null) {
            beam.setRemove();
            beam = null;
        }
    }

    public void syncBeamPosition() {
        if (beam != null) {
            PathNode pathNode = minion.getCurrentPathNode();
            beam.setCurrentPathNode(pathNode.modifyPos(pathNode.pos().add(Vec3.directionFromRotation(pathNode.pitch(), pathNode.yaw()).normalize().scale(0.45))));
        }
    }

    private void aimAt(Vec3 targetPos, float maxTurnDegrees) {
        Vec3 desired = targetPos.subtract(minion.getPos()).normalize();
        if (desired.lengthSqr() > 0) {
            Vec3 forward = minion.getLookAngle();
            double dot = Mth.clamp(forward.dot(desired), -1, 1);
            double maxTurn = Math.toRadians(maxTurnDegrees);
            double cosTurn = Math.cos(maxTurn);
            if (dot < cosTurn) {
                Vec3 tangent = desired.subtract(forward.scale(dot)).normalize();
                if (tangent.lengthSqr() == 0) {
                    Vec3 axis = Math.abs(forward.y) < 0.99 ? new Vec3(0, 1, 0) : new Vec3(1, 0, 0);
                    tangent = axis.subtract(forward.scale(axis.dot(forward))).normalize();
                }
                desired = forward.scale(cosTurn).add(tangent.scale(Math.sin(maxTurn)));
            }
            PathNode direction = new PathNode(minion.getPos(), desired);
            PathNode node = minion.getCurrentPathNode().modifyEuler(direction.yaw(), direction.pitch(), minion.getRoll());
            minion.setCurrentPathNode(node);
            minion.setDesiredRotation(node.yaw(), node.pitch(), node.roll());
        }
    }
}
