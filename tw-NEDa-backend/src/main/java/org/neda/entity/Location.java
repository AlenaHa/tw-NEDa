package org.neda.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Make Location table with related columns
 */
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "location_id")
    private Long locationId;

    @Column(name = "district")
    private String district;

    @Column(name = "municipality")
    private String municipality;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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
