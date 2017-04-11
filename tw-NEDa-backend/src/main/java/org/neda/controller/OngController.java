package org.neda.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ong")
public class OngController {

    // TODO: Create Ong service and autowire it here

    @RequestMapping(value = "/ong", method = RequestMethod.GET)
    public String itWorks() {
        return "it works!";
    }
}
