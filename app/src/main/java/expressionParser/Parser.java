package expressionParser;

import java.util.Iterator;

public class Parser {

    char[] operators = {'+', '-', '/', '*'};

    ExpressionStack expressionStack;
    double ans;

    public Parser(String input) {
        expressionStack = new ExpressionStack(input);
    }

    public Double evaluate() {
        expressionStack.push('(');
        System.out.println(expressionStack.getSize());
        return evaluateRecursively();
    }

    private Double evaluateRecursively() {

        if (expressionStack.isEmpty()) {
            System.out.println("base case");
            return 1.0;
        }
        char curr = expressionStack.peek();
        System.out.println(String.valueOf(curr));
        if (Character.isDigit(curr)) {
            return getNumberFromStack();
        }
        if (curr == '(') {
            expressionStack.pop();
            double left = evaluateRecursively();
            char operand = getOperandFromStack();
            double right = evaluateRecursively();

            if (expressionStack.peek() == ')') {
                expressionStack.pop();
            }
            return calculate(left, operand, right);
        }
        double left = evaluateRecursively();
        char operand = getOperandFromStack();
        double right = evaluateRecursively();
        return calculate(left, operand, right);
    }

    private double calculate(double left, char operand, double right) {
        System.out.println("calucalting: " + left + " " + String.valueOf(operand) + " " + right);
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
            return null;
        }

        double num = 0;
        Iterator<Character> itr = expressionStack.iterator();
        while(Character.isDigit(expressionStack.peek())) {
            num = num * 10 +  Character.getNumericValue(itr.next());
        }
        return num;
    }
    private char getOperandFromStack() {
        char expresson = expressionStack.pop();
        for (char o : operators) {
            if (expresson == o) {
                return expresson;
            }
        }
        throw new IllegalCallerException("This character does not exist in operators");
    }
}
