package org.neda.service;

import java.io.IOException;
import java.sql.SQLException;

public interface CsvService {
    void exportCsv(String filePath) throws IOException, SQLException;
}
