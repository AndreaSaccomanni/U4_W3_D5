package org.example;

import org.example.Enumeration.Periodicita;

public class Rivista extends Articolo {
    private Periodicita periodicita;

    public Rivista(int ISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(ISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Rivista() {
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }


    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                ", ISBN=" + ISBN +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
