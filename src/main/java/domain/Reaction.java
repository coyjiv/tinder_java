package domain;

import java.sql.Timestamp;

public class Reaction {
    private final Long reactionId;
    private final Long userId;
    private final Long targetUserId;
    private final String type;
    private final Timestamp timestamp;

    public Reaction(Long reactionId, Long userId, Long targetUserId, String type, Timestamp timestamp) {
        this.reactionId = reactionId;
        this.userId = userId;
        this.targetUserId = targetUserId;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Long getReactionId() {
        return reactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public String getType() {
        return type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reaction reaction = (Reaction) o;

        if (!getReactionId().equals(reaction.getReactionId())) return false;
        if (!getUserId().equals(reaction.getUserId())) return false;
        if (!getTargetUserId().equals(reaction.getTargetUserId())) return false;
        if (getType() != null ? !getType().equals(reaction.getType()) : reaction.getType() != null) return false;
        return getTimestamp() != null ? getTimestamp().equals(reaction.getTimestamp()) : reaction.getTimestamp() == null;
    }

    @Override
    public int hashCode() {
        int result = getReactionId().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + getTargetUserId().hashCode();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
        return result;
    }
}
