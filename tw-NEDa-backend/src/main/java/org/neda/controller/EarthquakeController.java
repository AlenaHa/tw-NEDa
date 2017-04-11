package org.neda.controller;


import org.neda.entity.Earthquake;
import org.neda.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    @RequestMapping(value = "/earthquake", method = RequestMethod.GET)
    public Earthquake earthquakeList() {
        return earthquakeService.findById(5l);
    }

}
