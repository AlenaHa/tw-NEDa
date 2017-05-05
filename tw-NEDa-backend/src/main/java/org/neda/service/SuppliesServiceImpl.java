package org.neda.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neda.entity.Supplies;
import org.neda.repository.SuppliesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SuppliesServiceImpl implements SuppliesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuppliesServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SuppliesRepository suppliesRepository;

    @Override
    public Supplies findById(Long id) {
        return this.suppliesRepository.findOne(id);
    }

    @Override
    public Supplies save(Supplies supplies) {

        return this.suppliesRepository.save(supplies);
    }

    @Override
    public List<Supplies> findAll() {
        List<Supplies> allSuppliess = new ArrayList<Supplies>();
        for (Supplies supplies : suppliesRepository.findAll()) {
            allSuppliess.add(supplies);
        }
        return allSuppliess;
    }


    @Override
    public void delete(Long id) {
        this.suppliesRepository.delete(id);
    }
}
