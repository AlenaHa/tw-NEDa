package org.neda.entity;


import javax.persistence.*;

@Entity
//@Table(name = "location", uniqueConstraints = {@UniqueConstraint(columnNames = {"location_id"})})

@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="location_id")
    private long locationId;

    @Column(name = "district")
    private String district;

    @Column(name="municipality")
    private String municipality;


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
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
