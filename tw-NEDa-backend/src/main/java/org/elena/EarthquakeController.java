package org.elena;


import org.elena.entity.Earthquake;
import org.elena.repository.EarthquakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class EarthquakeController {

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @RequestMapping(value = "/works/earthquake", method = RequestMethod.GET)
    public String itWorks() {

        Earthquake earthquake = new Earthquake();
        earthquake.setLocalizationId(3);
        Date date = new Date(22 / 11 / 2015);
        earthquake.setDate(date);
        earthquake.setDepth(30.0);
        earthquake.setLatitude(79.06);
        earthquake.setLongitude(30.67);
        earthquake.setMagnitude(9.5);

        earthquakeRepository.save(earthquake);

        return "earthquake worked";
    }

}
