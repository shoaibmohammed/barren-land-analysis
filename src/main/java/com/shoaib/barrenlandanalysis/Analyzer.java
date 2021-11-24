package com.shoaib.barrenlandanalysis;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Analyzer {

    private static final Pattern INPUT_PATTERN = Pattern.compile("[0-9\\s,]+");

    public static void main(String[] args) throws Exception {
        System.out.println("----------Barren Land Analysis--------");
        System.out.println("Enter one or more barren land rectangles seperated by comma" +
                        "\nEach barren land is entered as 4 numbers, " +
                        "\nThe first two integers are the coordinates of the bottom left corner in the given rectangle, " +
                        "\nand the last two integers are the coordinates of the top right corner." +
                        "\nFor Example : 0 292 399 307" +
                        "\nOR" +
                        "\n48 192 351 207, 48 392 351 407, 120 52 135 547, 260 52 275 547");
        System.out.println("--------------------------------------");

        Land land = new Land();

        String input = new Scanner(System.in).nextLine();
        input = input.replaceAll("[\\[\\](){}]","");

        String s = input.replace("\"", "");
        if (INPUT_PATTERN.matcher(input).matches()){
            String[] barrenLandCoordinateSets = input.trim().split(",");

            for(String barrenLandCoordinates : barrenLandCoordinateSets) {
                String[] barrenLandCoordinateArray = barrenLandCoordinates.trim().split(" ");
                if (barrenLandCoordinateArray.length == 4) {

                    final Point bottom_left_coordinate = new Point(Integer.parseInt(barrenLandCoordinateArray[0]), Integer.parseInt(barrenLandCoordinateArray[1]));
                    final Point top_right_coordinate = new Point(Integer.parseInt(barrenLandCoordinateArray[2]), Integer.parseInt(barrenLandCoordinateArray[3]));

                    if (bottom_left_coordinate.x > top_right_coordinate.x || bottom_left_coordinate.y > top_right_coordinate.y) {
                        throw new Exception("The entered coordinates do not form a rectangle.");
                    }

                    land.markLandAsBarren(bottom_left_coordinate, top_right_coordinate);

                } else {
                    throw new Exception("A valid input is 4 numbers, " + barrenLandCoordinateArray.length +
                            " numbers were entered for at least one barren land");
                }
            }
        }
        else {
            throw new Exception("Wrong input format");
        }

        System.out.println("The Area of fertile land(s) in the order of size :");
        List<Integer> fertileLandAreas = land.getFertileLand();
        for (int fertileLand : fertileLandAreas) {
            System.out.print(fertileLand + " ");
        }
        System.out.println();
    }
}
