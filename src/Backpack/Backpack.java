package Backpack;

import Figures3D.Shape;
import java.util.ArrayList;
import java.util.Comparator;

public class Backpack {
    private double backpackVolume;
    private double currentVolume;
    private ArrayList<Shape> figures;

    public Backpack(double bpVolumeValue) {
        backpackVolume = bpVolumeValue;
        currentVolume = 0;
        figures = new ArrayList<Shape>();
    }

    public void putFigure(Shape figure) throws BackpackFullException {
        if (currentVolume + figure.getVolume() > backpackVolume) {
            throw new BackpackFullException("Figure does not fit into this backpack");
        }

        currentVolume += figure.getVolume();
        figures.add(figure);
        figures.sort(Comparator.naturalOrder());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(" ").append(backpackVolume).append(": ");

        for (Shape figure : figures) {
            stringBuilder.append(figure);
        }

        return stringBuilder.toString();
    }
}