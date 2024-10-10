package com.grupo30.tp3.utils;


import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.repository.EstudianteRepository;
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
public class CargaEstudiantes {

    private final EstudianteRepository estudianteRepository;


    @Autowired
    public CargaEstudiantes(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void cargarDatosDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/grupo30/tp3/csv/estudiantes.csv");

        try(FileReader reader = new FileReader(archivoCSV)){
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : csvParser) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDocumento(Integer.parseInt(csvRecord.get("DNI")));
                estudiante.setNombre(csvRecord.get("nombre"));
                estudiante.setApellido(csvRecord.get("apellido"));
                estudiante.setEdad(Integer.parseInt(csvRecord.get("edad")));
                estudiante.setGenero(csvRecord.get("genero"));
                estudiante.setCiudad_residencia(csvRecord.get("ciudad"));
                estudiante.setNro_libreta(Integer.parseInt(csvRecord.get("LU")));
                estudianteRepository.persist(estudiante);
            }
        }
    }
}
