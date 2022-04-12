package com.nhlanhla.quarkusawslambda.repository;

import com.nhlanhla.quarkusawslambda.models.Atm;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AtmRepository implements PanacheRepository<Atm> {
}
