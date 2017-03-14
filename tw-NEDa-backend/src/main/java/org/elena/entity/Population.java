package org.elena.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "population", uniqueConstraints = {@UniqueConstraint(columnNames = {"population_id"})})
public class Population {

    @Id
    @GeneratedValue
    @Column(name = "population_id")
    private int id;

    @Column(name = "localization_id")
    @NotNull
    private int localizationId;

    @Column(name = "age_range")
    private String ageDistribution;

    @Column(name = "ong_support")
    private String ongSupport;

    @Column(name = "progress")
    private String postEqProgress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(int localizationId) {
        this.localizationId = localizationId;
    }

    public String getAgeDistribution() {
        return ageDistribution;
    }

    public void setAgeDistribution(String ageDistribution) {
        this.ageDistribution = ageDistribution;
    }

    public String getOngSupport() {
        return ongSupport;
    }

    public void setOngSupport(String ongSupport) {
        this.ongSupport = ongSupport;
    }

    public String getPostEqProgress() {
        return postEqProgress;
    }

    public void setPostEqProgress(String postEqProgress) {
        this.postEqProgress = postEqProgress;
    }
}
