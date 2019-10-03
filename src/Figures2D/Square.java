package Figures2D;

import Figures3D.Cube;

public class Square extends Shape{
    private double edge;

    public Square(double edgeValue) {
        super(edgeValue * edgeValue);
        edge = edgeValue;
    }

    public double getEdge() {
        return edge;
    }

    public Cube castToCube() {
        return new Cube(edge);
    }
}
