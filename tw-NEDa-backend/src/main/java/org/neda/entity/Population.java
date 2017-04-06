package org.neda.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "population", uniqueConstraints = {@UniqueConstraint(columnNames = {"population_id"})})
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "population_id")
    private long id;

    @Column(name = "p_location_id")
    @NotNull
    private long localizationId;

    @Column(name = "age_distribution")
    private String ageDistribution;

    @Column(name="gender")
    private String gender;

    @Column(name="district")
    private String district;

    @Column(name="municipality")
    private String municipality;

    //nu avem aceste campuri in tabela
/*    @Column(name = "ong_support")
    private String ongSupport;

    @Column(name = "progress")
    private String postEqProgress;
*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    /*
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
    */

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
}
