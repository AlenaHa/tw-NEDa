package org.elena;


import org.elena.entity.Earthquake;
import org.elena.repository.EarthquakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EarthquakeController {

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @RequestMapping(value = "/works", method = RequestMethod.GET)
    public String itWorks() {

        Earthquake earthquake = new Earthquake();
        earthquake.setName("Cutremuru din 77");

        earthquakeRepository.save(earthquake);

        return "it worked";
    }

}
