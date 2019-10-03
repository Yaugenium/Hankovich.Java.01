package Figures3D;

public class Sphere extends RotationShape {
    public Sphere(double radiusValue) {
        super(4 * Math.PI * radiusValue * radiusValue * radiusValue / 3, radiusValue);
    }
}