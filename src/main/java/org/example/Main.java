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

        // dopo aver istanziato i vari  articoli, e salvati nel database, con il metodo findById salvo dento i vari oggetti, le istanze gia presenti nel DB

        Articolo libro1 = articoloDAO.findById(19);
        Articolo libro2 = articoloDAO.findById(21);
        Articolo libro3 = articoloDAO.findById(22);

//        articoloDAO.save(libro1);
//        articoloDAO.save(libro2);
//        articoloDAO.save(libro3);


        Articolo rivista1 = articoloDAO.findById(16);
        Articolo rivista2 = articoloDAO.findById(17);
        Articolo rivista3 = articoloDAO.findById(18);
//        articoloDAO.save(rivista1);
//        articoloDAO.save(rivista2);
//        articoloDAO.save(rivista3);


        Utente utente1 = utenteDAO.findById(1);
        Utente utente2 = utenteDAO.findById(2);
        Utente utente3 = utenteDAO.findById(3);

//        utenteDAO.save(utente1);
//        utenteDAO.save(utente2);
//        utenteDAO.save(utente3);

//        creazione di prestiti con elementi gia presenti nel database

        Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2025, 1, 20), LocalDate.of(2025, 2, 20), null);
        Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.of(2025, 1, 22), LocalDate.of(2025, 2, 22), null);
        Prestito prestito3 = new Prestito(utente3, libro3, LocalDate.of(2025, 1, 25), LocalDate.of(2025, 2, 25), null);

//        prestitoDAO.save(prestito1);
        prestitoDAO.save(prestito2);
        prestitoDAO.save(prestito3);

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
            System.out.println("Articolo non trovato!");
        }

        //RICERCA TRAMITE ANNO DI PUBBLICAZIONE
        List<Articolo> articoliTrovatiConAnnoPubblicazione = articoloDAO.findByAnnoPubblicazione(1954);
        if (articoliTrovatiConAnnoPubblicazione != null) {
            System.out.println("Articoli trovati tramite l'anno di pubblicazione: ");
            articoliTrovatiConAnnoPubblicazione.forEach(System.out::println);
        } else {
            System.out.println("Articolo non trovato!");
        }

        //RICERCA TRAMITE AUTORE
        List<Libro> articoliTrovatiTramiteAutore = articoloDAO.ricercaTramiteAutore("Dante Alighieri");
        if (articoliTrovatiTramiteAutore != null) {
            System.out.println("Libri trovati tramite autore: ");
            articoliTrovatiTramiteAutore.forEach(System.out::println);
        } else {
            System.out.println("Nessun libro trovato!");
        }

        //RICERCA TRAMITE TITOLO O PARTE DI ESSO
        List<Articolo> articoliTrovatiTramiteTitolo = articoloDAO.ricercaTramiteTitolo("Java");
        if (articoliTrovatiTramiteTitolo != null) {
            articoliTrovatiTramiteTitolo.forEach(System.out::println);
        } else {
            System.out.println("Nessun articolo trovato!");
        }


    }


}
