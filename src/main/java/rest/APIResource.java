package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import facades.APIFacade;
import facades.Populator;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private static Populator populator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String welcomeGreeting() {
        return "{\"msg\":\"Hello boatsman\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("owner")
//    @RolesAllowed({"user"})
    public String getAllOwners() {
        Set<OwnerDTO> ownerDTOSet = FACADE.getAllOwners();
        return GSON.toJson(ownerDTOSet);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("harbour")
//    @RolesAllowed({"user"})
    public String getAllHarbours() {
        Set<HarbourDTO> harbourDTOSet = FACADE.getAllHarbours();
//        List<HarbourDTO> harbourDTOList = new ArrayList<>();
//        harbourDTOList.addAll(harbourDTOSet);
//        return GSON.toJson(harbourDTOList);
        return GSON.toJson(harbourDTOSet);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("boat")
//    @RolesAllowed({"user"})
    public String getAllBoats() {
        Set<BoatDTO> boatDTOSet = FACADE.getAllBoats();
        List<BoatDTO> boatDTOList = new ArrayList<>();
        boatDTOList.addAll(boatDTOSet);
        return GSON.toJson(boatDTOList);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("populate")
//    @RolesAllowed({"admin"})
    public String populateDB() {
        populator.populate();
        return "{\"msg\":\"DB populated\"}";

    }


}

