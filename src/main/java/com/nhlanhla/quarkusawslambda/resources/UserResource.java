package com.nhlanhla.quarkusawslambda.resources;

import com.nhlanhla.quarkusawslambda.models.User;
import com.nhlanhla.quarkusawslambda.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/api/v1/user")
public class UserResource {
    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());

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
            URI userUri = UriBuilder.fromResource(UserResource.class)
                    .path("/" + u.getUserId()).build();
            return Response.created(userUri).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Transactional
    public Response update(User u) {
        try {
            User user = userRepository.findById(u.getUserId());
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            user.setLastName(u.getLastName());
            user.setFirstName(u.getFirstName());
            userRepository.persist(user);
            URI userUri = UriBuilder.fromResource(UserResource.class)
                    .path("/" + u.getUserId()).build();
            return Response.ok("successfully updated " + user.getFirstName()).build();
        }catch (Exception e) {
            LOG.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = userRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
