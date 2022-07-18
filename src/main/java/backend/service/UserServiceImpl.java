package backend.service;

import backend.dao.UserDAO;
import backend.dao.UserDAOEntityManagerImpl;
import backend.dao.UserDAOHebirnateImpl;
import frontend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    UserDAOHebirnateImpl UserDAOEntityManagerImpl
//    @Qualifier("UserDAOHebirnateImpl")
    private UserDAOHebirnateImpl userDao;

    @Autowired
    public UserServiceImpl(UserDAOHebirnateImpl userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void updateUserById(Long id, User updateuser) {
        userDao.updateUserById(id, updateuser);
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }


}
