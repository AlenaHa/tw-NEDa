package org.neda.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.neda.entity.Earthquake;
import org.neda.service.EarthquakeService;
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
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/earthquakes")
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    /**
     * Find an Earthquake Object by Id
     *
     * @param id the Id to search by
     *
     * @return An Earthquake Object / message if the object was successfully found or not
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Earthquake> readEarthquake(@PathVariable Long id) {
        Earthquake earthquake = earthquakeService.findById(id);
        if (earthquake != null) {
            return new ResponseEntity<Earthquake>(earthquake, HttpStatus.OK);
        } else {
            return new ResponseEntity<Earthquake>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a particular Earthquake Object from database
     * @param id the id of the Earthquake to delete
     * @return message if the object was successfully deleted
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Earthquake> deleteEarthquake(@PathVariable Long id) {
        earthquakeService.delete(id);
        return new ResponseEntity<Earthquake>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add a new Earthquake Object and add it into the database
     * @param reqEarthquake the object to add into the database
     * @return the new Earthquake Object / message if the object was successfully inserted into the db
     */
    @RequestMapping(value = "/earthquake", method = RequestMethod.POST)
    public ResponseEntity<Earthquake> createEarthquake(@RequestBody Earthquake reqEarthquake) {
        Earthquake savedEarthquake = earthquakeService.save(reqEarthquake);
        return new ResponseEntity<Earthquake>(savedEarthquake, HttpStatus.CREATED);
    }

    /**
     * Update a particular Earthquake Object
     * @param reqEarthquake the object that needs to be updated
     * @param id the id of the object
     * @return message if the update was successfully or not
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Earthquake> updateEarthquake(@RequestBody Earthquake reqEarthquake, @PathVariable Long id) {
        if (!id.equals(reqEarthquake.getEarthquakeId())) {
            return new ResponseEntity<Earthquake>(HttpStatus.BAD_REQUEST);
        }
        Earthquake savedEarthquake = earthquakeService.save(reqEarthquake);
        return new ResponseEntity<Earthquake>(savedEarthquake, HttpStatus.OK);
    }

    /**
     * Get a list with all the Earthquake objects from database
     * @return List with the Earthquake Object and a message if the request was successfully
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Earthquake>> getAllEarthquakes() {
        List<Earthquake> all = earthquakeService.findAll();
        return new ResponseEntity<List<Earthquake>>(all, HttpStatus.OK);
    }

    /**
     * Method that shows sql injection
     * @param sqlInjectionId
     * @return
     */
    @RequestMapping(value = "/sqlinjection", method = RequestMethod.POST)
    public ResponseEntity<List<Earthquake>> sqlInjectionExample(@RequestBody String sqlInjectionId) {
        List<Earthquake> earthquakeSqlInjectionExample = earthquakeService.findEarthquakeSqlInjectionExample(sqlInjectionId);
        return new ResponseEntity<List<Earthquake>>(earthquakeSqlInjectionExample, HttpStatus.OK);
    }

    /**
     * Get a list with all the Earthquakes that happened in a location with the location id given
     *
     * @param localizationId the location id by which the query is made
     *
     * @return List with all the query and the message if the list was found or nor
     */
    @RequestMapping(value = "location/{localizationId}", method = RequestMethod.GET)
    public ResponseEntity<List<Earthquake>> getListByLocId(@PathVariable Long localizationId) {
        List<Earthquake> list = this.earthquakeService.getEarthquakesByLocalizationId(localizationId);
        if (list.isEmpty()) {
            return new ResponseEntity<List<Earthquake>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Earthquake>>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/csvExport", method = RequestMethod.POST)
    public ResponseEntity exportCsv(@RequestBody String filePath) throws IOException, SQLException {
        earthquakeService.exportCsv(filePath);
        return new ResponseEntity(HttpStatus.OK);
    }

}
