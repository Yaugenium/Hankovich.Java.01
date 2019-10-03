package Figures3D;

public class Cylinder extends RotationShape {
    private double height;

    public Cylinder(double radiusValue, double heightValue) {
        super(Math.PI * radiusValue * radiusValue * heightValue, radiusValue);
        height = heightValue;
    }

    public double getHeight() {
        return height;
    }
}
