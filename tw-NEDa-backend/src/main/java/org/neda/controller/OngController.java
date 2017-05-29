package org.neda.controller;


import java.util.List;

import org.neda.entity.Ong;
import org.neda.service.OngService;
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
@RequestMapping(value = "/ong")
@CrossOrigin("*")
public class OngController {

    @Autowired
    private OngService ongService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ong> readOng(@PathVariable Long id) {
        Ong ong = ongService.findById(id);
        if (ong != null) {
            return new ResponseEntity<Ong>(ong, HttpStatus.OK);
        } else {
            return new ResponseEntity<Ong>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ong> deleteOng(@PathVariable Long id) {
        ongService.delete(id);
        return new ResponseEntity<Ong>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Ong> createOng(@RequestBody Ong reqOng) {
        Ong savedOng = ongService.save(reqOng);
        return new ResponseEntity<Ong>(savedOng, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ong> updateOng(@RequestBody Ong reqOng, @PathVariable Long id) {
        if (!id.equals(reqOng.getOngId())) {
            return new ResponseEntity<Ong>(HttpStatus.BAD_REQUEST);
        }
        Ong savedOng = ongService.save(reqOng);
        return new ResponseEntity<Ong>(savedOng, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Ong>> getAllOngs() {
        List<Ong> all = ongService.findAll();
        return new ResponseEntity<List<Ong>>(all, HttpStatus.OK);
    }

    /**
     * Get the list with all the ONG that are in that Location Id
     *
     * @param locationId the Location Id by which the search is made
     *
     * @return List of Ong Object / message if list was successfully populated or not
     */
    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public ResponseEntity<List<Ong>> getListOngByLocation(@PathVariable Long locationId) {
        List<Ong> list = this.ongService.findOngListByLocationId(locationId.toString());
        if (list.isEmpty()) {
            return new ResponseEntity<List<Ong>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ong>>(list, HttpStatus.OK);
    }
}
