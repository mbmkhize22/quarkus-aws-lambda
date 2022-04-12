package com.nhlanhla.quarkusawslambda.resources;

import com.nhlanhla.quarkusawslambda.models.User;
import com.nhlanhla.quarkusawslambda.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/v1/user")
public class UserResource {

    @Inject
    UserRepository userRepository;

    @GET
    public Response getAll() {
        List<User> obj = userRepository.listAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return userRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(User u) {
        String guid = UUID.randomUUID().toString().replaceAll("-", "");
        u.setUserGuid(guid);
        userRepository.persist(u);
        if(userRepository.isPersistent(u)) {
            return Response.created(URI.create("created")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = userRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
