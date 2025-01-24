package org.example.dao;

import org.example.Articolo;
import org.example.Prestito;
import org.example.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class PrestitoDAO {

    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare un prestito
    public void save(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(prestito);
            t.commit();
            System.out.println("Prestito creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo per trovare un prestito per ID
    public Prestito findById(int id) {
        return em.find(Prestito.class, id);
    }

    // Metodo per rimuovere un prestito
    public void remove(int id) {
        try {
            EntityTransaction t = em.getTransaction();
            Prestito found = em.find(Prestito.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Prestito eliminato");
            } else System.out.println("Prestito non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

