package org.neda.entity;

import javax.persistence.*;


@Entity
@Table(name = "supplies")
public class Supplies {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="supplies_id")
    private long suppliesId;

    @Column(name = "s_name")
    private String supplyName;

    @Column(name = "s_category")
    private String category;

    @Column(name = "s_unit")
    private String unit;

    @Column(name = "s_amount")
    private String amount;


    public long getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(long suppliesId) {
        this.suppliesId = suppliesId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
