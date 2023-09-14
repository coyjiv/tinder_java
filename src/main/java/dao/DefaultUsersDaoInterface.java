package dao;

import domain.User;

import java.util.List;

public interface DefaultUsersDaoInterface {
public List<User> getAll();
public User findById(int id);
}
