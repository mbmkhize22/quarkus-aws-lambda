package com.nhlanhla.quarkusawslambda.resources;

import com.nhlanhla.quarkusawslambda.models.User;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URL;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserResourceTest {
    Logger LOG = Logger.getLogger(UserResourceTest.class.getName());

    @TestHTTPResource
    @TestHTTPEndpoint(UserResource.class)
    URL url;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("Alex");
        user.setLastName("Mkhize");
    }

    @Test
    @Order(3)
    void getAll() {
        LOG.info("start testing getAll users ->  [GET]" + url);
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("size()", is(1));
        LOG.info("done testing getAll user");
    }

    @Test
    @Order(2)
    void getById() {
        LOG.info("start testing getById user -> [GET] " + url + "/1");
       given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get(url + "/{id}", 1)
                .then()
                .statusCode(200)
                .body("firstName", is("Alex"))
                .body("lastName", is("Mkhize"));
        LOG.info("done testing getById");
    }

    @Test
    @Order(1)
    void create() {
        LOG.info("start testing create user -> [POST] " + url);
        given()
                .body(user)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                //.post("/api/v1/user")
                .post(url)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .header("location", url + "/1");
        LOG.info("finish testing create user");
    }

    @Test
    @Order(4)
    void update() {
        LOG.info("start testing update user -> [PUT] " + url);
        user = new User();
        user.setFirstName("Trevor");
        user.setLastName("Noah");
        user.setUserId(1L);
        given()
                .body(user)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .put(url)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(equalTo("successfully updated " + user.getFirstName()));

        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get(url + "/{id}", 1)
                .then()
                .statusCode(200)
                .body("firstName", is(user.getFirstName()))
                .body("lastName", is(user.getLastName()));
        LOG.info("finish testing update user");
    }

    @Test
    @Order(5)
    void delete() {
        LOG.info("start testing delete user -> [DELETE] " + url + "/1");
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .delete(url + "/{id}", 1)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        /**
         * Double checking if it really deleted
         * This will return 404, NOT_FOUND status because user 1 doesn't exists.
         */
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .delete(url + "/{id}", 1)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        LOG.info("done testing delete user");
    }
}