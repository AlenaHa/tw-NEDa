package org.elena.repository;

import org.elena.entity.Ong;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends CrudRepository<Ong, Long> {
}