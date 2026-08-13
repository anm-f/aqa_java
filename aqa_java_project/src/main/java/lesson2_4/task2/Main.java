package lesson2_4.task2;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5.0, "Красный", "Синий"),
                new Rectangle(4.0, 6.0, "Зелёный", "Чёрный"),
                new Triangle(3.0, 4.0, 5.0, "Жёлтый", "Оранжевый")
        };

        for (Shape s : shapes) {
            s.printInfo();
        }
    }
}
