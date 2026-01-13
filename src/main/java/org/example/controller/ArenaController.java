package org.example.controller;

import org.example.model.Ereignis;
import org.example.model.SponsorGeschenk;
import org.example.model.Tribut;
import org.example.repository.FileRepository;
import org.example.service.ArenaService;
import org.example.view.ArenaView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaController {
    private FileRepository repo;
    private ArenaView view;
    private ArenaService service;

    public ArenaController() {
        this.repo = new FileRepository();
        this.view = new ArenaView();
        this.service = new ArenaService();
    }

    public void start() {
        try {
            // Schritt 1: Daten laden
            List<Tribut> tributes = repo.loadTributes("tributes.json");
            List<Ereignis> events = repo.loadEvents("events.json");
            List<SponsorGeschenk> gifts = repo.loadGifts("gifts.json");

            // Schritt 2: Ausgabe Aufgabe 1 [
            //view.printTask1Results(tributes, events.size(), gifts.size());

            //int inputDistrict = view.requestDistrictInput();

            // 2. Logik aufrufen (Filtern)
            //List<Tribut> filteredTributes = service.filterByDistrictAndAlive(tributes, inputDistrict);

            // 3. Ergebnis ausgeben (Format ist gleich wie bei Aufgabe 1) [cite: 79]
           // view.printTributeList(filteredTributes);

            List<Tribut> sortedTributes = service.sortTributes(tributes);

            System.out.println("Sortierte Tribute:");
            view.printTributeList(sortedTributes);

            // --- NEU FÜR AUFGABE 4 ---
            // 1. Umwandeln der Tribute-Objekte in Strings
            List<String> linesToWrite = new ArrayList<>();
            for (Tribut t : sortedTributes) {
                linesToWrite.add(t.toString()); // Nutzt das Format aus Aufgabe 1
            }

            // 2. In Datei schreiben
            repo.saveToFile("tributes_sorted.txt", linesToWrite);
            System.out.println("Datei 'tributes_sorted.txt' wurde erfolgreich erstellt.");

            System.out.println("Punktberechnung (Erste 5 Events):");

            // Schleife läuft bis 5 oder bis zum Listenende (falls Liste kürzer als 5 ist)
            for (int i = 0; i < 5 && i < events.size(); i++) {
                Ereignis e = events.get(i);

                // 1. Berechnung im Service
                int computed = service.calculateComputedPoints(e);

                // 2. Ausgabe in der View
                view.printComputedPoints(e.getId(), e.getPoints(), computed);
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Dateien: " + e.getMessage());
            e.printStackTrace();
        }
    }
}