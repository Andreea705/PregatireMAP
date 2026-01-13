package org.example.view;



import org.example.model.Tribut;
import java.util.List;
import java.util.Scanner;

public class ArenaView {

    private Scanner scanner = new Scanner(System.in);

    public void printTask1Results(List<Tribut> tributes, int eventCount, int giftCount) {
        System.out.println("Tributes loaded: " + tributes.size());
        System.out.println("Events loaded: " + eventCount);
        System.out.println("Gifts loaded: " + giftCount);

        for (Tribut t : tributes) {
            System.out.println(t.toString());
        }
    }

    public int requestDistrictInput() {
        System.out.print("Input district: ");
        // Wir nehmen an, dass der Benutzer eine ganze Zahl eingibt
        return scanner.nextInt();
    }

    public void printComputedPoints(int id, int rawPoints, int computedPoints) {
        System.out.println("Event " + id + " -> rawPoints=" + rawPoints + " -> computedPoints=" + computedPoints);
    }

    public void printTributeList(List<Tribut> tributes) {
        for (Tribut t : tributes) {
            System.out.println(t.toString());
        }
    }
}
