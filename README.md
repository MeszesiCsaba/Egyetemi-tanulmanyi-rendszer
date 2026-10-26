# Egyetemi tanulmányi rendszer

Java 17-es Maven-projekt egy egyszerű egyetemi tanulmányi modellhez.

## Futtatás és tesztelés

A projekt gyökeréből futtasd:

    mvn test
    mvn package

## Modell

A Szemely közös ősosztály a Hallgato és Oktato számára. A Hallgato.beiratkozik metódus ellenőrzi a kurzus férőhelyét és azt, hogy a hallgató ugyanabban a félévben ne vegye fel kétszer ugyanazt a kurzust.

A Beiratkozas egy kurzushoz és félévhez tartozik; a jegy opcionális, és 1–10 közötti egész lehet. A megszerzett kredit csak az 5-ös vagy jobb jegyű kurzusok kreditjeit számolja. A súlyozott átlag csak az osztályozott beiratkozásokat veszi figyelembe. A kurzus átmenési arányának nevezője szintén az osztályozott hallgatók száma.

A Szemely egyenlősége az azonosítón alapul, ezért az azonosítónak személyenként egyedinek kell lennie. A beiratkozási listák getterei nem adják ki módosítható formában a belső listákat.

## Tesztek

A TanulmanyiRendszerTest ellenőrzi a férőhelyet, a duplikált felvételt, a jegyhatárokat, a kredit súlyozta átlagot, a megszerzett krediteket, az átmenési arányt, a legjobb három hallgatót és a személyek egyenlőségét.

## AI-használat

Használtam AI-t ahol elakadtam, a felépitésre.
