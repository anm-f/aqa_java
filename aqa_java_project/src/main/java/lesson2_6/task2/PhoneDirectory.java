package lesson2_6.task2;
import java.util.*;

public class PhoneDirectory {
    private Map<String, List<String>> phoneMap; // фамилия -> список номеров
    private Set<String> uniqueNumbers; // для проверки уникальности

    public PhoneDirectory() {
        phoneMap = new HashMap<>();
        uniqueNumbers = new HashSet<>();
    }

    // Добавление записи (фамилия, номер)
    public boolean add(String surname, String phone) {
        // Проверяем уникальность номера
        if (uniqueNumbers.contains(phone)) {
            System.out.println("Ошибка: номер " + phone + " уже существует.");
            return false;
        }

        // Если фамилии нет — создаём новый список
        phoneMap.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone);
        uniqueNumbers.add(phone);
        return true;
    }

    // Поиск номеров по фамилии
    public List<String> get(String surname) {
        return phoneMap.getOrDefault(surname, Collections.emptyList());
    }

    // Вывод всех записей (для проверки)
    public void printAll() {
        for (Map.Entry<String, List<String>> entry : phoneMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

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
