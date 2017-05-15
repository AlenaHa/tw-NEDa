package org.neda.controller;


import java.util.List;

import org.neda.entity.Supplies;
import org.neda.service.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/supplies")
public class SuppliesController {

    @Autowired
    private SuppliesService suppliesService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Supplies> readSupplies(@PathVariable Long id) {
        Supplies supplies = suppliesService.findById(id);
        if (supplies != null) {
            return new ResponseEntity<Supplies>(supplies, HttpStatus.OK);
        } else {
            return new ResponseEntity<Supplies>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Supplies> deleteSupplies(@PathVariable Long id) {
        suppliesService.delete(id);
        return new ResponseEntity<Supplies>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Supplies> createSupplies(@RequestBody Supplies reqSupplies) {
        Supplies savedSupplies = suppliesService.save(reqSupplies);
        return new ResponseEntity<Supplies>(savedSupplies, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Supplies> updateSupplies(@RequestBody Supplies reqSupplies, @PathVariable Long id) {
        if (!id.equals(reqSupplies.getSuppliesId())) {
            return new ResponseEntity<Supplies>(HttpStatus.BAD_REQUEST);
        }
        Supplies savedSupplies = suppliesService.save(reqSupplies);
        return new ResponseEntity<Supplies>(savedSupplies, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Supplies>> getAllSupplies() {
        List<Supplies> all = suppliesService.findAll();
        return new ResponseEntity<List<Supplies>>(all, HttpStatus.OK);
    }

}
