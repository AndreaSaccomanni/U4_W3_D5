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

        // dopo aver istanziato i vari  articoli, e salvati nel database, con il metodo findById, salvo dento i vari oggetti, le istanze gia presenti nel DB

        Articolo libro1 = articoloDAO.findById(19);
        Articolo libro2 = articoloDAO.findById(21);
        Articolo libro3 = articoloDAO.findById(22);

//        Articolo libro5 = new Libro(654321, "Lo Hobbit", 1937, 310, "J.R.R. Tolkien", "Fantasy");
//        Articolo rivista4 = new Rivista(987654, "Scientific American", 1954, 60, Periodicita.MENSILE);

//        articoloDAO.save(libro4);
//        articoloDAO.save(libro5);
//        articoloDAO.save(rivista4);


        Articolo libro5 =  articoloDAO.findById(24);
        Articolo rivista4 =articoloDAO.findById(25);



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

//        utenteDAO.remove(4);
//        utenteDAO.remove(5);
//        utenteDAO.remove(6);
//        utenteDAO.remove(7);

//        creazione di prestiti con elementi gia presenti nel database

        Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.of(2025, 1, 20), null);
        Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.of(2025, 1, 22), null);
        Prestito prestito3 = new Prestito(utente3, libro3, LocalDate.of(2025, 1, 25), null);
        Prestito prestito4 = new Prestito(utente1, rivista3, LocalDate.of(2024, 7, 19), null);
//        prestitoDAO.save(prestito1);
//        prestitoDAO.save(prestito2);
//        prestitoDAO.save(prestito3);
//        prestitoDAO.save(prestito4);
        //---- APPLICAZIONE DEI METODI -----

        //AGGIUNTA ARTICOLO AL CATALOGO
        //Articolo articoloAggiunto  = articoloDAO.save(libro1);

        //RIMOZIONE ELEMENTO TRAMITE CODICE ISBN
        //articoloDAO.remove(123456);
//        articoloDAO.removeById(23);

        //RICERCA TRAMITE CODICE ISBN
        Articolo articoloTrovatoConISBN = articoloDAO.findByISBN(123456);
        if (articoloTrovatoConISBN != null) {
            System.out.println("ARTICOLO TROVATO TRAMITE ISBN: " + articoloTrovatoConISBN);
        } else {
            System.out.println("Articolo non trovato!");
        }

        //RICERCA TRAMITE ANNO DI PUBBLICAZIONE
        List<Articolo> articoliTrovatiConAnnoPubblicazione = articoloDAO.findByAnnoPubblicazione(1954);
        if (articoliTrovatiConAnnoPubblicazione != null) {
            System.out.println("ARTICOLI TROVATI TRAMITE L'ANNI DI PUBBLICAZIONE: ");
            articoliTrovatiConAnnoPubblicazione.forEach(System.out::println);
        } else {
            System.out.println("Articolo non trovato!");
        }

        //RICERCA TRAMITE AUTORE
        List<Libro> articoliTrovatiTramiteAutore = articoloDAO.ricercaTramiteAutore("J.R.R. Tolkien");
        if (articoliTrovatiTramiteAutore != null) {
            System.out.println("LIBRI TROVATI TRAITE AUTORE: ");
            articoliTrovatiTramiteAutore.forEach(System.out::println);
        } else {
            System.out.println("Nessun libro trovato!");
        }

        //RICERCA TRAMITE TITOLO O PARTE DI ESSO
        List<Articolo> articoliTrovatiTramiteTitolo = articoloDAO.ricercaTramiteTitolo("Signore");
        if (articoliTrovatiTramiteTitolo != null) {
            System.out.println("ARTICOLI TROVATI TRAMITE IL TITOLO: ");
            articoliTrovatiTramiteTitolo.forEach(System.out::println);
        } else {
            System.out.println("Nessun articolo trovato!");
        }

        //RICERCA ELEMENT IN PRESTITO TRAMITE LA TESSERA UTENTE
        List<Articolo> articoliTrovatiTramiteTessera = articoloDAO.ricercaElementiInPrestitoTramiteTessera("TESSERA001");
        if (articoliTrovatiTramiteTessera != null) {
            System.out.println("ARTICOLI TROVATI TRAMITE LA TESSERA  IUTENTE: ");
            articoliTrovatiTramiteTessera.forEach(System.out::println);
        } else {
            System.out.println("Nessun articolo trovato!");
        }

        //RICERCA PRESTITI SCADUTI E NON RESTITUITI
        List<Prestito> prestitiScadutiDaRestituire = prestitoDAO.ricercaPrestitiScaduti();
        if (prestitiScadutiDaRestituire != null) {
            System.out.println("PRESTITI SCADUTI E NON RESTITUITI: ");
            prestitiScadutiDaRestituire.forEach(System.out::println);
        } else {
            System.out.println("Nessun prestito trovato!");
        }
    }


}
