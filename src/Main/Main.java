package Main;

import java.util.Scanner;
import Backpack.*;
import Figures3D.*;
import Figures2D.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Input a backpack volume ");
        Scanner in = new Scanner(System.in);

        Backpack backpack = new Backpack(in.nextDouble());

        loop3D:
        while (true) {
            System.out.println("Input a 3D figure number ");
            System.out.println("1) Cube ");
            System.out.println("2) Sphere ");
            System.out.println("3) Cilinder ");
            System.out.println("4) End 3D input ");

            try {
                switch (in.nextInt()) {
                    case (1):
                        System.out.println("Input an edge value ");
                        backpack.putFigure(new Cube(in.nextDouble()));
                        break;
                    case (2):
                        System.out.println("Input a radius value ");
                        backpack.putFigure(new Sphere(in.nextDouble()));
                        break;
                    case (3):
                        System.out.println("Input radius and height values ");
                        backpack.putFigure(new Cylinder(in.nextDouble(), in.nextDouble()));
                        break;
                    default:
                        break loop3D;
                }
            } catch (BackpackFullException ex) {
                System.out.println(ex.getMessage());
            }
        }

        loop2D:
        while (true) {
            System.out.println("Input a 2D figure number ");
            System.out.println("1) Square ");
            System.out.println("2) Circle ");
            System.out.println("3) End 2D input ");

            try {
                switch (in.nextInt()) {
                    case (1):
                        System.out.println("Input an edge value ");
                        backpack.putFigure((new Square(in.nextDouble())).castToCube());
                        break;
                    case (2):
                        System.out.println("Input a radius value ");
                        backpack.putFigure((new Circle(in.nextDouble())).castToSphere());
                        break;
                    default:
                        break loop2D;
                }
            } catch (BackpackFullException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println(backpack);
    }
}
