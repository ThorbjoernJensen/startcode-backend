package facades;

import entities.Bookmark;
import entities.City;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public void populate() {
        EntityManager em = emf.createEntityManager();
        User user = new User("user", "As123456");
        User admin = new User("admin", "JK123456");
        User both = new User("user_admin", "DQ123456");

        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test"))
            throw new UnsupportedOperationException("You have not changed the passwords");
        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();
        em.close();

//        System.out.println("PW: " + user.getUserPass());
//        System.out.println("Testing user with OK password: " + user.verifyPassword("As123456"));
//        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
        System.out.println("Created TEST Users");


    }


    public User createUser(User user) {
        User createdUser = user;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role userRole = new Role("user");
        user.addRole(userRole);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return createdUser;
    }

    public Bookmark createBookmark(String userName, long cityId) {
        Bookmark createdBookmark;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        City city = em.find(City.class, cityId);

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
        User user = query.setParameter("userName", userName).getSingleResult();

        Bookmark bookmark = new Bookmark(user, city);

        em.persist(bookmark);
        em.getTransaction().commit();
        em.close();

        return bookmark;
    }

    public City createCity(String cityName) {
        EntityManager em = emf.createEntityManager();
        City newCity = null;
        em.getTransaction().begin();

        TypedQuery<City> query = em.createQuery("SELECT c FROM City c WHERE c.cityName = :cityName", City.class)
                .setParameter("cityName", cityName);
        if (query.getResultList().size() == 0) {
            newCity = new City(cityName);
            em.persist(newCity);
        } else {
            newCity = query.getSingleResult();
        }
        em.getTransaction().commit();
        em.close();

        return newCity;

    }


    public User getUserByUserName(String userName) {
        EntityManager em = emf.createEntityManager();
        User user;
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class)
                .setParameter("userName", userName);
        user = query.getSingleResult();
        em.getTransaction().commit();
        em.close();

        return user;

    }
}
