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

    /**
     * Find an Location Object by id
     *
     * @param id
     *
     * @return Location Object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Location> readLocation(@PathVariable Long id) {
        Location location = locationService.findById(id);
        if (location != null) {
            return new ResponseEntity<Location>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Delete a specific Location object with a specific id
     * @param id
     * @return message if the Location Object was not deleted
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }

    /**
     * Create a new Location Object
     * @param reqLocation the new Location Object
     * @return the new Location Object and a status code
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Location> createLocation(@RequestBody Location reqLocation) {
        Location savedLocation = locationService.save(reqLocation);
        return new ResponseEntity<Location>(savedLocation, HttpStatus.CREATED);
    }

    /**
     * Update a specific Location Object
     * @param reqLocation the Location Object that you want to be updated
     * @param id the id of the Location Object that you want to be updated
     * @return the Location Object if it was succesfully inserted into the database or just
     * a HTTP error code
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Location> updateLocation(@RequestBody Location reqLocation, @PathVariable Long id) {
        if (!id.equals(reqLocation.getLocationId())) {
            return new ResponseEntity<Location>(HttpStatus.BAD_REQUEST);
        }
        Location savedLocation = locationService.save(reqLocation);
        return new ResponseEntity<Location>(savedLocation, HttpStatus.OK);
    }

    /**
     * Function to return all the list from the Location Table
     * @return List with Location Objects
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> all = locationService.findAll();
        return new ResponseEntity<List<Location>>(all, HttpStatus.OK);
    }

    /**
     * Get a Location by District
     *
     * @param district we search the database with the district that we receive as parameter
     *
     * @return
     */
    @RequestMapping(value = "district/{district}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocationByDistrict(@PathVariable String district) {
        Location location = locationService.getLocationDistrict(district);
        if (location.equals(null)) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Location>(location, HttpStatus.OK);

    }

    /**
     * Get a Location by Municipality
     *
     * @param municipality we search the database with the municipality that we receive as parameter
     *
     * @return
     */
    @RequestMapping(value = "municipality/{municipality}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocationByMunicipality(@PathVariable String municipality) {
        Location location = locationService.getLocationMunicipality(municipality);
        if (location.equals(null)) {
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Location>(location, HttpStatus.OK);

    }





}
