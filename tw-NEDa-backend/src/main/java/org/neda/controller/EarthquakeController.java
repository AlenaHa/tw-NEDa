package org.neda.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Earthquake> readEarthquake(@PathVariable Long id) {
        Earthquake earthquake = earthquakeService.findById(id);
        if (earthquake != null) {
            return new ResponseEntity<Earthquake>(earthquake, HttpStatus.OK);
        } else {
            return new ResponseEntity<Earthquake>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Earthquake> deleteEarthquake(@PathVariable Long id) {
        earthquakeService.delete(id);
        return new ResponseEntity<Earthquake>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/earthquake", method = RequestMethod.POST)
    public ResponseEntity<Earthquake> createEarthquake(@RequestBody Earthquake reqEarthquake) {
        Earthquake savedEarthquake = earthquakeService.save(reqEarthquake);
        return new ResponseEntity<Earthquake>(savedEarthquake, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Earthquake> updateEarthquake(@RequestBody Earthquake reqEarthquake, @PathVariable Long id) {
        if (!id.equals(reqEarthquake.getEarthquakeId())) {
            return new ResponseEntity<Earthquake>(HttpStatus.BAD_REQUEST);
        }
        Earthquake savedEarthquake = earthquakeService.save(reqEarthquake);
        return new ResponseEntity<Earthquake>(savedEarthquake, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Earthquake>> getAllEarthquakes() {
        List<Earthquake> all = earthquakeService.findAll();
        return new ResponseEntity<List<Earthquake>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/sqlinjection", method = RequestMethod.POST)
    public ResponseEntity<List<Earthquake>> sqlInjectionExample(@RequestBody String sqlInjectionId) {
        List<Earthquake> earthquakeSqlInjectionExample = earthquakeService.findEarthquakeSqlInjectionExample(sqlInjectionId);
        return new ResponseEntity<List<Earthquake>>(earthquakeSqlInjectionExample, HttpStatus.OK);
    }

    /**
     * Implements the end-point that creates the csv file and provides it on the {@link HttpServletResponse}
     */
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            is.close();
            file.delete();
        }
    }
}
