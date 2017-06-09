package org.neda.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.neda.entity.Ong;
import org.neda.entity.OngDetails;
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

    @RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET)
    public ResponseEntity<List<Ong>> getListOngByLocation(@PathVariable Long locationId) {
        List<Ong> list = this.ongService.findOngListByLocationId(locationId.toString());
        if (list.isEmpty()) {
            return new ResponseEntity<List<Ong>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ong>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "ongDetails/district/{district}", method = RequestMethod.GET)
    public ResponseEntity<List<OngDetails>> getListOngByLocation(@PathVariable String district) {
        List<OngDetails> list = this.ongService.getAllOngDetailsByDistrict(district);
        if (list.isEmpty()) {
            return new ResponseEntity<List<OngDetails>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OngDetails>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "ongDetails/ongName/{ongName}", method = RequestMethod.GET)
    public ResponseEntity<List<OngDetails>> getOngByName(@PathVariable String ongName) {
        List<OngDetails> ong = ongService.getAllOngDetailsByOngName(ongName);
        if (ong.equals(null)) {
            return new ResponseEntity<List<OngDetails>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<OngDetails>>(ong, HttpStatus.OK);

    }

    @RequestMapping(value = "ongDetails/ongActivityType/{ongActivity}", method = RequestMethod.GET)
    public ResponseEntity<List<OngDetails>> getOngByActivity(@PathVariable String ongActivity) {
        List<OngDetails> ong = ongService.getAllOngDetailsByActivityType(ongActivity);
        if (ong.equals(null)) {
            return new ResponseEntity<List<OngDetails>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<OngDetails>>(ong, HttpStatus.OK);

    }

    @RequestMapping(value = "ongDetails/supplies/{supply}", method = RequestMethod.GET)
    public ResponseEntity<List<OngDetails>> getOngBySupplies(@PathVariable String supply) {
        List<OngDetails> ong = ongService.getAllOngDetailsBySupplies(supply);
        if (ong.equals(null)) {
            return new ResponseEntity<List<OngDetails>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<OngDetails>>(ong, HttpStatus.OK);

    }

    @RequestMapping(value = "/ongDetails", method = RequestMethod.GET)
    public ResponseEntity<List<OngDetails>> getAllOngDetails() {
        List<OngDetails> allOngDetailsList = ongService.getAllOngDetailsInformation();

        if (allOngDetailsList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(allOngDetailsList, HttpStatus.OK);

    }

    @RequestMapping(value = "/ong.csv", method = RequestMethod.GET)
    public void exportCsv(HttpServletResponse response) throws IOException, SQLException {
        String fileName = "D:\\ong.csv";
        ongService.exportCsv(fileName);
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

}
