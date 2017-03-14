package org.elena.entity;

import javax.persistence.*;

/**
 * @author Elena Hardon
 * @date 3/13/17.
 */

@Entity
@Table(name = "ong")
public class Ong {

    @Id
    @GeneratedValue
    private int ongId;

    @Column(nullable = false)
    private int localizationId;

    @Column(nullable = false)
    private String ongName;

    @Column
    private String activityType;

    @Column
    private String subactivityType;


}
