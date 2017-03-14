package org.elena.entity;


import javax.persistence.*;

@Entity
@Table(name = "location", uniqueConstraints = {@UniqueConstraint(columnNames = {"locationId"})})
public class Location {

    @Id
    @GeneratedValue
    private int locationId;
}
