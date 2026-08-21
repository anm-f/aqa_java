package lesson2_6.task1;
import java.util.*;

public class StudentManager {
    // 1. Удаление студентов со средним баллом < 3
    public void removePoorStudents(Set<Student> students) {
        students.removeIf(s -> s.getAverageGrade() < 3);
    }

    // 2. Перевод всех студентов с баллом >= 3 на следующий курс
    public void promoteStudents(Set<Student> students) {
        for (Student s : students) {
            if (s.getAverageGrade() >= 3) {
                s.promoteToNextCourse();
            }
        }
    }

    // 3. Печать имён студентов на указанном курсе
    public void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println(" - " + s.getName());
            }
        }
    }
}