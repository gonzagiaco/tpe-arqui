package com.grupo30.tpe2;

import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudiante;
import com.grupo30.entidades.Estudia;
import com.grupo30.repository.CarreraRepository;
import com.grupo30.repository.EstudianteRepository;
import com.grupo30.repository.EstudiaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Inicialización del EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("grupo30");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        CarreraRepository repositorioCarrera = new CarreraRepository(em);
        EstudianteRepository repositorioEstudiante = new EstudianteRepository(em);
        EstudiaRepository repositorioEstudia = new EstudiaRepository(em);
        /*
        // Crear y persistir algunas carreras
        Carrera carrera1 = new Carrera("Ingeniería en Sistemas");
        Carrera carrera2 = new Carrera("Licenciatura en Administración");

        repositorioCarrera.darDeAltaCarrera(carrera1);
        repositorioCarrera.darDeAltaCarrera(carrera2);


        // Crear y persistir algunos estudiantes
        Estudiante estudiante1 = new Estudiante(12345678, "Juan", "Pérez", 20, "masculino", "Buenos Aires");
        Estudiante estudiante2 = new Estudiante(87654321, "María", "Gómez", 22, "femenino", "Córdoba");

        repositorioEstudiante.darDeAltaEstudiante(estudiante1);
        repositorioEstudiante.darDeAltaEstudiante(estudiante2);


        // Crear y persistir relaciones de estudios
        Estudia estudia1 = new Estudia(estudiante1, carrera1, false, Date.valueOf("2022-03-01"));
        Estudia estudia2 = new Estudia(estudiante1, carrera2, true, Date.valueOf("2021-08-15"));
        Estudia estudia3 = new Estudia(estudiante2, carrera1, false, Date.valueOf("2023-01-10"));

        repositorioEstudia.matricularEstudiante(estudia1);
        repositorioEstudia.matricularEstudiante(estudia2);
        repositorioEstudia.matricularEstudiante(estudia2);

        */
        System.out.println(repositorioEstudiante.recuperarEstudiantes("nombre"));
        System.out.println("--------------");
        System.out.println(repositorioEstudiante.recuperarEstudianteNroLibreta(2));
        System.out.println("--------------");
        System.out.println(repositorioEstudiante.recuperarEstudianteGenero("masculino"));
        System.out.println("--------------");
        System.out.println(repositorioCarrera.selectCarrerasConEstudiantesOrdenadas());
        System.out.println("--------------");
        System.out.println(repositorioEstudiante.selectEstudiantesPorCarrera(repositorioCarrera.buscarCarreraPorID(1), "Buenos Aires"));
        System.out.println("--------------");



        em.getTransaction().commit();
        // Cerrar el EntityManager
        em.close();
        emf.close();

        System.out.println("Datos creados exitosamente!");
    }
}