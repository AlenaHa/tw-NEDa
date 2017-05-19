package org.neda.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.neda.entity.Location;
import org.neda.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationServiceImpl implements LocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private LocationRepository LocationRepository;

    @Override
    public Location findById(Long id) {
        return this.LocationRepository.findOne(id);
    }

    @Override
    public Location save(Location Location) {

        return this.LocationRepository.save(Location);
    }

    @Override
    public List<Location> findAll() {
        List<Location> allLocations = new ArrayList<Location>();
        for (Location Location : LocationRepository.findAll()) {
            allLocations.add(Location);
        }
        return allLocations;
    }


    @Override
    public void delete(Long id) {
        this.LocationRepository.delete(id);
    }
}
