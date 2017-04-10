package org.neda.controller;


import org.neda.entity.Ong;
import org.neda.repository.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OngController {

    @Autowired
    private OngRepository ongRepository;

    @RequestMapping(value = "/ong",method = RequestMethod.GET)
    @ResponseBody
    public String itWorks(){
        Ong ong = new Ong();
        ong.setLocalizationId(3);
        ong.setOngName("MyOng");
        ong.setActivityType("Building house");

        ongRepository.save(ong);
        return "ONG : " + ong.getOngName() + " " + ong.getActivityType();
    }
}
