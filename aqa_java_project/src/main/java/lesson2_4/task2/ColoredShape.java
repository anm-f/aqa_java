package lesson2_4.task2;

public abstract class ColoredShape implements Shape {
    protected String fillColor;
    protected String borderColor;

    public ColoredShape(String fillColor, String borderColor) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public abstract double getPerimeter();

    @Override
    public abstract double getArea();
}
