package org.neda.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neda.entity.Population;
import org.neda.repository.PopulationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cami on 2017-05-04.
 */
@Service
public class PopulationServiceImpl implements PopulationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PopulationServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PopulationRepository populationRepository;

    @Override
    public Population findById(Long id) {
        return this.populationRepository.findOne(id);
    }

    @Override
    public Population save(Population population) {

        return this.populationRepository.save(population);
    }

    @Override
    public List<Population> findAll() {
        List<Population> allPopulations = new ArrayList<Population>();
        for (Population population : populationRepository.findAll()) {
            allPopulations.add(population);
        }
        return allPopulations;
    }

    @Override
    public void delete(Long id) {
        this.populationRepository.delete(id);
    }


    public Integer getFemaleNumber(String district) {

        Query query = entityManager.createNativeQuery(
                "select * from POPULATION p join location l on p.POPULATION_ID = l.LOCATION_ID " +
                        "where p.GENDER like 'female' and l.district = '" + district + "'", Population.class);
        List resultList = query.getResultList();

        return resultList.size();

    }

    public Integer getMaleNumber(String district) {

        Query query = entityManager.createNativeQuery(
                "select * from POPULATION p join location l on p.POPULATION_ID = l.LOCATION_ID " +
                        "where p.GENDER like 'male' and l.district = '" + district + "'", Population.class);
        List resultList = query.getResultList();

        return resultList.size();

    }

    @Override
    public Integer getAgeNumber15_24(String district) {
        Query query = entityManager.createNativeQuery(
                "select * from POPULATION p join location l on p.POPULATION_ID = l.LOCATION_ID " +
                        "where p.AGE_DISTRIBUTION like '%15%'  and l.district = '" + district + "'", Population.class);
        List resultList = query.getResultList();

        return resultList.size();
    }

    @Override
    public Integer getAgeNumber25_39(String district) {
        Query query = entityManager.createNativeQuery(
                "select * from POPULATION p join location l on p.POPULATION_ID = l.LOCATION_ID " +
                        "where p.AGE_DISTRIBUTION like '%25%'  and l.district = '" + district + "'", Population.class);
        List resultList = query.getResultList();

        return resultList.size();
    }

    @Override
    public Integer getAgeNumber40_54(String district) {
        Query query = entityManager.createNativeQuery(
                "select * from POPULATION p join location l on p.POPULATION_ID = l.LOCATION_ID " +
                        "where p.AGE_DISTRIBUTION like '%40%'  and l.district = '" + district + "'", Population.class);
        List resultList = query.getResultList();

        return resultList.size();
    }

    @Override
    public Integer getAgeNumber55(String district) {
        Query query = entityManager.createNativeQuery(
                "select * from POPULATION p join location l on p.POPULATION_ID = l.LOCATION_ID " +
                        "where p.AGE_DISTRIBUTION like '%55%'  and l.district = '" + district + "'", Population.class);
        List resultList = query.getResultList();

        return resultList.size();
    }

}

