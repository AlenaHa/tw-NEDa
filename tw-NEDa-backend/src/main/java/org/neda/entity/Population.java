package org.neda.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Make Population table with related columns
 */
@Entity
@Table(name = "population")
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "population_id")
    private long populationId;

    @Column(name = "p_location_id")
    @NotNull
    private long localizationId;

    @Column(name = "age_distribution")
    private String ageDistribution;

    @Column(name = "gender")
    private String gender;

    public long getPopulationId() {
        return populationId;
    }

    public void setPopulationId(long populationId) {
        this.populationId = populationId;
    }

    public long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(long localizationId) {
        this.localizationId = localizationId;
    }

    public String getAgeDistribution() {
        return ageDistribution;
    }

    public void setAgeDistribution(String ageDistribution) {
        this.ageDistribution = ageDistribution;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
