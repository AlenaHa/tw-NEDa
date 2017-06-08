package org.neda.repository;

import java.util.List;

import org.neda.entity.Ong;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends CrudRepository<Ong, Long> {
    List<Ong> findByOngName(String name);

    List<Ong> findByActivityType(String activity);
}