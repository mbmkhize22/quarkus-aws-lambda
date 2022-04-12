package com.nhlanhla.quarkusawslambda.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskGuid;
    private String region;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private LocalDateTime dateAdded;
    private LocalDateTime dateUpdated;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskGuid() {
        return taskGuid;
    }

    public void setTaskGuid(String taskGuid) {
        this.taskGuid = taskGuid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @PrePersist
    public void prePersist() {
        this.dateAdded = LocalDateTime.now();
        this.dateUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateUpdated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskGuid='" + taskGuid + '\'' +
                ", region='" + region + '\'' +
                ", user=" + user +
                ", dateAdded=" + dateAdded +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
