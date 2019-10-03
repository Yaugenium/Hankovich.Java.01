package Figures3D;

public abstract class RotationShape extends Shape {
    private double radius;

    RotationShape(double volumeValue, double radiusValue) {
        super(volumeValue);
        radius = radiusValue;
    }

    public double getRadius() {
        return radius;
    }
}
