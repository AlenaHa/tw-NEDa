package org.neda.service;

import org.neda.entity.District;
import org.neda.entity.Location;

import java.util.List;


public interface LocationService extends CrudService<Location> {

    /**
     * Method that calls the query to search by distrct from LocationRepository
     *
     * @param district
     *
     * @return Location Object
     */
    Location getLocationDistrict(String district);


    /**
     * Method that calls the query to search by municipality from LocationRepository
     *
     * @param municipality
     *
     * @return Location Object
     */
    Location getLocationMunicipality(String municipality);

    /**
     * @return Returns a list of districts.
     */
    List<District> getAllDistricts();

}

