package org.neda.repository;

import org.neda.entity.Supplies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliesRepository extends CrudRepository<Supplies, Long> {
}