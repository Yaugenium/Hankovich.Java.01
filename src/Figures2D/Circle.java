package Figures2D;

public class Circle extends Shape {
    private double radius;

    public Circle(double radiusValue) {
        super(Math.PI * radiusValue * radiusValue);
        radius = radiusValue;
    }

    public double getRadius() {
        return radius;
    }
}
