package Figures2D;

import Figures3D.Sphere;

public class Circle extends Shape {
    private double radius;

    public Circle(double radiusValue) {
        super(Math.PI * radiusValue * radiusValue);
        radius = radiusValue;
    }

    public double getRadius() {
        return radius;
    }

    public Sphere castToSphere() {
        return new Sphere(radius);
    }
}
