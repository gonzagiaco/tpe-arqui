package com.grupo30.tp3.controller;


import com.grupo30.tp3.dtos.EstudianteDTO;
import com.grupo30.tp3.model.Estudiante;
import com.grupo30.tp3.servicios.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteControllerJpa {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findAll());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    /**
     * Endpoint para obtener un estudiante por su número de libreta.
     *
     * @param nrolibreta El número de libreta del estudiante que se desea buscar.
     *
     * @return Un objeto ResponseEntity que contiene el estudiante encontrado (EstudianteDTO) o null
     * - Retorna una respuesta HTTP 200 (OK) si la consulta es exitosa.
     * - Retorna una respuesta HTTP 400 (Bad Request) si el criterio de orden no es válido.
     * - Retorna una respuesta HTTP 404 (NOT FOUND) si el estudiante no es encontrado.
     * - Retorna una respuesta HTTP 500 (Internal Server Error) en caso de un error inesperado.
     */
    @GetMapping("/nrolibreta/{nrolibreta}")
    public ResponseEntity<EstudianteDTO> getEstudiantePorNroLibreta(@PathVariable int nrolibreta) {
        try {
            EstudianteDTO estudiante = estudianteServicio.buscarEstudiantePorNroLibreta(nrolibreta);
            if (estudiante != null) {
                return ResponseEntity.ok(estudiante);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Endpoint para obtener la lista de estudiantes ordenada según un criterio específico.
     *
     * @param orden El criterio de ordenamiento (puede ser "nombre", "apellido" o "edad"). Si no se especifica, se usa "nombre" como valor por defecto.
     * Este parámetro se pasa como un parámetro de consulta en la URL.
     *
     * @return ResponseEntity que contiene una lista de objetos EstudianteDTO ordenados según el criterio proporcionado.
     * - Retorna una respuesta HTTP 200 (OK) si la consulta es exitosa.
     * - Retorna una respuesta HTTP 400 (Bad Request) si el criterio de orden no es válido.
     * - Retorna una respuesta HTTP 500 (Internal Server Error) en caso de un error inesperado.
     */
    @GetMapping("/order")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesOrdenados(
            @RequestParam(value = "orden", defaultValue = "nombre") String orden) {
        try {
            List<EstudianteDTO> estudiantes = estudianteServicio.getEstudiantesOrdenados(orden);
            return ResponseEntity.ok(estudiantes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Guarda un objeto Estudiante en la base de datos.
     *
     * @param estudiante El objeto Estudiante enviado en el cuerpo de la solicitud.
     * @return ResponseEntity con el estado HTTP y el estudiante guardado o un mensaje de error si falla.
     *
     * Respuestas:
     * - 200 OK: Si el estudiante fue guardado correctamente.
     * - 400 BAD REQUEST: Si ocurre un error al guardar el estudiante (por ejemplo, campos inválidos).
     */
     @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.save(estudiante));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    /**
     * Obtiene una lista de estudiantes filtrados por género.
     *
     * @param genero El género por el cual se filtrarán los estudiantes.
     * @return ResponseEntity con la lista de EstudianteDTO o un error si no se encuentran resultados.
     *
     * Respuestas:
     * - 200 OK: Si se encuentran estudiantes con el género especificado.
     * - 404 NOT FOUND: Si no se encuentran estudiantes con el género especificado.
     * - 400 BAD REQUEST: Si el parámetro de género es inválido.
     * - 500 INTERNAL SERVER ERROR: Si ocurre un error en el servidor.
     */
    @GetMapping("/genero")
    public ResponseEntity<List<EstudianteDTO>> getEstudianteByGenero(
            @RequestParam(value = "genero", defaultValue = "masculino") String genero) {
        try {
            List<EstudianteDTO> estudiantes = estudianteServicio.getEstudianteByGenero(genero);
            return ResponseEntity.ok(estudiantes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    /**
     * Endpoint para obtener la lista de estudiantes inscriptos a una carrera y filtrados por la ciudad de recidencia.
     *
     * @param carrera Indica el nombre de la carrera en la que se va a buscar la lista de estudiantes.
     * @param ciudad Indica la ciudad por la que se va a filtrar la lista de estudiantes.
     *
     * @return ResponseEntity que contiene una lista de objetos EstudianteDTO filtrados según el criterio dado.
     * - Retorna una respuesta HTTP 200 (OK) si la consulta es exitosa.
     * - Retorna una respuesta HTTP 400 (Bad Request) si el criterio de orden no es válido.
     * - Retorna una respuesta HTTP 500 (Internal Server Error) en caso de un error inesperado.
     */
    @GetMapping("/filter")
    public ResponseEntity<List<EstudianteDTO>> getEstudianteByCarreraFilterCiudad(
            @RequestParam(value = "carrera") String carrera,
            @RequestParam(value = "ciudad") String ciudad) {
        try {
            List<EstudianteDTO> estudiantes = estudianteServicio.getEstudianteByCarreraFilterCiudad(carrera, ciudad);
            return ResponseEntity.ok(estudiantes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
