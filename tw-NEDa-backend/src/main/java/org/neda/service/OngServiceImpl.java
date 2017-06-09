package org.neda.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neda.entity.Ong;
import org.neda.entity.OngDetails;
import org.neda.repository.OngRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cami on 2017-05-04.
 */
@Service
public class OngServiceImpl implements OngService, CsvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OngServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OngRepository ongRepository;

    @Override
    public Ong findById(Long id) {
        return this.ongRepository.findOne(id);
    }

    @Override
    public Ong save(Ong ong) {
        return this.ongRepository.save(ong);
    }

    @Override
    public List<Ong> findAll() {
        List<Ong> allOngs = new ArrayList<Ong>();
        for (Ong ong : ongRepository.findAll()) {
            allOngs.add(ong);
        }
        return allOngs;
    }


    @Override
    public void delete(Long id) {
        this.ongRepository.delete(id);
    }

    @Override
    public List<Ong> findOngListByLocationId(String locationId) {

        Query query = entityManager.createNativeQuery(
                "SELECT * FROM ong o JOIN ONG_LOCATION ol on ol.ONG_ID = o.ONG_ID WHERE ol.LOCATION_ID = " + locationId, Ong.class);
        List resultList = query.getResultList();

        return resultList;

    }

    @Override
    public List<OngDetails> getAllOngDetailsInformation() {
        List<OngDetails> ongDetails = new ArrayList<OngDetails>();

        List<Object[]> results = entityManager.createNativeQuery("select l.DISTRICT,o.ONG_NAME, o.ACTIVITY_TYPE, o.ACTIVITY_SUBTYPE, s.S_NAME " +
                "from ong o join ONG_LOCATION ol on ol.ONG_ID = o.ONG_ID " +
                "join LOCATION l on l.LOCATION_ID = ol.LOCATION_ID " +
                "join SUPPLIES_LOCATION sl on sl.LOCATION_ID = l.LOCATION_ID " +
                "join SUPPLIES s on s.SUPPLIES_ID = sl.SUPPLIES_ID").getResultList();

        for (Object[] result : results) {
            OngDetails ong = new OngDetails();
            ong.setDistrict((String) result[0]);
            ong.setOngName((String) result[1]);
            ong.setActivityType((String) result[2]);
            ong.setActivitySubtype((String) result[3]);
            ong.setSupplyName((String) result[4]);
            ongDetails.add(ong);
        }

        return ongDetails;

    }

    @Override
    public List<OngDetails> getAllOngDetailsByOngName(String name) {
        List<OngDetails> ongDetails = new ArrayList<OngDetails>();

        List<Object[]> results = entityManager.createNativeQuery("select l.DISTRICT,o.ONG_NAME, o.ACTIVITY_TYPE, o.ACTIVITY_SUBTYPE, s.S_NAME " +
                "from ong o join ONG_LOCATION ol on ol.ONG_ID = o.ONG_ID " +
                "join LOCATION l on l.LOCATION_ID = ol.LOCATION_ID " +
                "join SUPPLIES_LOCATION sl on sl.LOCATION_ID = l.LOCATION_ID " +
                "join SUPPLIES s on s.SUPPLIES_ID = sl.SUPPLIES_ID " +
                "where o.ONG_NAME = '" + name + "'").getResultList();

        for (Object[] result : results) {
            OngDetails ong = new OngDetails();
            ong.setDistrict((String) result[0]);
            ong.setOngName((String) result[1]);
            ong.setActivityType((String) result[2]);
            ong.setActivitySubtype((String) result[3]);
            ong.setSupplyName((String) result[4]);
            ongDetails.add(ong);
        }

        return ongDetails;
    }

    @Override
    public List<OngDetails> getAllOngDetailsByDistrict(String district) {
        List<OngDetails> ongDetails = new ArrayList<OngDetails>();

        List<Object[]> results = entityManager.createNativeQuery("select l.DISTRICT,o.ONG_NAME, o.ACTIVITY_TYPE, o.ACTIVITY_SUBTYPE, s.S_NAME " +
                "from ong o join ONG_LOCATION ol on ol.ONG_ID = o.ONG_ID " +
                "join LOCATION l on l.LOCATION_ID = ol.LOCATION_ID " +
                "join SUPPLIES_LOCATION sl on sl.LOCATION_ID = l.LOCATION_ID " +
                "join SUPPLIES s on s.SUPPLIES_ID = sl.SUPPLIES_ID " +
                "where l.DISTRICT = '" + district + "'").getResultList();

        for (Object[] result : results) {
            OngDetails ong = new OngDetails();
            ong.setDistrict((String) result[0]);
            ong.setOngName((String) result[1]);
            ong.setActivityType((String) result[2]);
            ong.setActivitySubtype((String) result[3]);
            ong.setSupplyName((String) result[4]);
            ongDetails.add(ong);
        }

        return ongDetails;
    }

    @Override
    public List<OngDetails> getAllOngDetailsByActivityType(String ongActivity) {
        List<OngDetails> ongDetails = new ArrayList<OngDetails>();

        List<Object[]> results = entityManager.createNativeQuery("select l.DISTRICT,o.ONG_NAME, o.ACTIVITY_TYPE, o.ACTIVITY_SUBTYPE, s.S_NAME " +
                "from ong o join ONG_LOCATION ol on ol.ONG_ID = o.ONG_ID " +
                "join LOCATION l on l.LOCATION_ID = ol.LOCATION_ID " +
                "join SUPPLIES_LOCATION sl on sl.LOCATION_ID = l.LOCATION_ID " +
                "join SUPPLIES s on s.SUPPLIES_ID = sl.SUPPLIES_ID " +
                "where o.ACTIVITY_TYPE = '" + ongActivity + "'").getResultList();

        for (Object[] result : results) {
            OngDetails ong = new OngDetails();
            ong.setDistrict((String) result[0]);
            ong.setOngName((String) result[1]);
            ong.setActivityType((String) result[2]);
            ong.setActivitySubtype((String) result[3]);
            ong.setSupplyName((String) result[4]);
            ongDetails.add(ong);
        }

        return ongDetails;
    }

    @Override
    public List<OngDetails> getAllOngDetailsBySupplies(String supply) {
        List<OngDetails> ongDetails = new ArrayList<OngDetails>();

        List<Object[]> results = entityManager.createNativeQuery("select l.DISTRICT,o.ONG_NAME, o.ACTIVITY_TYPE, o.ACTIVITY_SUBTYPE, s.S_NAME " +
                "from ong o join ONG_LOCATION ol on ol.ONG_ID = o.ONG_ID " +
                "join LOCATION l on l.LOCATION_ID = ol.LOCATION_ID " +
                "join SUPPLIES_LOCATION sl on sl.LOCATION_ID = l.LOCATION_ID " +
                "join SUPPLIES s on s.SUPPLIES_ID = sl.SUPPLIES_ID " +
                "where s.S_NAME = '" + supply + "'").getResultList();

        for (Object[] result : results) {
            OngDetails ong = new OngDetails();
            ong.setDistrict((String) result[0]);
            ong.setOngName((String) result[1]);
            ong.setActivityType((String) result[2]);
            ong.setActivitySubtype((String) result[3]);
            ong.setSupplyName((String) result[4]);
            ongDetails.add(ong);
        }

        return ongDetails;
    }

    @Override
    public void exportCsv(String filePath) throws IOException {
        String csvFile = filePath;
        FileWriter writer = new FileWriter(csvFile);

        for (Ong ong : this.findAll()) {
            CsvUtils.writeLine(writer, Arrays.asList(
                    ong.getOngId().toString(),
                    ong.getOngName(),
                    ong.getActivityType(),
                    ong.getSubactivityType()));
        }

        writer.flush();
        writer.close();
    }
}
