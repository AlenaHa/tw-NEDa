package org.neda.controller;


import org.neda.entity.Earthquake;
import org.neda.service.EarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
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


}
