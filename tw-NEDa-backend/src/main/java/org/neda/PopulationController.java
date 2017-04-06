package org.neda;

import org.neda.entity.Population;
import org.neda.repository.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopulationController {

    private PopulationRepository populationRepository;

    @Autowired
    public PopulationController(PopulationRepository populationRepository){
        this.populationRepository = populationRepository;
    }

    @RequestMapping(value = "/population", method = RequestMethod.GET)
    @ResponseBody
    public String itWorks() {

        Population population = new Population();
        population.setLocalizationId(2);
        population.setAgeDistribution("20-30");
        population.setGender("Male");
        population.setDistrict("Brahmaputra");
        population.setMunicipality("Asdqwtbaala");
        // population.setPostEqProgress("not satisfied");
       // population.setOngSupport("not satisfied");

        populationRepository.save(population);
        return "Population : " + population.getDistrict() + " " + population.getMunicipality();
    }
}
