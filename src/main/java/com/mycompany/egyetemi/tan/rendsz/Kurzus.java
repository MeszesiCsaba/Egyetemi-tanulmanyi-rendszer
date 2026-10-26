/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.egyetemi.tan.rendsz;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcsab
 */
public final class Kurzus {
    private final String kod;
    private final String nev;
    private final Oktato oktato;
    private final int kredit;
    private final int maximalisLetszam;
    private final List<Beiratkozas> beiratkozasok = new ArrayList<>();

    public Kurzus(String kod, String nev, Oktato oktato,
                  int kredit, int maximalisLetszam) {
        this.kod = kotelezo(kod, "Kód");
        this.nev = kotelezo(nev, "Név");

        if (oktato == null) {
            throw new IllegalArgumentException("Az oktató kötelező.");
        }
        if (kredit < 1 || maximalisLetszam < 1) {
            throw new IllegalArgumentException("A kredit és a létszám legyen pozitív.");
        }

        this.oktato = oktato;
        this.kredit = kredit;
        this.maximalisLetszam = maximalisLetszam;
    }

    private static String kotelezo(String ertek, String mezo) {
        if (ertek == null || ertek.isBlank()) {
            throw new IllegalArgumentException(mezo + " nem lehet üres.");
        }
        return ertek.trim();
    }

    public String getKod() { return kod; }
    public String getNev() { return nev; }
    public Oktato getOktato() { return oktato; }
    public int getKredit() { return kredit; }
    public int getMaximalisLetszam() { return maximalisLetszam; }
    public List<Beiratkozas> getBeiratkozasok() {
        return List.copyOf(beiratkozasok);
    }

    void ellenorizFerohely() {
        if (beiratkozasok.size() >= maximalisLetszam) {
            throw new IllegalStateException("A kurzus betelt.");
        }
    }

    void hozzaad(Beiratkozas beiratkozas) {
        beiratkozasok.add(beiratkozas);
    }

    public double getAtmenesiArany() {
        long osztalyozott = beiratkozasok.stream()
                .filter(b -> b.getJegy().isPresent())
                .count();

        if (osztalyozott == 0) {
            return 0.0;
        }

        long atment = beiratkozasok.stream()
                .filter(b -> b.getJegy().orElse(0) >= 5)
                .count();

        return (double) atment / osztalyozott;
    }

    @Override
    public boolean equals(Object masik) {
        return this == masik
                || (masik instanceof Kurzus kurzus
                && kod.equals(kurzus.kod));
    }

    @Override
    public int hashCode() {
        return kod.hashCode();
    }

    @Override
    public String toString() {
        return "Kurzus{" + kod + ", " + nev + ", " + kredit
                + " kredit, oktató=" + oktato.getAzonosito() + "}";
    }
}
