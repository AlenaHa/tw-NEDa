package org.neda.controller;

import org.neda.entity.Location;
import org.neda.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;



    /**
     * Map this method to path from the value
     * We populate an entity to see if the databese works
     * @return string message with some values from the location entity
     */
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    @ResponseBody
    public String itWorks() {

        Location location = new Location();
        location.setMunicipality("Brahmaptra");
        location.setDistrict("Brahmaptra");

        locationRepository.save(location);


        return "Location works : " + location.getMunicipality() +" " +
                    location.getDistrict();
    }
}
