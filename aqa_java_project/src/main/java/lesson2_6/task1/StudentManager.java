package lesson2_6.task1;
import java.util.*;

public class StudentManager {
    // 1. Удаление студентов со средним баллом < 3
    public static void removePoorStudents(Set<Student> students) {
        students.removeIf(s -> s.getAverageGrade() < 3);
    }

    // 2. Перевод всех студентов с баллом >= 3 на следующий курс
    public static void promoteStudents(Set<Student> students) {
        for (Student s : students) {
            if (s.getAverageGrade() >= 3) {
                s.promoteToNextCourse();
            }
        }
    }

    // 3. Печать имён студентов на указанном курсе
    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println(" - " + s.getName());
            }
        }
    }

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

        // Удаляем слабых (средний < 3)
        removePoorStudents(students);
        System.out.println("\n=== После удаления слабых ===");
        for (Student s : students) System.out.println(s);

        // Переводим на следующий курс (если средний >= 3)
        promoteStudents(students);
        System.out.println("\n=== После перевода на следующий курс ===");
        for (Student s : students) System.out.println(s);

        // Печать студентов на 3 курсе
        printStudents(students, 3);
    }
}
