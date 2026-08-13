package lesson2_4.task2;

public class Triangle extends ColoredShape {
    private double a, b, c;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        super(fillColor, borderColor);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
