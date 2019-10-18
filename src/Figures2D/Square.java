package Figures2D;

public class Square extends Shape{
    private double edge;

    public Square(double edgeValue) {
        super(edgeValue * edgeValue);
        edge = edgeValue;
    }

    public double getEdge() {
        return edge;
    }
}
