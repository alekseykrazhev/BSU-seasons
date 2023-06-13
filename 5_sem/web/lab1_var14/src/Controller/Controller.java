package Controller;

import Model.Circle;
import Model.RationalFraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to show work with Circle and Rational Fraction classes
 *
 * @author Alex Krazhevskiy
 * @version 1.0
 */
public class Controller {

    /**
     * Default constructor
     */
    public Controller() {}

    /**
     * Method that demonstrates work with Circle and Rational Fraction classes
     * @return output string (result)
     */
    public static String TestApplication()
    {
        List<Circle> circles = new ArrayList<>();
        List<Double> circleLengths = new ArrayList<>();
        List<Double> circleSquares = new ArrayList<>();

        circles.add(new Circle(new RationalFraction(4, 5), new RationalFraction(7, 3), new RationalFraction(4, 1)));
        circles.add(new Circle(new RationalFraction(5, 4), new RationalFraction(8, 4), new RationalFraction(8, 1)));
        circles.add(new Circle(new RationalFraction(4, 10), new RationalFraction(11, 2), new RationalFraction(9, 7)));
        circles.add(new Circle(new RationalFraction(5, 4), new RationalFraction(10, 4), new RationalFraction(10, 1)));
        circles.add(new Circle(new RationalFraction(1, 1), new RationalFraction(5, 5), new RationalFraction(1, 5)));
        circles.add(new Circle(new RationalFraction(1, 3), new RationalFraction(4, 4), new RationalFraction(3, 1)));
        circles.add(new Circle(new RationalFraction(5, 5), new RationalFraction(1, 3), new RationalFraction(4, 1)));
        circles.add(new Circle(new RationalFraction(2, 0), new RationalFraction(0, 2), new RationalFraction(4, 2)));
        circles.add(new Circle(new RationalFraction(0, 1), new RationalFraction(0, 5), new RationalFraction(5, 3)));
        circles.add(new Circle(new RationalFraction(0, 0), new RationalFraction(0, 3), new RationalFraction(-5, 3)));

        for (Circle circle: circles) {
            circleLengths.add(circle.GetLength());
            circleSquares.add(circle.GetSquare());
        }

        StringBuilder outputString = new StringBuilder(String.format("\nMax - %.2f and min - %.2f (circle length)\n",
                Collections.max(circleLengths), Collections.min(circleLengths)));
        outputString.append(String.format("Max - %.2f and min - %.2f (circle square)\n\n",
                Collections.max(circleSquares), Collections.min(circleSquares)));

        for (int i=0; i+1<circles.size(); i++){
            outputString.append(String.format("Group %d is: \n", i)).append(circles.get(i).toString()).append(circles.get(i+1).toString());
            for (int j = i+1; j < circles.size(); j++){
                if(circles.get(i).IsOnTheSameLine(circles.get(i+1), circles.get(j))){
                    outputString.append(circles.get(j).toString());
                }
            }
            outputString.append("\n________________________________________\n");
        }
        return outputString.toString();
    }

}
