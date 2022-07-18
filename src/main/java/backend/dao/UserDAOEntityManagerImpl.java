package backend.dao;


import frontend.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDAOEntityManagerImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
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

//        sessionFactory.getCurrentSession().update(anotherUser);

    }
//    sessionFactory.getCurrentSession().getSession().get(User.class, id);


    @Override
    public void removeUserById(Long id) {

//        sessionFactory.getCurrentSession().delete(findUserById(id));
    }

    @Override
    public void cleanUsersTable() {
//        sessionFactory.getCurrentSession().createSQLQuery("delete from users").executeUpdate();

    }

}
