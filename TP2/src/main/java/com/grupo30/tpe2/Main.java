package com.grupo30.tpe2;

import com.grupo30.dao.EstudianteDAO;
import com.grupo30.entidades.Carrera;
import com.grupo30.entidades.Estudia;
import com.grupo30.entidades.Estudiante;
import com.grupo30.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("grupo30");
        EntityManager em = emf.createEntityManager();

        EstudianteRepository estudianteRepository = new EstudianteRepository(em);

        em.getTransaction().begin();

       Carrera carrera1 = new Carrera("TUDAI");
       em.persist(carrera1);
       Carrera carrera2 = new Carrera("Ingenieria en Sistemas");
       em.persist(carrera2);

       Estudiante estudiante1 = new Estudiante(1, "Fermin", "Robledo", 20, "M", "Tandil");
       estudianteRepository.darDeAltaEstudiante(estudiante1);

       Estudiante estudiante2 = new Estudiante(2, "Juan", "Wagner", 21, "M", "Olavarria");
       estudianteRepository.darDeAltaEstudiante(estudiante2);

       Estudia estudia1 = new Estudia(estudiante1, carrera1, false, 2);
       em.persist(estudia1);

       Estudia estudia2 = new Estudia(estudiante2, carrera2, false, 2);
       em.persist(estudia2);

       em.getTransaction().commit();

       //prueba


    }
}
