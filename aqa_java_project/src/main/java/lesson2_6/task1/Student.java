package lesson2_6.task1;
import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new ArrayList<>(grades);
    }

    public String getName() { return name; }
    public int getCourse() { return course; }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public void promoteToNextCourse() {
        this.course++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(name, student.name) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group);
    }

    @Override
    public String toString() {
        return String.format("%s (группа %s, курс %d, средний балл %.2f)", name, group, course, getAverageGrade());
    }
}
