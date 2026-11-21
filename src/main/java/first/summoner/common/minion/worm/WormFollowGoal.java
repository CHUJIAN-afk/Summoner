package first.summoner.common.minion.worm;

import first.lyra.common.attachmentEntity.AttachmentEntityGoal;
import first.lyra.common.attachmentEntity.PathNode;
import first.lyra.common.attachmentEntity.PlannedPath;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.List;

// 按体节间距沿前一体节的历史轨迹跟随。
class WormFollowGoal extends AttachmentEntityGoal<WormAttachmentEntity> {

    WormFollowGoal(WormAttachmentEntity minion) {
        super(minion);
    }

    @Override
    public boolean canUse() {
        return !minion.isHead();
    }

    @Override
    public void tick() {
        WormAttachmentEntity preceding = minion.getPrecedingSegment();
        if (preceding != null) {
            List<PathNode> history = preceding.getHistoryNodes();
            if (history.size() >= 2) {
                double trailCursor = minion.getSegmentDistance();
                double accumulated = 0;
                Vec3 targetPos = history.getLast().pos();
                for (int index = 0; index < history.size() - 1; index++) {
                    PathNode current = history.get(index);
                    PathNode next = history.get(index + 1);
                    double segmentLength = current.pos().distanceTo(next.pos());
                    if (accumulated + segmentLength >= trailCursor) {
                        float progress = segmentLength > 0.001 ? (float) ((trailCursor - accumulated) / segmentLength) : 0;
                        targetPos = current.pos().lerp(next.pos(), Mth.clamp(progress, 0, 1));
                        break;
                    }
                    accumulated += segmentLength;
                }
                Vec3 toPreceding = preceding.getPos().subtract(targetPos);
                Vec3 direction = toPreceding.lengthSqr() > 0.0001 ? toPreceding.normalize() : minion.getCurrentVelocity();
                Vec3 normal = minion.getCurrentNormal().lerp(preceding.getCurrentNormal(), 0.5f);
                PathNode orientation = minion.getEulerNode(targetPos, direction, normal);
                minion.setPath(new PlannedPath("physics", List.of(orientation)));
                minion.setDesiredRotation(orientation.yaw(), orientation.pitch(), orientation.roll());
            }
        }
    }
}
