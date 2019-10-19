package Backpack;

import Figures3D.Shape;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Backpack implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    private double backpackVolume;
    private double currentVolume;
    private ArrayList<Shape> figures;

    public Backpack() {
        backpackVolume = 100;
        currentVolume = 0;
        figures = new ArrayList<>();
    }

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

    public int getSize() {
        return figures.size();
    }

    public Shape getFigure(int index) {
        return figures.get(index);
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

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        backpackVolume = aInputStream.readDouble();
        currentVolume = aInputStream.readDouble();
        figures = (ArrayList) aInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeDouble(backpackVolume);
        aOutputStream.writeDouble(currentVolume);
        aOutputStream.writeObject(figures);
    }
}