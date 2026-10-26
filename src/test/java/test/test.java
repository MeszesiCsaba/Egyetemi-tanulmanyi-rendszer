/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.mycompany.egyetemi.tan.rendsz.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mcsab
 */
class TanulmanyiRendszerTest {
    private final Oktato oktato =
            new Oktato("O1", "Oktató", "oktato@example.hu");
    private final Felev felev = new Felev(2026, 1);

    private Kurzus kurzus(String kod, int kredit, int letszam) {
        return new Kurzus(kod, kod, oktato, kredit, letszam);
    }

    @Test
    void teltKurzusraNemLehetBeiratkozni() {
        Kurzus kurzus = kurzus("K1", 3, 1);
        new Hallgato("H1", "Anna", "anna@example.hu")
                .beiratkozik(kurzus, felev);

        Hallgato masik = new Hallgato("H2", "Bela", "bela@example.hu");
        assertThrows(IllegalStateException.class,
                () -> masik.beiratkozik(kurzus, felev));
    }

    @Test
    void kurzusEgyFelevbenCsakEgyszerVehetoFel() {
        Hallgato hallgato =
                new Hallgato("H1", "Anna", "anna@example.hu");
        Kurzus kurzus = kurzus("K1", 3, 5);

        hallgato.beiratkozik(kurzus, felev);

        assertThrows(IllegalStateException.class,
                () -> hallgato.beiratkozik(kurzus, felev));
    }

    @Test
    void jegyCsakEgyEsTizKozottLehet() {
        Beiratkozas beiratkozas =
                new Hallgato("H1", "Anna", "anna@example.hu")
                        .beiratkozik(kurzus("K1", 3, 5), felev);

        assertThrows(IllegalArgumentException.class,
                () -> beiratkozas.jegyetRogzit(11));

        beiratkozas.jegyetRogzit(5);
        assertEquals(5, beiratkozas.getJegy().orElseThrow());
    }

    @Test
    void sulyozottAtlagKredittelSulyoz() {
        Hallgato hallgato =
                new Hallgato("H1", "Anna", "anna@example.hu");

        Beiratkozas ketKredites =
                hallgato.beiratkozik(kurzus("K1", 2, 5), felev);
        Beiratkozas negyKredites =
                hallgato.beiratkozik(kurzus("K2", 4, 5), felev);

        ketKredites.jegyetRogzit(10);
        negyKredites.jegyetRogzit(5);

        assertEquals(20.0 / 3,
                hallgato.getSulyozottAtlag().orElseThrow(),
                0.000001);
    }

    @Test
    void csakAtmenoJegyhezJarKredit() {
        Hallgato hallgato =
                new Hallgato("H1", "Anna", "anna@example.hu");

        hallgato.beiratkozik(kurzus("K1", 3, 5), felev)
                .jegyetRogzit(5);
        hallgato.beiratkozik(kurzus("K2", 4, 5), felev)
                .jegyetRogzit(4);

        assertEquals(3, hallgato.getMegszerzettKredit());
    }

    @Test
    void kurzusAtmenesiAranyaAzOsztalyozottakbolSzamol() {
        Kurzus kurzus = kurzus("K1", 3, 5);

        new Hallgato("H1", "Anna", "anna@example.hu")
                .beiratkozik(kurzus, felev).jegyetRogzit(5);
        new Hallgato("H2", "Bela", "bela@example.hu")
                .beiratkozik(kurzus, felev).jegyetRogzit(4);
        new Hallgato("H3", "Cili", "cili@example.hu")
                .beiratkozik(kurzus, felev);

        assertEquals(0.5, kurzus.getAtmenesiArany());
    }

    @Test
    void legjobbHaromHallgatotJegySzerintAdjaVissza() {
        Kurzus kurzus = kurzus("K1", 3, 6);
        int[] jegyek = {6, 10, 8, 4, 9};

        for (int i = 0; i < jegyek.length; i++) {
            new Hallgato("H" + i, "Név" + i, "nev" + i + "@example.hu")
                    .beiratkozik(kurzus, felev)
                    .jegyetRogzit(jegyek[i]);
        }

        assertEquals(
                List.of("H1", "H4", "H2"),
                Hallgato.legjobbHarom(kurzus).stream()
                        .map(Szemely::getAzonosito)
                        .toList()
        );
    }

    @Test
    void szemelyekAzonositoAlapjanEgyenlok() {
        Hallgato egyik =
                new Hallgato("H1", "Anna", "anna@example.hu");
        Hallgato masik =
                new Hallgato("H1", "Másik név", "masik@example.hu");

        assertEquals(egyik, masik);
        assertEquals(egyik.hashCode(), masik.hashCode());
    }

    @Test
    void oktatoSajatKurzusainakKreditjeitOsszegzi() {
        Kurzus sajat = kurzus("K1", 3, 5);
        Oktato masikOktato =
                new Oktato("O2", "Másik oktató", "masik@example.hu");
        Kurzus masik = new Kurzus("K2", "Másik kurzus",
                masikOktato, 5, 5);

        assertEquals(3, oktato.getTanitottKreditek(List.of(sajat, masik)));
    }
}
