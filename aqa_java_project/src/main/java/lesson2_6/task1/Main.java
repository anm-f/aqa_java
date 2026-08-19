package lesson2_6.task1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Создаём студентов с оценками
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иванов Иван", "Группа 1", 2,
                Arrays.asList(4, 5, 3, 4)));
        students.add(new Student("Петров Пётр", "Группа 1", 2,
                Arrays.asList(2, 3, 2, 2))); // средний < 3
        students.add(new Student("Сидорова Анна", "Группа 2", 3,
                Arrays.asList(5, 5, 4, 4)));
        students.add(new Student("Козлов Дмитрий", "Группа 2", 2,
                Arrays.asList(4, 4, 5, 4)));

        System.out.println("=== Исходные студенты ===");
        for (Student s : students) System.out.println(s);

        StudentManager manager = new StudentManager();
        // Удаляем слабых (средний < 3)
        manager.removePoorStudents(students);
        System.out.println("\n=== После удаления слабых ===");
        for (Student s : students) System.out.println(s);

        // Переводим на следующий курс (если средний >= 3)
        manager.promoteStudents(students);
        System.out.println("\n=== После перевода на следующий курс ===");
        for (Student s : students) System.out.println(s);

        // Печать студентов на 3 курсе
        manager.printStudents(students, 3);
    }
}
