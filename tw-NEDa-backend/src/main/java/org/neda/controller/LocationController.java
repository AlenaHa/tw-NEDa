package org.neda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    // TODO: Create Location service and autowire it here

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String locationWorks() {
        return "it works!";
    }
}
