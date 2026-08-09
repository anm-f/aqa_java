package lesson2_3;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private String parkName;
    private List<Attraction> attractions;

    public Park(String parkName) {
        this.parkName = parkName;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(String name, String workingHours, double cost) {
        Attraction attraction = new Attraction(name, workingHours, cost);
        attractions.add(attraction);
    }

    public void showAttractions() {
        System.out.println("Парк: " + parkName);
        if (attractions.isEmpty()) {
            System.out.println("В парке пока нет аттракционов.");
            return;
        }
        for (Attraction a : attractions) {
            a.printInfo();
        }
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("  Аттракцион: " + name);
            System.out.println("  Время работы: " + workingHours);
            System.out.println("  Стоимость: " + cost + " BYN");
            System.out.println();
        }
    }
}
