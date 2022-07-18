package backend.dao;


import backend.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAOEntityManagerImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User updateuser) {
        User anotherUser = findUserById(id);

        anotherUser.setName(updateuser.getName());
        anotherUser.setLastName(updateuser.getLastName());
        anotherUser.setAge(updateuser.getAge());
        anotherUser.setHeight(updateuser.getHeight());
        anotherUser.setWeight(updateuser.getWeight());

        entityManager.merge(anotherUser);


    }


    @Override
    @Transactional
    public void removeUserById(Long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    @Transactional
    public void cleanUsersTable() {
//        sessionFactory.getCurrentSession().createSQLQuery("delete from users").executeUpdate();

    }

}
