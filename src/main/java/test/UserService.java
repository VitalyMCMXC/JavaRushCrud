package test;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
@Transactional

public class UserService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<User> getUsersList(int page, int itemsCount) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");

        int begin = (page - 1) * itemsCount;
        int end = (page - 1) * itemsCount + (itemsCount - 1) + 1;

        if (end > query.list().size())
            end = query.list().size();

        return query.list().subList(begin, end);
    }

    public User get( Integer id ) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
    }

    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        User existingUser = (User) session.get(User.class, user.getId());

        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setIsAdmin(user.getisAdmin());

        session.save(existingUser);
    }

    public List<User> search(String name) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("select * from users where name like '%" + name + "%'");
        return query.addEntity(User.class).list();
    }

    public int tableSize(){
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from User");
        return q.list().size();
    }
}
