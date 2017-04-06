package org.neda;


import org.neda.entity.Earthquake;
import org.neda.repository.EarthquakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class EarthquakeController {

    private EarthquakeRepository earthquakeRepository;


    @Autowired
    public EarthquakeController(EarthquakeRepository earthquakeRepository){
        this.earthquakeRepository = earthquakeRepository;
    }

    @RequestMapping(value = "/earthquake", method = RequestMethod.GET)
    @ResponseBody
    public String itWorks() {

        Earthquake earthquake = new Earthquake();
        earthquake.setLocalizationId(3);
        Date date = new Date(22 / 11 / 2015);
        earthquake.setHappenedOn(date);
        earthquake.setDepth(30.0);
        earthquake.setLatitude(79.06);
        earthquake.setLongitude(30.67);
        earthquake.setMagnitude(9.5);

        earthquakeRepository.save(earthquake);

        return "Earthquake : " + earthquake.getLongitude() + " " + earthquake.getLatitude();
    }

}
