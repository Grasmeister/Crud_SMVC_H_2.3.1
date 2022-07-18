package backend.dao;


import frontend.model.User;

import java.util.List;

public interface UserDAO {
    void add(User user);

    List<User> listUsers();

    void updateUserById(Long id, User user);

    void removeUserById(Long id);

    User findUserById(Long id);

    void cleanUsersTable();
}
