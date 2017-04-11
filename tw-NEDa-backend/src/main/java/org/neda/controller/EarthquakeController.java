package org.neda.controller;


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
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/earthquake/{id}", method = RequestMethod.GET)
    public ResponseEntity<Earthquake> readEarthquake(@PathVariable Long id) {
        Earthquake earthquake = earthquakeService.findById(id);
        if (earthquake != null) {
            return new ResponseEntity<Earthquake>(earthquake, HttpStatus.OK);
        } else {
            return new ResponseEntity<Earthquake>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/earthquake/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Earthquake> deleteEarthquake(@PathVariable Long id) {
        earthquakeService.delete(id);
        return new ResponseEntity<Earthquake>(HttpStatus.NO_CONTENT);
    }

    /**
     * @param reqEarthquake
     * @return
     */
    @RequestMapping(value = "/earthquake", method = RequestMethod.POST)
    public ResponseEntity<Earthquake> createEarthquake(@RequestBody Earthquake reqEarthquake) {
        Earthquake savedEarthquake = earthquakeService.save(reqEarthquake);
        return new ResponseEntity<Earthquake>(savedEarthquake, HttpStatus.CREATED);
    }

    /**
     * @param reqEarthquake
     * @return
     */
    @RequestMapping(value = "/earthquake/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Earthquake> updateEarthquake(@RequestBody Earthquake reqEarthquake, @PathVariable Long id) {
        if (id != reqEarthquake.getEarthquakeId()) {
            return new ResponseEntity<Earthquake>(HttpStatus.BAD_REQUEST);
        }
        Earthquake savedEarthquake = earthquakeService.save(reqEarthquake);
        return new ResponseEntity<Earthquake>(savedEarthquake, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/earthquake/all", method = RequestMethod.GET)
    public ResponseEntity<List<Earthquake>> getAllEarthquakes() {
        List<Earthquake> all = earthquakeService.findAll();
        return new ResponseEntity<List<Earthquake>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/earthquake/sqlinjection", method = RequestMethod.POST)
    public ResponseEntity<List<Earthquake>> sqlInjectionExample(@RequestBody String sqlInjectionId) {
        List<Earthquake> earthquakeSqlInjectionExample = earthquakeService.findEarthquakeSqlInjectionExample(sqlInjectionId);
        return new ResponseEntity<List<Earthquake>>(earthquakeSqlInjectionExample, HttpStatus.OK);
    }


}
