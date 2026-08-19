package lesson2_6.task2;

public class Main {
    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory();
        directory.add("Иванов", "+375291234567");
        directory.add("Петров", "+375291234568");
        directory.add("Иванов", "+375291234569"); // второй номер для Иванова
        directory.add("Сидоров", "+375291234570");

        // Попытка добавить дублирующий номер
        directory.add("Сидоров", "+375291234568"); // этот номер уже занят Петровым
        System.out.println("=== Все записи ===");
        directory.printAll();

        System.out.println("\n=== Номера по фамилии 'Иванов' ===");
        System.out.println(directory.get("Иванов"));

        System.out.println("=== Номера по фамилии 'Петров' ===");
        System.out.println(directory.get("Петров"));

        System.out.println("=== Номера по фамилии 'Смирнов' (нет) ===");
        System.out.println(directory.get("Смирнов"));
    }
}
