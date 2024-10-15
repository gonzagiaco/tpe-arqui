package com.grupo30.tp3.utils;


import com.grupo30.tp3.model.Carrera;
import com.grupo30.tp3.repository.CarreraRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargaCarreras {

    @Autowired
    private CarreraRepository carreraRepository;


    @Autowired
    public CargaCarreras(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public void cargarDatosDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/grupo30/tp3/csv/carreras.csv");

        try(FileReader reader = new FileReader(archivoCSV)){
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : csvParser) {
                Carrera carrera = new Carrera();
                carrera.setId(Integer.parseInt(csvRecord.get("id_carrera")));
                carrera.setCarrera(csvRecord.get("carrera"));
                carrera.setDuracion(Integer.parseInt(csvRecord.get("duracion")));
                carreraRepository.save(carrera);
            }
        }
    }
}
