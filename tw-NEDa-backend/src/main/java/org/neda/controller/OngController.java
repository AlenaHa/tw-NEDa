package org.neda.controller;


import java.util.List;

import org.neda.entity.Ong;
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
public class OngController {

    @Autowired
    private OngService ongService;

    /**
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/ong/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ong> readOng(@PathVariable Long id) {
        Ong ong = ongService.findById(id);
        if (ong != null) {
            return new ResponseEntity<Ong>(ong, HttpStatus.OK);
        } else {
            return new ResponseEntity<Ong>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "/ong/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ong> deleteOng(@PathVariable Long id) {
        ongService.delete(id);
        return new ResponseEntity<Ong>(HttpStatus.NO_CONTENT);
    }

    /**
     * @param reqOng
     *
     * @return
     */
    @RequestMapping(value = "/ong", method = RequestMethod.POST)
    public ResponseEntity<Ong> createOng(@RequestBody Ong reqOng) {
        Ong savedOng = ongService.save(reqOng);
        return new ResponseEntity<Ong>(savedOng, HttpStatus.CREATED);
    }

    /**
     * @param reqOng
     *
     * @return
     */
    @RequestMapping(value = "/ong/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ong> updateOng(@RequestBody Ong reqOng, @PathVariable Long id) {
        if (!id.equals(reqOng.getOngId())) {
            return new ResponseEntity<Ong>(HttpStatus.BAD_REQUEST);
        }
        Ong savedOng = ongService.save(reqOng);
        return new ResponseEntity<Ong>(savedOng, HttpStatus.OK);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/ong/all", method = RequestMethod.GET)
    public ResponseEntity<List<Ong>> getAllOngs() {
        List<Ong> all = ongService.findAll();
        return new ResponseEntity<List<Ong>>(all, HttpStatus.OK);
    }

}
