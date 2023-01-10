package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class APIFacade {
    private static APIFacade instance;
    private static EntityManagerFactory emf;

    private APIFacade() {
    }


    public static APIFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new APIFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public OwnerDTO getOwnerById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Owner owner = em.find(Owner.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
//        return new RenameMeDTO(rm);
            return new OwnerDTO(owner);
        } finally {
            em.close();
        }
    }

    public long getOwnerCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long ownerCount = (long) em.createQuery("SELECT COUNT(o) FROM Owner o").getSingleResult();
            return ownerCount;
        } finally {
            em.close();
        }
    }

    public Set<OwnerDTO> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
            List<Owner> owners = query.getResultList();
            Set<OwnerDTO> ownerDTOList = OwnerDTO.makeDTOSet(owners);
//        return RenameMeDTO.getDtos(rms);
            return ownerDTOList;
        } finally {
            em.close();
        }
    }

    public Set<HarbourDTO> getAllHarbours() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Harbour> query = em.createQuery("SELECT h FROM Harbour h", Harbour.class);
            List<Harbour> harbours = query.getResultList();
            harbours.forEach(harbour -> harbour.getBoats().forEach(boat -> System.out.println("boat:" + boat.getId())));
//        List<HarbourDTO> harbourDTOList = HarbourDTO.makeDTOSet(harbours);
            Set<HarbourDTO> harbourDTOList = HarbourDTO.makeDTOSet(harbours);
            System.out.println("fra facade: ");
            harbourDTOList.forEach(harbourDTO -> System.out.println(harbourDTO.toString()));
            em.getTransaction().commit();
            return harbourDTOList;
        } finally {
            em.close();
        }
    }

    public Set<HarbourDTO> getAllHarbours2() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Harbour> query = em.createQuery("Select h From Harbour h LEFT JOIN FETCH h.boats", Harbour.class);
//            ist<Author> authors = em.createQuery("SELECT a FROM Author a LEFT JOIN FETCH a.books", Author.class).getResultList();
            List<Harbour> harbourList = query.getResultList();
            harbourList.forEach(harbour -> harbour.getBoats().forEach(boat -> System.out.println("boat:" + boat.getId())));
            return HarbourDTO.makeDTOSet(harbourList);
        } finally {
            em.close();
        }
    }


    public Set<BoatDTO> getAllBoats() {
        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Boat> query = em.createQuery("SELECT b FROM Boat b", Boat.class);
            List<Boat> boatList = query.getResultList();
            Set<BoatDTO> boatDTOSet = BoatDTO.makeDTOSet(boatList);
            return boatDTOSet;
        } finally {
            em.close();
        }
    }

    public Owner create(Owner owner) {
        Owner newOwner = owner;

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newOwner);
            em.getTransaction().commit();
            return owner;
        } finally {
            em.close();
        }
//        return new RenameMeDTO(rme);
    }

    public BoatDTO createBoat(BoatDTO newBoatDTO) {
        Boat newBoat = new Boat(newBoatDTO);
        Harbour harbour;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            harbour = em.find(Harbour.class, newBoat.getHarbour().getId());
            harbour.addBoat(newBoat);

            em.persist(newBoat);
            em.getTransaction().commit();
            return new BoatDTO(newBoat);
        } finally {
            em.close();
            System.out.println("vi nåede til em close i create boat");
        }

//        em.getTransaction().begin();
//        updateAddress(newPerson, em);
//        newPerson.getPhone().forEach(em::persist);
//        em.persist(newPerson);
//        em.getTransaction().commit();
//        return newPerson;

    }
}



