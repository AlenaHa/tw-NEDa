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
}