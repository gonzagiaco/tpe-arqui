package com.grupo30.tp3.utils;


import com.grupo30.tp3.model.Estudia;
import com.grupo30.tp3.servicios.CarreraServicio;
import com.grupo30.tp3.repository.EstudianteCarreraRepository;
import com.grupo30.tp3.servicios.EstudianteServicio;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargaEstudianteCarrera {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;
    @Autowired
    private EstudianteServicio estudianteServicio;
    @Autowired
    private CarreraServicio carreraServicio;


    @Autowired
    public CargaEstudianteCarrera(EstudianteCarreraRepository estudianteCarreraRepository) {
        this.estudianteCarreraRepository = estudianteCarreraRepository;
    }

    @Transactional
    public void cargarDatosDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/grupo30/tp3/csv/estudianteCarrera.csv");

        try(FileReader reader = new FileReader(archivoCSV)){
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord csvRecord : csvParser) {
                Estudia estudia = new Estudia();
                estudia.setId(Integer.parseInt(csvRecord.get("id")));
                estudia.setEstudiante(estudianteServicio.findById(Long.parseLong(csvRecord.get("id_estudiante"))));
                estudia.setCarrera(carreraServicio.findById(Long.parseLong(csvRecord.get("id_carrera"))));
                estudia.setInscripcion(Integer.parseInt(csvRecord.get("inscripcion")));
                estudia.setGraduacion(Integer.parseInt(csvRecord.get("graduacion")));
                estudia.setAntiguedad(Integer.parseInt(csvRecord.get("antiguedad")));
                estudianteCarreraRepository.save(estudia);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
