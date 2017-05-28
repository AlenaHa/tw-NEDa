package org.neda.repository;

import java.util.List;

import org.neda.entity.Earthquake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarthquakeRepository extends CrudRepository<Earthquake, Long> {

    /**
     * Query that returns a list with all the Earthquakes from a location with
     * the id given as parameter
     *
     * @param localizationId
     *
     * @return
     */
    List<Earthquake> findByLocalizationId(Long localizationId);

    /**
     * Find the latest earthquake
     *
     * @return Earthquake object
     */
    Earthquake findFirstByOrderByHappenedOnDesc();

    /**
     * Find the list of Earthquakes with a given magnitude
     *
     * @return List Of earthquakes
     */
    List<Earthquake> findByMagnitude(Double magnitude);
}