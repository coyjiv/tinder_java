package dao;

import domain.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDao {
    boolean create(Message message);
    Optional<Message> findById(Integer messageId);
    List<Message> findAllBySenderId(Integer senderId);
    List<Message> findAllByReceiverId(Integer receiverId);
    List<Message> findAllBetweenUsers(Integer userId1, Integer userId2);
    boolean delete(Integer messageId);
}
