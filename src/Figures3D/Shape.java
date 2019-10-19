package Figures3D;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Shape implements Comparable<Shape>, Serializable {
    private double volume;

    protected Shape(double volumeValue) {
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
        DecimalFormat df = new DecimalFormat("#.##");

        return "(" + getClass().getSimpleName() + ", " + Double.valueOf(df.format(getVolume())) + ") ";
    }
}
