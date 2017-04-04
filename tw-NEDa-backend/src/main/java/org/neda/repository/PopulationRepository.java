package org.neda.repository;

import org.neda.entity.Population;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends CrudRepository<Population, Long> {

}