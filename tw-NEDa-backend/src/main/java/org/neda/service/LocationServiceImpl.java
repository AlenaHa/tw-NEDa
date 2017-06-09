package org.neda.service;

import org.neda.entity.District;
import org.neda.entity.Location;
import org.neda.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Service
public class LocationServiceImpl implements LocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

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

    /**
     * Implements the method from LocationService that searches into the database
     * a Location row with the district received as a parameter
     *
     * @param district
     *
     * @return
     */
    @Override
    public Location getLocationDistrict(String district) {
        return this.LocationRepository.findByDistrict(district);
    }

    /**
     * Implements the method from LocationService that searches into the database
     * a Location row with the municipality received as a parameter
     *
     * @param municipality
     *
     * @return
     */
    @Override
    public Location getLocationMunicipality(String municipality) {
        return this.LocationRepository.findByMunicipality(municipality);
    }

    @Override
    public List<District> getAllDistricts() {
        List<District> districts = new ArrayList<District>();
        List<Object[]> results = entityManager.createNativeQuery("SELECT district from location").getResultList();

        for (Object[] result : results) {
            District district = new District();
            district.setName(result[0].toString());
            districts.add(district);
        }
        return districts;
    }
}
