package org.neda.service;

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

/**
 * @author Elena Hardon
 * @date 4/11/17.
 */


/*
 The @Transactional is used to make sure that the operations are running in a transaction
 and allows setting the readOnly-flag (at the class level). This causes some performance
 optimizations inside the persistence provider as well as on the database level.
 */
@Service
public class EarthquakeServiceImpl implements EarthquakeService {
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
    public Earthquake findById(Long id) {
        return this.earthquakeRepository.findOne(id);
    }

    @Override
    public Earthquake save(Earthquake earthquake) {

        return this.earthquakeRepository.save(earthquake);
    }

    @Override
    public Iterable<Earthquake> findAll() {
        return this.earthquakeRepository.findAll();
    }


    @Override
    public void delete(Long id) {
        this.earthquakeRepository.delete(id);
    }
}
