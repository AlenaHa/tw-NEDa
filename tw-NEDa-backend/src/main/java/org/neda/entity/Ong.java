package org.neda.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Make Ong table with related columns
 */
@Entity
@Table(name = "ong")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "ong_id")
    private Long ongId;

    @Column(name = "ong_name")
    private String ongName;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "activity_subtype")
    private String subactivityType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Ong_Location", joinColumns = {@JoinColumn(name = "ong_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private List<Location> locationListOng = new ArrayList<Location>();

    public Long getOngId() {
        return ongId;
    }

    public void setOngId(long ongId) {
        this.ongId = ongId;
    }

    public String getOngName() {
        return ongName;
    }

    public void setOngName(String ongName) {
        this.ongName = ongName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getSubactivityType() {
        return subactivityType;
    }

    public void setSubactivityType(String subactivityType) {
        this.subactivityType = subactivityType;
    }

    public List<Location> getLocationListOng() {
        return locationListOng;
    }

    public void setLocationListOng(List<Location> locationListOng) {
        this.locationListOng = locationListOng;
    }

}
