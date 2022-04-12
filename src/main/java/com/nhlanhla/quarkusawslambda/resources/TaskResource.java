package com.nhlanhla.quarkusawslambda.resources;

import com.nhlanhla.quarkusawslambda.models.Task;
import com.nhlanhla.quarkusawslambda.models.User;
import com.nhlanhla.quarkusawslambda.repository.TaskRepository;
import com.nhlanhla.quarkusawslambda.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Path("/api/v1/task")
public class TaskResource {
    Logger LOG = Logger.getLogger(TaskResource.class.getName());

    @Inject
    TaskRepository taskRepository;

    @GET
    public Response getAll() {
        List<Task> obj = taskRepository.listAll();
        return Response.ok(obj).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        return taskRepository.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(Task u) {
        try {
            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            u.setTaskGuid(guid);
            LOG.info(u.toString());
            taskRepository.persist(u);
            taskRepository.flush();
            if (taskRepository.isPersistent(u)) {
                return Response.created(URI.create("created")).build();
            }
        }catch (Exception e) {
            LOG.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = taskRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
