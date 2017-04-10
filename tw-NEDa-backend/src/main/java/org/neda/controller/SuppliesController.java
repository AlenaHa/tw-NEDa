package org.neda.controller;

import org.neda.entity.Supplies;
import org.neda.repository.SuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SuppliesController {

    @Autowired
    private SuppliesRepository suppliesRepository;

    /**
     * Map this method to path from the value
     * We populate an entity to see if the databese works
     * @return string message with some values from the supplies entity
     */
    @RequestMapping(value = "/supplies", method = RequestMethod.GET)
    public String insertSupplies() {

        Supplies supplies = new Supplies();
        supplies.setAmount("10 Kg");
        supplies.setSupplyName("Bors");
        supplies.setCategory("Food");
        supplies.setUnit("Unit");

        suppliesRepository.save(supplies);
        return "Supplies Works : " + supplies.getCategory()+"-> "
                                + supplies.getSupplyName();
    }
}