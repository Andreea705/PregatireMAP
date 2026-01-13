package org.example.repository;

import org.example.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.file.StandardOpenOption;

public class FileRepository {

    public List<Tribut> loadTributes(String filename) throws IOException {
        String content = Files.readString(Path.of(filename));
        List<Tribut> list = new ArrayList<>();
        // Simple manual parsing assuming consistent format
        Matcher m = Pattern.compile("\\{([^}]*)\\}").matcher(content);
        while (m.find()) {
            String obj = m.group(1);
            int id = Integer.parseInt(extractValue(obj, "id"));
            String name = extractString(obj, "name");
            int district = Integer.parseInt(extractValue(obj, "district"));
            String statusStr = extractString(obj, "status");
            int skill = Integer.parseInt(extractValue(obj, "skillLevel"));

            Status status = statusStr.equals("ALIVE") ? Status.LEBENDIG : Status.TOT;
            list.add(new Tribut(id, name, district, status, skill));
        }
        return list;
    }

    public List<Ereignis> loadEvents(String filename) throws IOException {
        String content = Files.readString(Path.of(filename));
        List<Ereignis> list = new ArrayList<>();
        Matcher m = Pattern.compile("\\{([^}]*)\\}").matcher(content);
        while (m.find()) {
            String obj = m.group(1);
            int id = Integer.parseInt(extractValue(obj, "id"));
            int tId = Integer.parseInt(extractValue(obj, "tributeId"));
            String typeStr = extractString(obj, "type");
            int points = Integer.parseInt(extractValue(obj, "points"));
            int day = Integer.parseInt(extractValue(obj, "day"));

            list.add(new Ereignis(id, tId, EventTyp.valueOf(typeStr), points, day));
        }
        return list;
    }

    public List<SponsorGeschenk> loadGifts(String filename) throws IOException {
        String content = Files.readString(Path.of(filename));
        List<SponsorGeschenk> list = new ArrayList<>();
        Matcher m = Pattern.compile("\\{([^}]*)\\}").matcher(content);
        while (m.find()) {
            String obj = m.group(1);
            int id = Integer.parseInt(extractValue(obj, "id"));
            int tId = Integer.parseInt(extractValue(obj, "tributeId"));
            String item = extractString(obj, "itemName");
            int val = Integer.parseInt(extractValue(obj, "value"));
            int day = Integer.parseInt(extractValue(obj, "day"));

            list.add(new SponsorGeschenk(id, tId, item, val, day));
        }
        return list;
    }

    public void saveToFile(String filename, List<String> lines) throws IOException {
        // Schreibt die Liste 'lines' in die Datei.
        // CREATE: Erstellt Datei, falls nicht vorhanden.
        // TRUNCATE_EXISTING: Überschreibt den Inhalt, falls Datei schon existiert.
        Files.write(Path.of(filename), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Helper methods for manual JSON parsing
    private String extractValue(String src, String key) {
        Matcher m = Pattern.compile("\"" + key + "\":\\s*(-?\\d+)").matcher(src);
        if (m.find()) return m.group(1);
        return "0";
    }
    private String extractString(String src, String key) {
        Matcher m = Pattern.compile("\"" + key + "\":\\s*\"([^\"]+)\"").matcher(src);
        if (m.find()) return m.group(1);
        return "";
    }
}
