package org.neda.service;

import java.util.List;

import org.neda.entity.Population;

/**
 * Created by Cami on 2017-05-04.
 */
public interface PopulationService extends CrudService<Population> {
    Integer getFemaleNumber(String district);

    Integer getMaleNumber(String district);

    Integer getAgeNumber15_24(String district);

    Integer getAgeNumber25_39(String district);

    Integer getAgeNumber40_54(String district);

    Integer getAgeNumber55(String district);

}
