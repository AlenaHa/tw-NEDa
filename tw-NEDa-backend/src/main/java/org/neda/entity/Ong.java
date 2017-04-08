package org.neda.entity;

import javax.persistence.*;

/**
 * Make Ong table with related columns
 */
@Entity
@Table(name = "ong")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "ong_id")
    private long ongId;

    @Column(name="o_location_id")
    private long localizationId;

    @Column(name="ong_name")
    private String ongName;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "activity_subtype")
    private String subactivityType;

    public long getOngId() {
        return ongId;
    }

    public void setOngId(long ongId) {
        this.ongId = ongId;
    }

    public long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(long localizationId) {
        this.localizationId = localizationId;
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
}
