package com.grupo30.trabajogrupal.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CsvRecords {

    public Iterable<CSVRecord> getCsvRecords(String[] HEADERS, String csvRoute) throws IOException {
        Reader reader = new FileReader(csvRoute);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
        Iterable<CSVRecord> records = csvFormat.parse(reader);
        return records;
    }
}
