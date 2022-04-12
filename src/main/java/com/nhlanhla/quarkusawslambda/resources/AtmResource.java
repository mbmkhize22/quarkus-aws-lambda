package com.nhlanhla.quarkusawslambda.resources;

import com.nhlanhla.quarkusawslambda.models.Atm;
import com.nhlanhla.quarkusawslambda.models.User;
import com.nhlanhla.quarkusawslambda.repository.AtmRepository;
import com.nhlanhla.quarkusawslambda.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/v1/atm")
public class AtmResource {

    @Inject
    AtmRepository atmRepository;

    @GET
    public Response getAll() {
        List<Atm> obj = atmRepository.listAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return atmRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(Atm u) {
        String guid = UUID.randomUUID().toString().replaceAll("-", "");
        u.setAtmGuid(guid);
        atmRepository.persist(u);
        if(atmRepository.isPersistent(u)) {
            return Response.created(URI.create("created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = atmRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
