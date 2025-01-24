package org.example;

import org.example.Enumeration.Periodicita;
import org.example.dao.ArticoloDAO;
import org.example.dao.PrestitoDAO;
import org.example.dao.UtenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;


public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        ArticoloDAO articoloDAO = new ArticoloDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);

        // ---- CREAZIONE E SALVATAGGIO DI ARTICOLI NEL CATALOGO ----

        Articolo libro1 = articoloDAO.findById(19);
        Articolo libro2 = new Libro(789012, "1984", 1949, 328, "George Orwell", "Distopia");
        Articolo libro3 = new Libro(345678, "La Divina Commedia", 1320, 1000, "Dante Alighieri", "Poesia");

//        articoloDAO.save(libro1);
//        articoloDAO.save(libro2);
//        articoloDAO.save(libro3);


        Rivista rivista1 = new Rivista(111222, "Time", 2023, 120, Periodicita.SETTIMANALE);
        Rivista rivista2 = new Rivista(333444, "National Geographic", 2022, 100, Periodicita.MENSILE);
        Rivista rivista3 = new Rivista(555666, "L'Officiel", 2021, 150, Periodicita.SEMESTRALE);
//        articoloDAO.save(rivista1);
//        articoloDAO.save(rivista2);
//        articoloDAO.save(rivista3);

        //----CREAZIONE E SALVATAGGIO DI UTENTI E PRESTITI-----

        Utente utente1 = utenteDAO.findById(1);
        Utente utente2 = new Utente("Luigi", "Bianchi", LocalDate.of(1990, 8, 20), "TESSERA002");
        Utente utente3 = new Utente("Giulia", "Verdi", LocalDate.of(1995, 12, 10), "TESSERA003");

        //utenteDAO.save(utente1);
//        utenteDAO.save(utente2);
//        utenteDAO.save(utente3);


        Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2025, 1, 20), LocalDate.of(2025, 2, 20), null);
        Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.of(2025, 1, 22), LocalDate.of(2025, 2, 22), null);
        Prestito prestito3 = new Prestito(utente3, libro3, LocalDate.of(2025, 1, 25), LocalDate.of(2025, 2, 25), null);

//        prestitoDAO.save(prestito1);
//        prestitoDAO.save(prestito2);
//        prestitoDAO.save(prestito3);

        //---- APPLICAZIONE DEI METODI -----

        //AGGIUNTA ARTICOLO AL CATALOGO
        //Articolo articoloAggiunto  = articoloDAO.save(libro1);

        //RIMOZIONE ELEMENTO TRAMITE CODICE ISBN
        //articoloDAO.remove(789012);

        //RICERCA TRAMITE CODICE ISBN
        Articolo articoloTrovatoConISBN = articoloDAO.findByISBN(123456);
        if (articoloTrovatoConISBN != null) {
            System.out.println("Articolo trovato tramite ISBN: " + articoloTrovatoConISBN);
        } else {
            System.out.println("Articolo non trovato.");
        }

        //RICERCA TRAMITE ANNO DI PUBBLICAZIONE
        List<Articolo> articoliTrovatiConAnnoPubblicazione = articoloDAO.findByAnnoPubblicazione(1954);
        if (articoliTrovatiConAnnoPubblicazione != null) {
            System.out.println("Articoli trovati tramite l'anno di pubblicazione: ");
            articoliTrovatiConAnnoPubblicazione.forEach(System.out::println);
        } else {
            System.out.println("Articolo non trovato.");
        }

        //RICERCA TRAMITE AUTORE
        List<Libro> articoliTrovatiTramiteAutore = articoloDAO.ricercaTramiteAutore("Dante Alighieri");
        if (articoliTrovatiTramiteAutore != null) {
            System.out.println("Libri trovati tramite autore: ");
            articoliTrovatiTramiteAutore.forEach(System.out::println);
        } else {
            System.out.println("Nessun libro trovato.");
        }


    }


}
