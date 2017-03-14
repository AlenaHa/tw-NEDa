package org.elena.entity;

import javax.persistence.*;

/**
 * @author Elena Hardon
 * @date 3/13/17.
 */

@Entity
@Table(name = "suplies")
public class Supplies {

    @Id
    @GeneratedValue
    private int suppliesId;

    @Column(name = "localization_id")
    private int localizationId;

    @Column
    private String supplyName;

    @Column
    private String category;

    @Column
    private String unit;

    @Column
    private int amount;
}
