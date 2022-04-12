package com.nhlanhla.quarkusawslambda.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmId;
    private String atmGuid;
    private String atmName;
    private String address;
    private LocalDateTime dateAdded;
    private LocalDateTime dateUpdated;

    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
    }

    public String getAtmGuid() {
        return atmGuid;
    }

    public void setAtmGuid(String atmGuid) {
        this.atmGuid = atmGuid;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Atm{" +
                "atmId=" + atmId +
                ", atmGuid='" + atmGuid + '\'' +
                ", atmName='" + atmName + '\'' +
                ", address='" + address + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
