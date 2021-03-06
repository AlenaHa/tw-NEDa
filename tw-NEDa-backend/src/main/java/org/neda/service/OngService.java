package org.neda.service;

import java.io.IOException;
import java.util.List;

import org.neda.entity.Ong;
import org.neda.entity.OngDetails;

/**
 * Created by Cami on 2017-05-04.
 */
public interface OngService extends CrudService<Ong> {

    List<Ong> findOngListByLocationId(String locationId);

    List<OngDetails> getAllOngDetailsInformation();

    List<OngDetails> getAllOngDetailsByOngName(String name);

    List<OngDetails> getAllOngDetailsByDistrict(String s);

    List<OngDetails> getAllOngDetailsByActivityType(String ongActivity);

    List<OngDetails> getAllOngDetailsBySupplies(String supply);

    public void exportCsv(String filePath) throws IOException;
}
