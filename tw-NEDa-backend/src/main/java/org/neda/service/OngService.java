package org.neda.service;

import java.util.List;

import org.neda.entity.Ong;

/**
 * Created by Cami on 2017-05-04.
 */
public interface OngService extends CrudService<Ong> {

    List<Ong> findOngListByLocationId(String locationId);

}
