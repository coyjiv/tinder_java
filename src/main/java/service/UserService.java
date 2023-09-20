package service;

import domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean create(User user);
    boolean update(User user);
    boolean delete(Integer userId);
    Optional<User> findById(Integer userId);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    List<User> findLikedUsersByUserId(Long userId);

    Optional<User> getNextUserToReact(Long loggedUserId);

    boolean register(User user);
    boolean login(String username, String password);
    boolean logout();
}
