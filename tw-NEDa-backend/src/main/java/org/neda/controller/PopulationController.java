package org.neda.controller;

import java.util.List;

import org.neda.entity.Population;
import org.neda.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cami on 2017-06-06.
 */

@RestController
@RequestMapping(value = "/population")
@CrossOrigin("*")
public class PopulationController {

    @Autowired
    private PopulationService populationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Population> readPopulation(@PathVariable Long id) {
        Population population = populationService.findById(id);
        if (population != null) {
            return new ResponseEntity<Population>(population, HttpStatus.OK);
        } else {
            return new ResponseEntity<Population>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Population> deletePopulation(@PathVariable Long id) {
        populationService.delete(id);
        return new ResponseEntity<Population>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Population> createPopulation(@RequestBody Population reqPopulation) {
        Population savedPopulation = populationService.save(reqPopulation);
        return new ResponseEntity<Population>(savedPopulation, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Population> updatePopulation(@RequestBody Population reqPopulation, @PathVariable Long id) {
        if (!id.equals(reqPopulation.getPopulationId())) {
            return new ResponseEntity<Population>(HttpStatus.BAD_REQUEST);
        }
        Population savedPopulation = populationService.save(reqPopulation);
        return new ResponseEntity<Population>(savedPopulation, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Population>> getAllPopulation() {
        List<Population> all = populationService.findAll();
        return new ResponseEntity<List<Population>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/female/{district}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getPopulationFemaleNumber(@PathVariable String district) {
        Integer list = this.populationService.getFemaleNumber(district);

        return new ResponseEntity<Integer>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/male/{district}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getPopulationMaleNumber(@PathVariable String district) {
        Integer list = this.populationService.getMaleNumber(district);

        return new ResponseEntity<Integer>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/age/15/{district}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAge15(@PathVariable String district) {
        Integer list = this.populationService.getAgeNumber15_24(district);

        return new ResponseEntity<Integer>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/age/25/{district}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAge25(@PathVariable String district) {
        Integer list = this.populationService.getAgeNumber25_39(district);

        return new ResponseEntity<Integer>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/age/40/{district}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAge40(@PathVariable String district) {
        Integer list = this.populationService.getAgeNumber40_54(district);

        return new ResponseEntity<Integer>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/age/55/{district}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAge55(@PathVariable String district) {
        Integer list = this.populationService.getAgeNumber55(district);

        return new ResponseEntity<Integer>(list, HttpStatus.OK);

    }


}
