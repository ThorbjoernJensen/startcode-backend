package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class APIFacadeTest {
    private static EntityManagerFactory emf;
    private static APIFacade facade;

    private Owner o1, o2, o3;
    private OwnerDTO o1DTO, o2DTO, o3DTO;
    private Harbour h1, h2, h3;
    private HarbourDTO h1DTO, h2DTO, h3DTO;
    private Boat b1, b2, b3;
    private BoatDTO b1DTO, b2DTO, b3DTO;

    public APIFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = APIFacade.getFacadeInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.createQuery("delete from Boat").executeUpdate();
            em.createQuery("delete from Harbour").executeUpdate();
            em.createQuery("delete from Owner").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();


            Owner o1 = new Owner("Skipper Bænt", "Persillehaven 40", "38383838");
            Owner o2 = new Owner("Skipper Niels", "Persillehaven 42", "39393939");
            Owner o3 = new Owner("Skipper Bente", "Persillehaven 38", "40404040");

            Harbour h1 = new Harbour("Melsted Havn", "Melsted byvej", 8);
            Harbour h2 = new Harbour("Nexø Havn", "Hovedvejen", 14);
            Harbour h3 = new Harbour("Aakirkeby Havn", "Melsted byvej", 32);

            Boat b1 = new Boat("Boatmaster", "speeder", "Martha", "https://img.fruugo.com/product/8/58/278398588_max.jpg");
            Boat b2 = new Boat("Das Boot", "submarine", "Aase", "https://cdn.shopify.com/s/files/1/0626/0562/3537/products/31S6ddXfLmL.jpg?v=1659358008");
            Boat b3 = new Boat("Hanger", "supersize", "King Lincoln", "https://upload.wikimedia.org/wikipedia/commons/2/2d/USS_Nimitz_%28CVN-68%29.jpg");

            b1.addOwner(o1);
            b2.addOwner(o1);
            b2.addOwner(o2);
            b3.addOwner(o3);
            b3.addOwner(o3);

            h1.addBoat(b1);
            h3.addBoat(b2);
            h3.addBoat(b3);

            em.persist(o1);
            em.persist(o2);
            em.persist(o3);
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    @Test
    public void getOwnerCount() throws Exception {
        assertEquals(3, facade.getOwnerCount(), "Expects 3 rows in the database");
    }

}
