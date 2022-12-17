package facades;

import dtos.HarbourDTO;
import dtos.OwnerDTO;
import dtos.RenameMeDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class APIFacade {
    private static APIFacade instance;
    private static EntityManagerFactory emf;

    private APIFacade() {
    }

    public static APIFacade getFacadeInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new APIFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //    public Boat create(RenameMeDTO rm){
    public Owner create(Owner owner) {
        Owner newOwner = owner;

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newOwner);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
//        return new RenameMeDTO(rme);
        return owner;
    }

    public Owner getOwnerById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Owner owner = em.find(Owner.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
//        return new RenameMeDTO(rm);
        return owner;
    }

    public long getOwnerCount() {
        EntityManager em = getEntityManager();
        try {
            long ownerCount = (long) em.createQuery("SELECT COUNT(o) FROM Owner o").getSingleResult();
            return ownerCount;
        } finally {
            em.close();
        }
    }

    public List<OwnerDTO> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> owners = query.getResultList();
        List<OwnerDTO> ownerDTOList = OwnerDTO.makeDTOList(owners);
//        return RenameMeDTO.getDtos(rms);
        return ownerDTOList;
    }

    public List<HarbourDTO> getAllHarbours() {
        EntityManager em = getEntityManager();
        TypedQuery<Harbour> query = em.createQuery("SELECT h FROM Harbour h", Harbour.class);
        List<Harbour> harbours = query.getResultList();
        List<HarbourDTO> harbourDTOList = HarbourDTO.makeDTOList(harbours);
        System.out.println("fra facade: " +harbourDTOList.toString());
        return harbourDTOList;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        APIFacade facade = getFacadeInstance(emf);


    }

}



