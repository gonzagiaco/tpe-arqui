package com.grupo30.tpe2;

import com.grupo30.dao.CarreraDAO;
import com.grupo30.dao.EstudiaDAO;
import com.grupo30.dao.EstudianteDAO;
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



        /* LOS DATOS SON CARGADOS MEDIANTE EL ARCHIVO CSV AUNQUE ESTEN
        IMPLEMENTADOS LOS SIGUIENTES METODOS


        Carrera carrera1 = new Carrera("Ingeniería en Sistemas");
        repositorioCarrera.darDeAltaCarrera(carrera1);

        Estudiante estudiante1 = new Estudiante(12345678, "Juan", "Pérez", 20, "masculino", "Buenos Aires");
        repositorioEstudiante.darDeAltaEstudiante(estudiante1);


        Estudia estudia1 = new Estudia(estudiante1, carrera1, false, Date.valueOf("2022-03-01"));
        repositorioEstudia.matricularEstudiante(estudia1);

        */

        // CARGA DE DATOS UTILIZANDO CSV

        /*EstudianteDAO estudianteDAO = new EstudianteDAO(em);
        estudianteDAO.insertAll();

        CarreraDAO carreraDAO = new CarreraDAO(em);
        carreraDAO.insertAll();

        EstudiaDAO estudiaDAO = new EstudiaDAO(em);
        estudiaDAO.insertAll();*/


        System.out.println("Estudiantes ordenados por nombre:");
        System.out.println(repositorioEstudiante.recuperarEstudiantes("nombre"));
        System.out.println("--------------");

        System.out.println("Estudiante por numero de libreta:");
        System.out.println(repositorioEstudiante.recuperarEstudianteNroLibreta(72976));
        System.out.println("--------------");

        System.out.println("Estudiantes filtrados por genero:");
        System.out.println(repositorioEstudiante.recuperarEstudianteGenero("Masculino"));
        System.out.println("--------------");

        System.out.println("Carreras ordenadas por cantidad de estudiantes:");
        System.out.println(repositorioCarrera.selectCarrerasConEstudiantesOrdenadas());
        System.out.println("--------------");

        System.out.println("Estudiantes de una carrera (TUDAI) filtrados por ciudad de residencia (RAUCH):");
        System.out.println(repositorioEstudiante.selectEstudiantesPorCarrera(repositorioCarrera.buscarCarreraPorID(1), "Rauch"));
        System.out.println("--------------");


        // Cerrar el EntityManager
        em.getTransaction().commit();
        em.close();
        emf.close();

        System.out.println("Datos creados exitosamente!");
    }
}