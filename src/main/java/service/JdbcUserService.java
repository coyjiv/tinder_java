package service;

import dao.ReactionDao;
import dao.UserDao;
import domain.Reaction;
import domain.User;

import java.util.List;
import java.util.Optional;

public class JdbcUserService implements UserService{
    private final UserDao userDao;
    private final ReactionDao reactionDao;

    public JdbcUserService(UserDao userDao, ReactionDao reactionDao) {
        this.userDao = userDao;
        this.reactionDao = reactionDao;
    }


    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Integer userId) {
        return userDao.delete(userId);
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userDao.findById(userId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findLikedUsersByUserId(Long userId) {
        return userDao.findLikedUsersByUserId(userId);
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    public Optional<User> getNextUserToReact(Long loggedUserId) {
        List<User> allUsers = userDao.findAll();
        List<Reaction> reactionsByUser = reactionDao.findByUserId(loggedUserId);

        for (User user : allUsers) {
            boolean hasReacted = reactionsByUser.stream().anyMatch(reaction -> reaction.getTargetUserId().equals(user.getUserId()));
            if (!hasReacted && !user.getUserId().equals(loggedUserId)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
