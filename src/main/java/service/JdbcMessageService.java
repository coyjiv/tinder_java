package service;

import dao.MessageDao;
import domain.Message;

import java.util.List;
import java.util.Optional;

public class JdbcMessageService implements MessageService{
    private final MessageDao messageDao;

    public JdbcMessageService(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    @Override
    public boolean create(Message message) {
        return messageDao.create(message);
    }

    @Override
    public Optional<Message> findById(Integer messageId) {
        return messageDao.findById(messageId);
    }

    @Override
    public List<Message> findAllBySenderId(Integer senderId) {
        return messageDao.findAllBySenderId(senderId);
    }

    @Override
    public List<Message> findAllByReceiverId(Integer receiverId) {
        return messageDao.findAllByReceiverId(receiverId);
    }

    @Override
    public List<Message> findAllBetweenUsers(Integer userId1, Integer userId2) {
        return messageDao.findAllBetweenUsers(userId1, userId2);
    }

    @Override
    public boolean delete(Integer messageId) {
        return messageDao.delete(messageId);
    }

    @Override
    public boolean sendMessage(String message, Integer userId1, Integer userId2) {
        return messageDao.create(new Message(null, (long) userId1, (long) userId2, message, null));
    }
}
