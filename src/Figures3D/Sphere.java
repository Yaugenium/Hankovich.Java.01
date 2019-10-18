package Figures3D;

public class Sphere extends RotationShape {
    public Sphere() {
        super(0, 0);
    }
    public Sphere(double radiusValue) {
        super(4 * Math.PI * radiusValue * radiusValue * radiusValue / 3, radiusValue);
    }
}