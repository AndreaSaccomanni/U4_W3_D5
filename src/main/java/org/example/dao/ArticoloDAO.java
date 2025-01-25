package org.example.dao;

import org.example.Articolo;
import org.example.Libro;
import org.example.Utente;

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
            Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.ISBN = :isbn");
            q.setParameter("isbn", articolo.getISBN());
            List<Articolo> risultati = q.getResultList();
            if (!risultati.isEmpty()) {
                // Se esiste già un articolo con lo stesso ISBN
                System.out.println("Impossibile aggiungere l'articolo. ISBN già presente: " + articolo.getISBN());
                return;
            }

            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(articolo);
            t.commit();
            System.out.println("Articolo " + articolo.getTitolo() + "creato con successo!");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }


    //metodo per ceracre un articolo nel catalogo tramite id
    public Articolo findById(int id) {
        Articolo found = em.find(Articolo.class, id);


        return found;
    }

    //metodo per rimuovere un articolo dal catalogo tramite ISBN
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

    // metodo per rimuovere elemento tramite id
    public void removeById(int id) {
        try {
            // Trova l'entità tramite il suo ID
            Articolo found = em.find(Articolo.class, id);
            if (found != null) {
                EntityTransaction t = em.getTransaction();
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Articolo eliminato: " + found.getTitolo());
            } else {
                System.out.println("Articolo non trovato");
            }
        } catch (Exception e) {
            System.out.println("Errore durante la rimozione: " + e.getMessage());
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
        em.clear();
        Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.autore = :autore");
        q.setParameter("autore", autore);
        return q.getResultList();
    }

    //metodo per cercare tramite titolo o parte di esso
    public List<Articolo> ricercaTramiteTitolo(String titolo) {
        Query q = em.createQuery("SELECT a FROM Articolo a WHERE a.titolo LIKE :titolo");
        q.setParameter("titolo", "%" + titolo + "%");
        return q.getResultList();
    }

    //metodo per cercare elemeti in prestito dato il numero di tessera utente
    public List<Articolo> ricercaElementiInPrestitoTramiteTessera(String numeroTessera) {
        try {
            Utente u = (Utente) em.createQuery("SELECT u FROM Utente u WHERE u.numeroTessera = :numeroTessera")
                    .setParameter("numeroTessera", numeroTessera)
                    .getSingleResult();

            if (u != null) {
                int idUtenteTrovato = u.getId();

                // Recupera gli articoli in prestito (prestiti con data restituzione effettiva null)
                Query q = em.createQuery("SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.id = :idUtenteTrovato AND p.dataRestituzioneEffettiva IS NULL");
                q.setParameter("idUtenteTrovato", idUtenteTrovato);

                // Ritorna la lista di articoli
                return q.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        return new ArrayList<>(); // se non trova niente
    }

}
