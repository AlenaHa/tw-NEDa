package org.elena;

import org.elena.entity.Population;
import org.elena.repository.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopulationController {

    @Autowired
    private PopulationRepository populationRepository;

    @RequestMapping(value = "/works/population", method = RequestMethod.GET)
    public String itWorks() {

        Population population = new Population();
        population.setAgeDistribution("20-30");
        population.setPostEqProgress("not satisfied");
        population.setOngSupport("not satisfied");
        population.setLocalizationId(2);

        populationRepository.save(population);
        return "worked for this population";
    }
}
