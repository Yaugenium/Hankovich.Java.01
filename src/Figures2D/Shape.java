package Figures2D;

public abstract class Shape implements Comparable<Shape> {
    private double square;

    protected Shape(double volumeValue) {
        square = volumeValue;
    }

    public double getSquare() {
        return square;
    }

    @Override
    public int compareTo(Shape shape) {
        return Double.compare(shape.getSquare(), square);
    }

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + ", " + getSquare() + ") ";
    }
}
