/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.egyetemi.tan.rendsz;

/**
 *
 * @author mcsab
 */
public record Felev(int ev, int szam) implements Comparable<Felev> {
    public Felev {
        if (ev < 1900 || ev > 2200) {
            throw new IllegalArgumentException("Érvénytelen év.");
        }
        if (szam < 1 || szam > 2) {
            throw new IllegalArgumentException("A félév sorszáma 1 vagy 2 lehet.");
        }
    }

    @Override
    public int compareTo(Felev masik) {
        int evOsszehasonlitas = Integer.compare(ev, masik.ev);
        return evOsszehasonlitas != 0
                ? evOsszehasonlitas
                : Integer.compare(szam, masik.szam);
    }

    @Override
    public String toString() {
        return ev + "/" + szam;
    }
}
