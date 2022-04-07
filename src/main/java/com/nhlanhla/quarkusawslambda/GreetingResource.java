package com.nhlanhla.quarkusawslambda;

import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/hello")
public class GreetingResource {


    @Inject
    @ConfigProperty(name = "message", defaultValue = "Failed to read property file")
    private String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name")String name) {
        return "Hello RESTEasy " + name;
    }

    @GET
    @Path("/message")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloMessage() {
        return "Hello RESTEasy " + message;
    }
}