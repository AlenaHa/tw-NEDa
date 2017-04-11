package org.neda.controller;


import org.neda.entity.Earthquake;
import org.neda.repository.EarthquakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;


@RestController
public class EarthquakeController {


    @Autowired
    private EarthquakeRepository earthquakeRepository;


    /**
     * Map this method to path from the value
     * We populate an entity to see if the databese works
     *
     * @return string message with some values from the earthquake entity
     */
    @RequestMapping(value = "/earthquake", method = RequestMethod.GET)
    @ResponseBody
    public String insertEarthquake() {

        Earthquake earthquake = new Earthquake();
        earthquake.setLocalizationId(3);

        Calendar myDate = Calendar.getInstance();
        myDate.set(Calendar.YEAR, 1988);
        myDate.set(Calendar.MONTH, Calendar.JANUARY);
        myDate.set(Calendar.DAY_OF_MONTH, 1);
        Date dateRepresentation = myDate.getTime();

        earthquake.setHappenedOn(dateRepresentation);
        earthquake.setDepth(30.0);
        earthquake.setLatitude(79.06);
        earthquake.setLongitude(30.67);
        earthquake.setMagnitude(9.5);

        earthquakeRepository.save(earthquake);

        return "Earthquake : " + earthquake.getLongitude() + " " + earthquake.getLatitude();
    }

}
