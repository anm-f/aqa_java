package lesson2_4.task2;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(5.0, "Красный", "Синий");
        shapes[1] = new Rectangle(4.0, 6.0, "Зелёный", "Чёрный");
        shapes[2] = new Triangle(3.0, 4.0, 5.0, "Жёлтый", "Оранжевый");

        for (Shape s : shapes) {
            s.printInfo();
        }
    }
}
