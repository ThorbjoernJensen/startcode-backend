package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDTO;
import entities.Owner;
import facades.APIFacade;
import facades.Populator;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.DeclareRoles;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@Path("boat")
@DeclareRoles({"user", "admin"})
public class APIResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private static final APIFacade FACADE = APIFacade.getFacadeInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static Populator POPULATOR;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello boatsman\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("owner")
    public String getAllOwners() {
        List<OwnerDTO> ownerDTOList = FACADE.getAllOwners();
        return GSON.toJson(ownerDTOList);

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("populate")
//    @RolesAllowed({"admin"})
    public String populateDB() {
        POPULATOR.populate();
        return "{\"msg\":\"DB populated\"}";


//        try {
//            TypedQuery<User> query = em.createQuery ("select u from User u",entities.User.class);
//            List<User> users = query.getResultList();
//            return "[" + users.size() + "]";
//        } finally {
//            em.close();
//        }
    }
}

