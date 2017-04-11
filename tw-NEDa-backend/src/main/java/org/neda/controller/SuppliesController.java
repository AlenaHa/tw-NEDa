package org.neda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SuppliesController {

    // TODO: Create Supplies service and autowire it here

    @RequestMapping(value = "/supplies", method = RequestMethod.GET)
    public String itWorks() {
        return "it works!";
    }
}