package org.neda.controller;


import java.util.List;

import org.neda.entity.Location;
import org.neda.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/location")
@CrossOrigin("*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Location> readLocation(@PathVariable Long id) {
        Location location = locationService.findById(id);
        if (location != null) {
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Location> createLocation(@RequestBody Location reqLocation) {
        Location savedLocation = locationService.save(reqLocation);
        return new ResponseEntity<Location>(savedLocation, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Location> updateLocation(@RequestBody Location reqLocation, @PathVariable Long id) {
        if (!id.equals(reqLocation.getLocationId())) {
            return new ResponseEntity<Location>(HttpStatus.BAD_REQUEST);
        }
        Location savedLocation = locationService.save(reqLocation);
        return new ResponseEntity<Location>(savedLocation, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> all = locationService.findAll();
        return new ResponseEntity<List<Location>>(all, HttpStatus.OK);
    }
    
}
