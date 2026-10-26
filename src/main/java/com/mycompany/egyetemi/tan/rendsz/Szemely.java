/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.egyetemi.tan.rendsz;

import java.util.Objects;

/**
 *
 * @author mcsab
 */
public abstract class Szemely {
    private final String azonosito;
    private String nev;
    private String email;

    protected Szemely(String azonosito, String nev, String email) {
        this.azonosito = kotelezo(azonosito, "Azonosító");
        setNev(nev);
        setEmail(email);
    }

    private static String kotelezo(String ertek, String mezo) {
        if (ertek == null || ertek.isBlank()) {
            throw new IllegalArgumentException(mezo + " nem lehet üres.");
        }
        return ertek.trim();
    }

    public String getAzonosito() { return azonosito; }
    public String getNev() { return nev; }

    public void setNev(String nev) {
        this.nev = kotelezo(nev, "Név");
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        String ellenorzott = kotelezo(email, "E-mail");
        if (!ellenorzott.contains("@")) {
            throw new IllegalArgumentException("Érvénytelen e-mail cím.");
        }
        this.email = ellenorzott;
    }

    @Override
    public final boolean equals(Object masik) {
        return this == masik
                || (masik instanceof Szemely szemely
                && azonosito.equals(szemely.azonosito));
    }

    @Override
    public final int hashCode() {
        return Objects.hash(azonosito);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{" + azonosito + ", " + nev + ", " + email + "}";
    }
}