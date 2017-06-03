package org.neda.service;

import java.util.List;

import org.neda.entity.CompleteEarthquake;
import org.neda.entity.Earthquake;

public interface EarthquakeService extends CrudService<Earthquake>, CsvService {

    /**
     * Sql injection function
     *
     * @param id
     *
     * @return
     */
    List<Earthquake> findEarthquakeSqlInjectionExample(String id);

    /**
     * Call the query from EarthquakeRepository to get the list of all
     * the earthquakes that took place int that locationId
     * @param localizationId
     * @return List of Earthquakes
     */
    List<Earthquake> getEarthquakesByLocalizationId(Long localizationId);

    /**
     * Call the query from EarthquakeRepository to get the latest
     * Earthquake
     *
     * @return Earthquake Object
     */
    Earthquake getLatestEarthquake();

    /**
     * Call the query from EarthquakeRepository to get the list
     * of eq with the given magnitude
     *
     * @param magnitude
     *
     * @return List of earthquakes
     */
    List<Earthquake> getListEarthquakeByMagnitude(Double magnitude);

    /**
     * Gets all the earthquakes and the information regarding the location(municipality and district)
     *
     * @return The list of all the earthquakes in the database and their complete location.
     */
    List<CompleteEarthquake> getAllEarthquakeInformation();
}
