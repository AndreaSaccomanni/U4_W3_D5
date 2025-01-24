package org.example.dao;

import org.example.Articolo;
import org.example.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ArticoloDAO {
    private EntityManager em;

    public ArticoloDAO(EntityManager em) {
        this.em = em;
    }


    //metodo per aggiugnere un articolo al catalogo
    public void save(Articolo articolo) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(articolo);
            t.commit();
            System.out.println("Articolo - " + articolo.getTitolo() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo per ceracre un articolo nel catalogo tramite id
    public Articolo findById(int id) {
        Articolo found = em.find(Articolo.class, id);


        return found;
    }

    //metodo per rimuovere un articolo dal catalogo tramite id
    public void remove(int codiceISBN) {
        try {
            Articolo found = findByISBN(codiceISBN);
            if (found != null) {
                EntityTransaction t = em.getTransaction();
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Articolo eliminato: " + found);
            } else System.out.println("Articolo non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // metoto per cercare un articolo tramite codice ISBN
    public Articolo findByISBN(int codiceISBN) {
        Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.ISBN = :isbn");
        q.setParameter("isbn", codiceISBN);
        return (Articolo) q.getSingleResult();
    }

    //metodo per cercare articolo tramite anno di pubblicazione
    public List<Articolo> findByAnnoPubblicazione(int annoPubblicazione) {
        Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.annoPubblicazione = :annoPubblicazione");
        q.setParameter("annoPubblicazione", annoPubblicazione);
        return q.getResultList();
    }

    //metodo per cercare tramite autore
    public List<Libro> ricercaTramiteAutore(String autore) {
        Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.autore = :autore");
        q.setParameter("autore",   autore );
        return q.getResultList();
    }

    //metodo per cercare tramite titolo o parte di esso
    public List<Articolo> ricercaTramiteTitolo(String titolo) {
        Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.titolo LIKE :titolo");
        q.setParameter("titolo", "%" +  titolo + "%");
        return q.getResultList();
    }


}
