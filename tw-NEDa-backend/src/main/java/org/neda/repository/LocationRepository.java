package org.neda.repository;

import org.neda.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    /**
     * The Query that searches into the Location Table the row with the District name
     * receive as parameter
     *
     * @param district District name
     *
     * @return Location Object
     */
    Location findByDistrict(String district);


    /**
     * The Query that searches into the Location Table the row with the Municipality name
     * receive as parameter
     *
     * @param municipality Municipality name
     *
     * @return Location Object
     */
    Location findByMunicipality(String municipality);


}
