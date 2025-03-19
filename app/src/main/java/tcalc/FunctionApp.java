package tcalc;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import expressionParser.FunctionStepper;
import plotting.Graph;
import plotting.Point;

public class FunctionApp {

    private static Set<String> escapesStrings = Set.of("q", "Q", "exit", "quit");
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String input = "";

        while (!escapesStrings.contains(input)) {
            System.out.print("enter an expression: ");
            input = scan.nextLine();
            System.out.print("enter a starting x value: ");
            double startX = scan.nextDouble();
            scan.nextLine();
            System.out.print("enter a limit x value: ");
            double stopX = scan.nextDouble();
            scan.nextLine();
            System.out.print("enter a step size: ");
            double step = scan.nextDouble();
            scan.nextLine();

            LinkedList<Point> coordinates = new LinkedList<>();

            try {
                FunctionStepper fs = new FunctionStepper(input, startX, stopX, step);
                for (double res : fs) {
                    coordinates.push(new Point(fs.getPos(), res));
                    // System.out.println("Result for x = " + fs.getPos() + " : " + res);
                }
                new Graph(coordinates, input);
                Thread.sleep(2000);
            }
            catch(Exception e) {
                System.out.println(e);
            }

        }
        scan.close();
    }
}
