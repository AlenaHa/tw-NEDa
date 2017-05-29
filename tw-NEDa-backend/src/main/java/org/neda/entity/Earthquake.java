package org.neda.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Make Earthquake table with the related columns
 */
@Entity
@Table(name = "earthquake")
public class Earthquake {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "e_id")
    private Long earthquakeId;

    @Column(name = "e_location_id")
    private Long localizationId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "eq_depth")
    private Double depth;

    @Column(nullable = false, name = "magnitude")
    private Double magnitude;

    @Column(name = "happened_on")
    private Date happenedOn;


    public Long getEarthquakeId() {
        return earthquakeId;
    }

    public void setEarthquakeId(Long earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    public long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(long localizationId) {
        this.localizationId = localizationId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Date getHappenedOn() {
        return happenedOn;
    }

    public void setHappenedOn(Date happenedOn) {
        this.happenedOn = happenedOn;
    }
}

