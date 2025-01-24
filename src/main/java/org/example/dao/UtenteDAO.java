package org.example.dao;

import org.example.Articolo;
import org.example.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare un utente
    public void save(Utente utente) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(utente);
            t.commit();
            System.out.println("Utente - " + utente.getNome() + " " + utente.getCognome() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo per trovare un utente per ID
    public Utente findById(int id) {
        return em.find(Utente.class, id);
    }

    // Metodo per rimuovere un utente
    public void remove(int id) {
        try {
            EntityTransaction t = em.getTransaction();
            Utente found = em.find(Utente.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Utente eliminato");
            } else System.out.println("Utente non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

