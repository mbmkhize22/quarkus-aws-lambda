package com.nhlanhla.quarkusawslambda.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Atm {
    @Id
    @GeneratedValue
    private Long atmId;
    private String atmGuid;
    private String atmName;
    private String address;
    private LocalDate dateAdded;
    private LocalDate dateUpdated;

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

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @PrePersist
    public void prePersist() {
        this.dateAdded = LocalDate.now();
        this.dateUpdated = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateUpdated = LocalDate.now();
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
