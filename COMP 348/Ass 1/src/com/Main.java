package com;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        /*************** 4.1) Q 2 ***************/
        //Scanner inputScanner = new Scanner(System.in);
        //System.out.print("Please enter the name of the file > ");
        //String input = inputScanner.nextLine();
        var input = "test.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(input)))  {

            Shape[] shapes = reader.lines()
                    .map(line -> line.startsWith("Rectangle") ? Rectangle.parse(line) : Circle.parse(line))
                    .toArray(Shape[]::new);

            long numberOfShapes = Arrays.stream(shapes).count();
            double avgPerimeter = Arrays.stream(shapes)
                    .mapToDouble(Shape::perimeter)
                    .average()
                    .getAsDouble();

            double avgArea = Arrays.stream(shapes)
                    .mapToDouble(Shape::area)
                    .average()
                    .getAsDouble();

            System.out.println("******** Sorted by name and area ********");
            Arrays.stream(shapes)
                    .sorted((a, b) -> {
                        int temp = a.name().compareTo(b.name());
                        return temp != 0 ? temp : (int)(a.area() - b.area());
                    })
                    .forEach(System.out::println);

            System.out.println("\n******** Sorted by perimeter ********");
            Arrays.stream(shapes)
                    .sorted((a, b) -> Double.compare(a.perimeter(), b.perimeter()) )
                    .forEach(System.out::println);

            System.out.println("\n******** Summary information ********");
            System.out.println("Total shapes: " + numberOfShapes);
            System.out.println("Average Perimeter: " + avgPerimeter);
            System.out.println("Average area: " + avgArea);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
