package org.neda.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "earthquake")
public class Earthquake {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int localizationId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double depth;

    @Column(nullable = false)
    private Double magnitude;

    @Column
    private Date happenedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(int localizationId) {
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

