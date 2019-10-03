package Figures3D;

public abstract class Shape implements Comparable<Shape> {
    private double volume;

    Shape(double volumeValue) {
        volume = volumeValue;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public int compareTo(Shape shape) {
        return Double.compare(shape.getVolume(), volume);
    }

    @Override
    public String toString() {
        return "(" + getClass().getSimpleName() + ", " + getVolume() + ") ";
    }
}
