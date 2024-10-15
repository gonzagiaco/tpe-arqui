package com.grupo30.tp3;

import com.grupo30.tp3.utils.CargaCarreras;
import com.grupo30.tp3.utils.CargaEstudianteCarrera;
import com.grupo30.tp3.utils.CargaEstudiantes;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.grupo30.tp3")
public class Tp3Application {

    public static void main(String[] args) {
        @Autowired
        private CargaEstudiantes cargaEstudiantes;
        @Autowired
        private CargaCarreras cargaCarreras;
        @Autowired
        private CargaEstudianteCarrera cargaEstudianteCarrera;

        public static void main(String[] args) {
            SpringApplication.run(Tp3Application.class, args);

        }

        @PostConstruct
        public void init() throws IOException {
            cargaEstudiantes.cargarDatosDesdeCSV();
            cargaCarreras.cargarDatosDesdeCSV();
            cargaEstudianteCarrera.cargarDatosDesdeCSV();
        }
    }
}
