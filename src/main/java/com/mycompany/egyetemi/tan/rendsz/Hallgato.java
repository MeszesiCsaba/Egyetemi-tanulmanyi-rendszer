/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.egyetemi.tan.rendsz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

/**
 *
 * @author mcsab
 */
public final class Hallgato extends Szemely {
    private final List<Beiratkozas> beiratkozasok = new ArrayList<>();

    public Hallgato(String azonosito, String nev, String email) {
        super(azonosito, nev, email);
    }

    public Beiratkozas beiratkozik(Kurzus kurzus, Felev felev) {
        if (kurzus == null || felev == null) {
            throw new IllegalArgumentException("A kurzus és a félév kötelező.");
        }

        boolean marFelvette = beiratkozasok.stream()
                .anyMatch(b -> b.getKurzus().equals(kurzus)
                        && b.getFelev().equals(felev));

        if (marFelvette) {
            throw new IllegalStateException(
                    "Ezt a kurzust ebben a félévben már felvette.");
        }

        kurzus.ellenorizFerohely();

        Beiratkozas uj = new Beiratkozas(this, kurzus, felev);
        beiratkozasok.add(uj);
        kurzus.hozzaad(uj);
        return uj;
    }

    public List<Beiratkozas> getBeiratkozasok() {
        return List.copyOf(beiratkozasok);
    }

    public OptionalDouble getSulyozottAtlag() {
        var jegyesek = beiratkozasok.stream()
                .filter(b -> b.getJegy().isPresent())
                .toList();

        int osszKredit = jegyesek.stream()
                .mapToInt(b -> b.getKurzus().getKredit())
                .sum();

        if (osszKredit == 0) {
            return OptionalDouble.empty();
        }

        double sulyozottOsszeg = jegyesek.stream()
                .mapToDouble(b -> b.getJegy().orElseThrow()
                        * b.getKurzus().getKredit())
                .sum();

        return OptionalDouble.of(sulyozottOsszeg / osszKredit);
    }

    public int getMegszerzettKredit() {
        return beiratkozasok.stream()
                .filter(b -> b.getJegy().orElse(0) >= 5)
                .mapToInt(b -> b.getKurzus().getKredit())
                .sum();
    }

    public static List<Hallgato> legjobbHarom(Kurzus kurzus) {
        if (kurzus == null) {
            throw new IllegalArgumentException("A kurzus kötelező.");
        }

        return kurzus.getBeiratkozasok().stream()
                .filter(b -> b.getJegy().isPresent())
                .sorted(Comparator
                        .comparingInt((Beiratkozas b) ->
                                b.getJegy().orElseThrow())
                        .reversed()
                        .thenComparing(b ->
                                b.getHallgato().getAzonosito()))
                .limit(3)
                .map(Beiratkozas::getHallgato)
                .toList();
    }

    @Override
    public String toString() {
        return super.toString()
                + " beiratkozások=" + beiratkozasok.size();
    }
}