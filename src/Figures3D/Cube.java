package Figures3D;

public class Cube extends Shape {
    private double edge;

    public Cube(double edgeValue) {
        super(edgeValue * edgeValue * edgeValue);
        edge = edgeValue;
    }

    public double getEdge() {
        return edge;
    }
}