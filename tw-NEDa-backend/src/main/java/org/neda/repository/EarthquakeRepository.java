package org.neda.repository;

import org.neda.entity.Earthquake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarthquakeRepository extends CrudRepository<Earthquake, Long> {

}