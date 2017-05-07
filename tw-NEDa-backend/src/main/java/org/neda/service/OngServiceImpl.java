package org.neda.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.neda.entity.Ong;
import org.neda.repository.OngRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cami on 2017-05-04.
 */
@Service
public class OngServiceImpl implements OngService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OngServiceImpl.class);

    @Autowired
    private OngRepository ongRepository;

    @Override
    public Ong findById(Long id) {
        return this.ongRepository.findOne(id);
    }

    @Override
    public Ong save(Ong ong) {
        return this.ongRepository.save(ong);
    }

    @Override
    public List<Ong> findAll() {
        List<Ong> allOngs = new ArrayList<Ong>();
        for (Ong ong : ongRepository.findAll()) {
            allOngs.add(ong);
        }
        return allOngs;
    }


    @Override
    public void delete(Long id) {
        this.ongRepository.delete(id);
    }
}
