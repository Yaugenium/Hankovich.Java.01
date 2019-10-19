package Main;

import java.io.*;
import java.util.Scanner;
import Backpack.*;
import Figures3D.*;
import Figures2D.*;
import Converter.*;
import BackpackGUI.*;

public class Main {
    public static void main(String[] args) {
        /*System.out.println("Input a backpack volume ");
        Scanner in = new Scanner(System.in);

        Backpack backpack = new Backpack(in.nextDouble());

        figures3D(in, backpack);
        figures2D(in, backpack);

        System.out.println(backpack);

        try
        {
            FileOutputStream fileOut = new FileOutputStream("Backpack.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(backpack);
            out.close();
            fileOut.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }*/

        /*try
        {
            FileInputStream fileIn = new FileInputStream("Backpack.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Backpack backpack = (Backpack) in.readObject();
            in.close();
            fileIn.close();

            System.out.println(backpack);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }*/

        BackpackGUI backpackGUI = new BackpackGUI();
    }

    private static void figures3D (Scanner in, Backpack backpack)
    {
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
                        return;
                }
            } catch (BackpackFullException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void figures2D (Scanner in, Backpack backpack)
    {
        Converter converter = new Converter();

        while (true) {
            System.out.println("Input a 2D figure number ");
            System.out.println("1) Square ");
            System.out.println("2) Circle ");
            System.out.println("3) End 2D input ");

            try {
                switch (in.nextInt()) {
                    case (1):
                        System.out.println("Input an edge value ");
                        backpack.putFigure(converter.cast( new Cube(), new Square(in.nextDouble())));
                        break;
                    case (2):
                        System.out.println("Input a radius value ");
                        backpack.putFigure(converter.cast( new Sphere(), new Circle(in.nextDouble())));
                        break;
                    default:
                        return;
                }
            } catch (BackpackFullException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
