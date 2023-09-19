package domain;

import java.sql.Timestamp;

public class Message {
    private final Long messageId;
    private final Long senderId;
    private final Long receiverId;
    private final String content;
    private final Timestamp timestamp;

    public Message(Long messageId, Long senderId, Long receiverId, String content, Timestamp timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!getMessageId().equals(message.getMessageId())) return false;
        if (!getSenderId().equals(message.getSenderId())) return false;
        if (!getReceiverId().equals(message.getReceiverId())) return false;
        if (getContent() != null ? !getContent().equals(message.getContent()) : message.getContent() != null)
            return false;
        return getTimestamp() != null ? getTimestamp().equals(message.getTimestamp()) : message.getTimestamp() == null;
    }

    @Override
    public int hashCode() {
        int result = getMessageId().hashCode();
        result = 31 * result + getSenderId().hashCode();
        result = 31 * result + getReceiverId().hashCode();
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
        return result;
    }
}
