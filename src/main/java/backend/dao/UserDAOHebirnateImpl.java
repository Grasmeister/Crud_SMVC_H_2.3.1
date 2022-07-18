package backend.dao;


import backend.model.User;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

//@Repository
public class UserDAOHebirnateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    //    @Autowired
    public UserDAOHebirnateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }


    @Override
    public void updateUserById(Long id, User updateuser) {
        User anotherUser = findUserById(id);

        anotherUser.setName(updateuser.getName());
        anotherUser.setLastName(updateuser.getLastName());
        anotherUser.setAge(updateuser.getAge());
        anotherUser.setHeight(updateuser.getHeight());
        anotherUser.setWeight(updateuser.getWeight());

        sessionFactory.getCurrentSession().update(anotherUser);

    }
//    sessionFactory.getCurrentSession().getSession().get(User.class, id);

    @Override
    public User findUserById(Long id) {

        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    public void removeUserById(Long id) {

        sessionFactory.getCurrentSession().delete(findUserById(id));
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory.getCurrentSession().createSQLQuery("delete from users").executeUpdate();

    }

}
