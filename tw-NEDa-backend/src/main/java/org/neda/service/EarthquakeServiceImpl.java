package org.neda.service;

import org.neda.entity.Earthquake;
import org.neda.repository.EarthquakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EarthquakeRepository earthquakeRepository;

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
