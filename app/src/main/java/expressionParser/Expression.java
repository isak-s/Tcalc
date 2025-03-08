package expressionParser;

import java.util.Iterator;
import constants.Physics;

public class Expression {

    Physics physicsConstants = new Physics();

    char[] operators = {'+', '-', '/', '*'};

    ExpressionStack expressionStack;
    double ans;

    public Expression(String input) {
        expressionStack = new ExpressionStack(input);
    }

    public Double evaluate() {
        System.out.println(expressionStack);
        return evaluateRecursively(0, '+');
    }

    private Double evaluateRecursively(double res, char operator) {

        // System.out.print(res + " " + String.valueOf(operator) + " ");

        if (expressionStack.isEmpty()) {
            System.out.println("Bad expression");
            return res;
        }

        if (expressionStack.peek() == ')') {
            expressionStack.pop();
            return res;
        }

        double operand;
        if (expressionStack.peek() == '(') {
            expressionStack.pop();
            operand = evaluateRecursively(0, '+');
        }
        else {
            operand = getNumberFromStack();
        }

        res = calculate(res, operator, operand);

        if (expressionStack.isEmpty()) {
            return res;
        }
        if (expressionStack.peek() == ')') {
            expressionStack.pop();
            return res;
        }
        operator = getOperatorFromStack();
        return evaluateRecursively(res, operator);
    }

    private double calculate(double left, char operand, double right) {
        // System.out.println("calucalting: " + left + " " + String.valueOf(operand) + " " + right);
        switch (operand) {
            case '-':
                return left - right;
            case '+':
                return left + right;
            case '/':
                return left / right;
            case '*':
                return left * right;
            case '^':
                return Math.pow(left, right);
            default:
                throw new UnsupportedOperationException("No implemented yet");
        }

    }

    private Double getNumberFromStack() {
        Character curr = expressionStack.peek();
        if (!Character.isDigit(curr)) {
            // Check if it is sin, cos or tan
            // Check h, e, c, etc for constants.
            if (physicsConstants.contains(curr)) {
                expressionStack.pop();
                return physicsConstants.getConstant(curr);
            }
            return null;
        }

        double num = 0;
        Iterator<Character> itr = expressionStack.iterator();
        while(itr.hasNext() && Character.isDigit(expressionStack.peek())) {
            num = num * 10 +  Character.getNumericValue(itr.next());
        }
        return num;
    }
    private char getOperatorFromStack() {
        char expresson = expressionStack.pop();
        for (char o : operators) {
            if (expresson == o) {
                return expresson;
            }
        }
        throw new IllegalCallerException("This character does not exist in operators");
    }
    @Override
    public String toString() {
        return expressionStack.toString();
    }
}
