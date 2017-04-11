package org.neda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PopulationController {


    // TODO: Create Populatnio service and autowire it here

    @RequestMapping(value = "/population", method = RequestMethod.GET)
    public String populationWorks() {
        return "it works!";
    }
}
