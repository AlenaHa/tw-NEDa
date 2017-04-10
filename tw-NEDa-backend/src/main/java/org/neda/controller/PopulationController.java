package org.neda.controller;

import org.neda.entity.Population;
import org.neda.repository.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopulationController {


    @Autowired
    private PopulationRepository populationRepository;

    /**
     * Map this method to path from the value
     * We populate an entity to see if the databese works
     * @return string message with some values from the population entity
     */
    @RequestMapping(value = "/population", method = RequestMethod.GET)
    @ResponseBody
    public String itWorks() {
        Population population = new Population();
        population.setLocalizationId(2);
        population.setAgeDistribution("20-30");
        population.setGender("Male");
        population.setDistrict("Brahmaputra");
        population.setMunicipality("Asdqwtbaala");

        populationRepository.save(population);
        return "Population : " + population.getDistrict() + " " + population.getMunicipality();
    }
}
