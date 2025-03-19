/*
 * This source file was generated by the Gradle 'init' task
 */
package tcalc;

import java.util.Scanner;

import expressionParser.Expression;
import treeExpressionParser.ExpressionTree;

import java.util.Set;

public class CalculatorApp {

    private static Set<String> escapesStrings = Set.of("q", "Q", "exit", "quit");

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {

        String input = "";

        if (args.length == 1) {
            input = args[0];
            double res = stackImplementation(input);

            System.out.println(" = " + res);
        }

        Scanner scan = new Scanner(System.in);

        while (!escapesStrings.contains(input)) {
            System.out.println("enter an expression. \n    ");
            input = scan.nextLine();

            double res = stackImplementation(input);

            System.out.println(" = " + res);
        }


        scan.close();
    }

    public static double stackImplementation(String input) {

        double res = 0;

        try {
            Expression parser = new Expression(input);
            res = parser.evaluate();
        }
        catch(Exception e) {
            System.out.println("Invalid expression!");
            System.out.println(e);
        }

        return res;
    }

    public static double treeImplementation(String input) {
        ExpressionTree tree = new ExpressionTree();

        tree.buildExpressionTree(input);

        // Evaluate the expression
        return tree.evaluateExpressionTree(tree.root);
    }
}
