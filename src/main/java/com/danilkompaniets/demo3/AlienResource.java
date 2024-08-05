package com.danilkompaniets.demo3;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Objects;

@Path("aliens")
public class AlienResource {

    AlienRepository repository;

    public AlienResource() {
        repository = new AlienRepository();
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alien> getAlien() {
        return repository.getAliens();
    }

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Alien createAlien(Alien alien) {
        repository.addAlien(alien);
        return alien;
    }

    @Path("get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Alien getOneAlien(@PathParam("id") int id) {
        return repository.getAlien(id);
    }

    @Path("update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateAlien(Alien alien, @PathParam("id") int id) {
        repository.updateAlien(alien, id);
    }

    @Path("update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAlien(Alien alien, @PathParam("id") int id) {
        repository.deleteAlien(id);
    }
}
