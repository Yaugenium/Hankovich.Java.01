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
        figures = new ArrayList<>();
    }

    public int putFigure(Shape figure) throws BackpackFullException {
        if (currentVolume + figure.getVolume() > backpackVolume) {
            throw new BackpackFullException("Figure does not fit into this backpack");
        }

        currentVolume += figure.getVolume();
        figures.add(figure);
        figures.sort(Comparator.naturalOrder());

        return figures.indexOf(figure);
    }

    public boolean takeOutFigure(int index) {
        if (figures.size() <= index) {
            return false;
        }

        currentVolume -= figures.get(index).getVolume();
        figures.remove(index);

        return true;
    }

    public boolean clearBackpack() {
        if(figures.size() == 0) {
            return false;
        }

        currentVolume = 0;
        figures.clear();

        return true;
    }

    public double getBackpackVolume() {
        return backpackVolume;
    }

    public double getCurrentVolume() {
        return currentVolume;
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