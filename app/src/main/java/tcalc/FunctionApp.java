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

        LinkedList<Point> coordinates = new LinkedList<>();

        if (args.length == 4) {

            String expression = args[0];
            Double startX = Double.valueOf(args[1]);
            Double stopX = Double.valueOf(args[2]);
            Double step = Double.valueOf(args[3]);

            try {
                FunctionStepper fs = new FunctionStepper(expression, startX, stopX, step);
                for (double res : fs) {
                    coordinates.push(new Point(fs.getPos(), res));
                    // System.out.println("Result for x = " + fs.getPos() + " : " + res);
                }
                new Graph(coordinates, expression);
                Thread.sleep(2000);
            }
            catch(Exception e) {
                System.out.println(e);
            }

        }
        else {
            Scanner scan = new Scanner(System.in);
            String expression = "";

            while (!escapesStrings.contains(expression)) {
                System.out.print("enter an expression: ");
                expression = scan.nextLine();
                System.out.print("enter a starting x value: ");
                double startX = scan.nextDouble();
                scan.nextLine();
                System.out.print("enter a limit x value: ");
                double stopX = scan.nextDouble();
                scan.nextLine();
                System.out.print("enter a step size: ");
                double step = scan.nextDouble();
                scan.nextLine();

            try {
                FunctionStepper fs = new FunctionStepper(expression, startX, stopX, step);
                for (double res : fs) {
                    coordinates.push(new Point(fs.getPos(), res));
                    // System.out.println("Result for x = " + fs.getPos() + " : " + res);
                }
                new Graph(coordinates, expression);
                Thread.sleep(2000);
            }
            catch(Exception e) {
                System.out.println(e);
            }

        }
        scan.close();
    }
    }
}
