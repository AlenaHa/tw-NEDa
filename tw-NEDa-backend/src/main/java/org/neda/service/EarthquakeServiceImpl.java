package org.neda.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neda.entity.Earthquake;
import org.neda.repository.EarthquakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EarthquakeServiceImpl implements EarthquakeService, CsvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EarthquakeServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EarthquakeRepository earthquakeRepository;

    @Override
    public List<Earthquake> findEarthquakeSqlInjectionExample(String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM earthquake where e_id = " + id, Earthquake.class);
        List resultList = query.getResultList();
        LOGGER.info("Sql injection happened!");

        return resultList;
    }

    @Override
    public List<Earthquake> getEarthquakesByLocalizationId(Long localizationId) {
        List<Earthquake> list = this.earthquakeRepository.findByLocalizationId(localizationId);
        return list;
    }

    @Override
    public Earthquake findById(Long id) {
        return this.earthquakeRepository.findOne(id);
    }

    @Override
    public Earthquake save(Earthquake earthquake) {

        return this.earthquakeRepository.save(earthquake);
    }

    @Override
    public List<Earthquake> findAll() {
        List<Earthquake> allEarthquakes = new ArrayList<Earthquake>();
        for (Earthquake earthquake : earthquakeRepository.findAll()) {
            allEarthquakes.add(earthquake);
        }
        return allEarthquakes;
    }

    @Override
    public void delete(Long id) {
        this.earthquakeRepository.delete(id);
    }


    @Override
    public void exportCsv(String filePath) throws IOException {
        String csvFile = filePath;
        FileWriter writer = new FileWriter(csvFile);

        for (Earthquake earthquake : this.findAll()) {
            CsvUtils.writeLine(writer, Arrays.asList(
                    earthquake.getEarthquakeId().toString(),
                    earthquake.getDepth().toString(),
                    earthquake.getHappenedOn().toString(),
                    earthquake.getLatitude().toString(),
                    earthquake.getLongitude().toString(),
                    earthquake.getMagnitude().toString()));

        }

        writer.flush();
        writer.close();

    }
}
