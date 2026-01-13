package org.example.model;

public class Tribut {
    private int id;
    private String name;
    private int distrikt;
    private Status status;
    private int skillLevel;

    public Tribut(int id, String name, int distrikt, Status status, int skillLevel) {
        this.id = id;
        this.name = name;
        this.distrikt = distrikt;
        this.status = status;
        this.skillLevel = skillLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDistrikt() {
        return distrikt;
    }

    public Status getStatus() {
        return status;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    @Override
    public String toString() {
        // Wir müssen "ALIVE" oder "DEAD" ausgeben, auch wenn das Enum LEBENDIG heißt.
        String statusStr = (status == Status.LEBENDIG) ? "ALIVE" : "DEAD";
        // Formatierung gemäß Aufgabe 1: "1 Katniss Everdeen | D12 | ALIVE | skill=9"
        return id + " " + name + " | D" + distrikt + " | " + statusStr + " | skill=" + skillLevel;
    }
}
