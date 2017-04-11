package org.neda.service;

import java.util.List;

import org.neda.entity.Earthquake;

/**
 * @author Elena Hardon
 * @date 4/11/17.
 */
public interface EarthquakeService extends CrudService<Earthquake> {
    List<Earthquake> findEarthquakeSqlInjectionExample(String id);
}
