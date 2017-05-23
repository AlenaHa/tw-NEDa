package org.neda.service;

import java.util.List;

import org.neda.entity.Earthquake;

public interface EarthquakeService extends CrudService<Earthquake>, CsvService {
    List<Earthquake> findEarthquakeSqlInjectionExample(String id);

    List<Earthquake> getEarthquakesByLocalizationId(Long localizationId);
}
