package com.nhlanhla.quarkusawslambda.repository;

import com.nhlanhla.quarkusawslambda.models.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
