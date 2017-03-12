package org.elena.repository;

import org.elena.entity.Earthquake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarthquakeRepository extends CrudRepository<Earthquake, Long> {

}