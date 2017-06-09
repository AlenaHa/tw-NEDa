package org.neda.controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.neda.db.EarthquakeAndLocation;
import org.neda.entity.CompleteEarthquake;
import org.neda.entity.Earthquake;
import org.neda.entity.Location;
import org.neda.service.EarthquakeService;
import org.neda.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;


@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/earthquakes")
public class EarthquakeController {

    @Autowired
    private EarthquakeService earthquakeService;

    @Autowired
    private LocationService locationService;

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
     * @param id            the id of the object
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
     * @return List with all the query and the message if the list was found or not
     */
    @RequestMapping(value = "/location/{localizationId}", method = RequestMethod.GET)
    public ResponseEntity<List<Earthquake>> getListByLocId(@PathVariable Long localizationId) {
        List<Earthquake> list = this.earthquakeService.getEarthquakesByLocalizationId(localizationId);
        if (list.isEmpty()) {
            return new ResponseEntity<List<Earthquake>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Earthquake>>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/db.csv", method = RequestMethod.GET)
    public void exportCsv(HttpServletResponse response) throws IOException, SQLException {
        String fileName = "D:\\earthquakes.csv";
        earthquakeService.exportCsv(fileName);
        InputStream is = null;
        File file = null;
        try {
            file = new File(fileName);
            is = new FileInputStream(file);
            IOUtils.copy(is, response.getOutputStream());
            response.setContentType("application/csv");
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
            file.delete();
        }
    }

    /**
     * Get the latest Earthquake
     *
     * @return Earthquake Object and a message if we found the object successfully or not
     */
    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public ResponseEntity<Earthquake> getLatestEarthquake() {
        Earthquake earthquake = earthquakeService.getLatestEarthquake();
        if (earthquake.equals(null)) {
            return new ResponseEntity<Earthquake>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Earthquake>(earthquake, HttpStatus.OK);
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public ResponseEntity<List<CompleteEarthquake>> getAllCompleteEarthquakes() {
        List<CompleteEarthquake> allCompleteEarthquakes = earthquakeService.getAllEarthquakeInformation();

        if (allCompleteEarthquakes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allCompleteEarthquakes, HttpStatus.OK);
    }

    @RequestMapping(value = "/complete/magnitude/{magnitude}", method = RequestMethod.GET)
    public ResponseEntity<List<CompleteEarthquake>> getEarthquakesByMangitude(@PathVariable Double magnitude) {
        List<CompleteEarthquake> allEarthquakesByMagnitude = earthquakeService.getAllEarthquakesByMagnitude(magnitude);
        return new ResponseEntity<List<CompleteEarthquake>>(allEarthquakesByMagnitude, HttpStatus.OK);
    }


    /**
     * Get the list of earthquakes that have this magnitude
     *
     * @param magnitude
     *
     * @return List of earthquakes
     */
    @RequestMapping(value = "/magnitude/{magnitude}", method = RequestMethod.GET)
    public ResponseEntity<List<Earthquake>> getListByMagnitude(@PathVariable Double magnitude) {
        List<Earthquake> list = this.earthquakeService.getListEarthquakeByMagnitude(magnitude);
        if (list.equals(null)) {
            return new ResponseEntity<List<Earthquake>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Earthquake>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/depth/{depth}", method = RequestMethod.GET)
    public ResponseEntity<List<CompleteEarthquake>> getEarthquakesByDepth(@PathVariable Double depth) {
        List<CompleteEarthquake> allEarthquakesByDepth = earthquakeService.getAllEarthquakesByDepth(depth);
        if (allEarthquakesByDepth.isEmpty()) {
            return new ResponseEntity<List<CompleteEarthquake>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CompleteEarthquake>>(allEarthquakesByDepth, HttpStatus.OK);
    }

    @RequestMapping(value = "/year/{year}", method = RequestMethod.GET)
    public ResponseEntity<List<CompleteEarthquake>> getEarthquakesByYear(@PathVariable Integer year) {
        List<CompleteEarthquake> allEarthquakesByYear = earthquakeService.getAllEarthquakesByYear(year);
        if (allEarthquakesByYear.isEmpty()) {
            return new ResponseEntity<List<CompleteEarthquake>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CompleteEarthquake>>(allEarthquakesByYear, HttpStatus.OK);
    }

    @RequestMapping(value = "/district/{district}", method = RequestMethod.GET)
    public ResponseEntity<List<CompleteEarthquake>> getEarthquakesByYear(@PathVariable String district) {
        List<CompleteEarthquake> allEarthquakesByDistrict = earthquakeService.getAllEarthquakesByDistrict(district);
        if (allEarthquakesByDistrict.isEmpty()) {
            return new ResponseEntity<List<CompleteEarthquake>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CompleteEarthquake>>(allEarthquakesByDistrict, HttpStatus.OK);
    }

    @RequestMapping(value = "/e-l", method = RequestMethod.POST)
    public ResponseEntity<EarthquakeAndLocation> createEarthquakeAndLocation(@RequestBody EarthquakeAndLocation earthquakeAndLocation) {

        Location savedLocation = locationService.save(earthquakeAndLocation.getLocation());
        Earthquake requestEarthquake = earthquakeAndLocation.getEarthquake();
        requestEarthquake.setLocalizationId(savedLocation.getLocationId());

        earthquakeService.save(requestEarthquake);

        return new ResponseEntity<EarthquakeAndLocation>(HttpStatus.CREATED);
    }
}
