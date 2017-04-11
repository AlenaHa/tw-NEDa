package org.neda.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "supplies")
public class Supplies {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "supplies_id")
    private Long suppliesId;

    @Column(name = "s_name")
    private String supplyName;

    @Column(name = "s_category")
    private String category;

    @Column(name = "s_unit")
    private String unit;

    @Column(name = "s_amount")
    private String amount;

    @ManyToMany
    @JoinTable(name = "Supplies_Location", joinColumns = {@JoinColumn(name = "supplies_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private List<Location> locationListSupplies = new ArrayList<Location>();

    public Long getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(Long suppliesId) {
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

    public List<Location> getLocationListSupplies() {
        return locationListSupplies;
    }

    public void setLocationListSupplies(List<Location> locationListSupplies) {
        this.locationListSupplies = locationListSupplies;
    }
}
