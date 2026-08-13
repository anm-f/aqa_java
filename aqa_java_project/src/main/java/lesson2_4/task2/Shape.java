package lesson2_4.task2;

public interface Shape {
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.println("Фигура: " + getClass().getSimpleName());
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
        System.out.println("---###---");
    }
}
