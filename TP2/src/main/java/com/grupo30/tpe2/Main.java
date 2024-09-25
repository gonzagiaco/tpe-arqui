package com.grupo30.tpe2;

import com.grupo30.dao.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("grupo30");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Estudiante est = new Estudiante(45620648, "Fermin", "Robledo", 20, "Tandil");
        em.persist(est);


    }
}
