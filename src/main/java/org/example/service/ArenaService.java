package org.example.service;


import org.example.model.Ereignis;
import org.example.model.Status;
import org.example.model.Tribut;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.model.EventTyp.FOUND_SUPPLIES;

public class ArenaService {

    public List<Tribut> filterByDistrictAndAlive(List<Tribut> tributes, int district) {
        List<Tribut> filteredList = new ArrayList<>();

        for (Tribut t : tributes) {

            if (t.getDistrikt() == district && t.getStatus() == Status.LEBENDIG) {
                filteredList.add(t);
            }
        }
        return filteredList;
    }

    public List<Tribut> sortTributes(List<Tribut> tributes) {
        return tributes.stream()
                // 1. Sortiere nach Skill Level absteigend (höchster zuerst)
                .sorted(Comparator.comparingInt(Tribut::getSkillLevel).reversed()
                        // 2. Bei gleichem Skill: Sortiere nach Name aufsteigend (A-Z)
                        .thenComparing(Tribut::getName))
                .collect(Collectors.toList());
    }

    public int calculateComputedPoints(Ereignis e) {
        int points = e.getPoints();
        int day = e.getDay();

        switch (e.getTyp()) {
            case FOUND_SUPPLIES:
                return points + (2 * day);
            case INJURED:
                return points - day;
            case ATTACK:
                return (points * 2) + day;
            case HELPED_ALLY:
                return points + 5;
            case SPONSORED:
                return points + 10;
            default:
                return points;
        }
    }
}
