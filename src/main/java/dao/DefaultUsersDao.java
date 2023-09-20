package dao;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public class DefaultUsersDao implements DefaultUsersDaoInterface {

    List<User> users = new ArrayList<>();


    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }
}
