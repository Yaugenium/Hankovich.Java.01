package Converter;

import java.util.HashMap;
import javafx.util.Pair;
import java.util.function.Function;

import Figures3D.*;
import Figures2D.*;

public class Converter {
    private HashMap<Pair<String, String>, Function<Figures2D.Shape, Figures3D.Shape>> map;

    public Converter() {
        map = new HashMap<>();
        map.put(new Pair<>(Square.class.getSimpleName(), Cube.class.getSimpleName()), x -> new Cube(((Square) x).getEdge()));
        map.put(new Pair<>(Circle.class.getSimpleName(), Sphere.class.getSimpleName()), x -> new Sphere(((Circle) x).getRadius()));
    }

    public Figures3D.Shape cast (Figures3D.Shape type, Figures2D.Shape figure) {
        return map.get(new Pair<>(figure.getClass().getSimpleName(), type.getClass().getSimpleName())).apply(figure);
    }
}
