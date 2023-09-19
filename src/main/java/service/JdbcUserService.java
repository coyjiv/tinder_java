package service;

import dao.UserDao;
import domain.User;

import java.util.List;
import java.util.Optional;

public class JdbcUserService implements UserService{
    private final UserDao userDao;

    public JdbcUserService(UserDao userDao) {
        this.userDao = userDao;
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
}
