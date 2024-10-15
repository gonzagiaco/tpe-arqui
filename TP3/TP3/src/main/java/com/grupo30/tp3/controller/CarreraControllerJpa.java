package com.grupo30.tp3.controller;


import com.grupo30.tp3.servicios.CarreraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carreras")
public class CarreraControllerJpa {

    @Autowired
    private CarreraServicio carreraServicio;


    /**
     * Endpoint para obtener todas las carreras con estudiantes inscriptos.
     * @return Se retorna una lista de carreras ordenadas por cantidad de inscriptos
     * - Retorna una respuesta HTTP 200 (OK) si la consulta es exitosa.
     * - Retorna una respuesta HTTP 400 (Bad Request) si el criterio de orden no es válido.
     * - Retorna una respuesta HTTP 500 (Internal Server Error) en caso de un error inesperado.
     */
    @GetMapping("")
    public ResponseEntity<?> findAll() {
            System.out.println(carreraServicio.getCarrerasOrdenadas());
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraServicio.getCarrerasOrdenadas());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<String>> getReportesCarreras() {
        try {
            List<Object[]> reportes = carreraServicio.getReporteCarreras();
            List<String> reportesFormateados = new ArrayList<>();

            for (Object[] reporte : reportes) {
                String carrera = (String) reporte[0];
                int inscripcion = (int) reporte[1];
                long inscriptos = (long) reporte[2];
                long egresados = (long) reporte[3];

                reportesFormateados.add(String.format("Carrera: %s, Inscripción: %d, Inscriptos: %d, Egresados: %d",
                        carrera, inscripcion, inscriptos, egresados));
            }

            return ResponseEntity.status(HttpStatus.OK).body(reportesFormateados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("{\"error\":\"Error. Por favor intente más tarde.\"}"));
        }
    }
}
