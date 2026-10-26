/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.egyetemi.tan.rendsz;

import java.util.List;

/**
 *
 * @author mcsab
 */
public final class Oktato extends Szemely {
    public Oktato(String azonosito, String nev, String email) {
        super(azonosito, nev, email);
    }

    public int getTanitottKreditek(List<Kurzus> kurzusok) {
        if (kurzusok == null) {
            throw new IllegalArgumentException("A kurzuslista nem lehet null.");
        }

        return kurzusok.stream()
                .filter(kurzus -> kurzus.getOktato().equals(this))
                .mapToInt(Kurzus::getKredit)
                .sum();
    }
}
