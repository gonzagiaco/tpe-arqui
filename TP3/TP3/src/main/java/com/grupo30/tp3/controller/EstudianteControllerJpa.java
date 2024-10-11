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
@RequestMapping("/Estudiante")
public class EstudianteControllerJpa {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping
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
    @GetMapping("/estudiantes/nrolibreta/{nrolibreta}")
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

    /** aprovecho para D&V
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
    @GetMapping("/estudiantes")
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


    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.save(estudiante));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

}
