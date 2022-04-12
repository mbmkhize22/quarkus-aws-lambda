package com.nhlanhla.quarkusawslambda.repository;

import com.nhlanhla.quarkusawslambda.models.Task;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {
}
