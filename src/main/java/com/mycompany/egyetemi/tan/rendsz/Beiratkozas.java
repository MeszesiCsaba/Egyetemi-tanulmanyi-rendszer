/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.egyetemi.tan.rendsz;

import java.util.Optional;

/**
 *
 * @author mcsab
 */
public final class Beiratkozas {
    private final Hallgato hallgato;
    private final Kurzus kurzus;
    private final Felev felev;
    private Integer jegy;

    Beiratkozas(Hallgato hallgato, Kurzus kurzus, Felev felev) {
        this.hallgato = hallgato;
        this.kurzus = kurzus;
        this.felev = felev;
    }

    public Hallgato getHallgato() { return hallgato; }
    public Kurzus getKurzus() { return kurzus; }
    public Felev getFelev() { return felev; }
    public Optional<Integer> getJegy() { return Optional.ofNullable(jegy); }

    public void jegyetRogzit(int jegy) {
        if (jegy < 1 || jegy > 10) {
            throw new IllegalArgumentException("A jegy 1 és 10 közé essen.");
        }
        this.jegy = jegy;
    }

    @Override
    public String toString() {
        return "Beiratkozas{" + hallgato.getAzonosito()
                + ", " + kurzus.getKod()
                + ", " + felev
                + ", jegy=" + getJegy() + "}";
    }
}
