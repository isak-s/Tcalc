package expressionParser;

public class ExpressionTree {

    public ExpressionNode root;

    public ExpressionTree() {
        root = null;
    }

    public double evaluateExpressionTree(ExpressionNode root) {
        if (root == null) {
            return 0.0;
        }

        if (root.left == null && root.right == null) {
            return root.value;  // Leaf node, return the number
        }

        // Recursively evaluate the left and right subtrees
        double leftValue = evaluateExpressionTree(root.left);
        double rightValue = evaluateExpressionTree(root.right);

        // Apply the operator at the root
        switch (root.operator) {
            case '+': return leftValue + rightValue;
            case '-': return leftValue - rightValue;
            case '*': return leftValue * rightValue;
            case '/': return leftValue / rightValue;
            default: throw new IllegalArgumentException("Unsupported operator: " + root.operator);
        }
    }

    public void buildExpressionTree(String expression) {
        Stack<ExpressionNode> values = new Stack<>();   // Stack for operands (numbers)
        Stack<Character> operators = new Stack<>();     // Stack for operators

        // Traverse the expression
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            // If the current character is a digit, parse the number
            if (Character.isDigit(current)) {
                double num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                values.push(new ExpressionNode(num));  // Push the number to the stack
                i--;  // Adjust for the next iteration of the loop
            }
            // If the current character is an opening parenthesis, push it to the operator stack
            else if (current == '(') {
                operators.push(current);
            }
            // If the current character is a closing parenthesis, resolve the subexpression
            else if (current == ')') {
                while (operators.peek() != '(') {
                    values.push(buildSubtree(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();  // Pop the opening parenthesis
            }
            // If the current character is an operator
            else if (isOperator(current)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(current)) {
                    values.push(buildSubtree(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(current);  // Push the operator to the stack
            }
        }

        // Process the remaining operators in the stack
        while (!operators.isEmpty()) {
            values.push(buildSubtree(operators.pop(), values.pop(), values.pop()));
        }

        // The root of the expression tree will be the only element in the stack
        root = values.pop();
    }

    // Method to check if a character is an operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Method to define operator precedence
    private int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    // Method to create a new subtree with an operator and two operands
    private ExpressionNode buildSubtree(char operator, ExpressionNode right, ExpressionNode left) {
        return new ExpressionNode(operator, left, right);
    }

    private class ExpressionNode {
        char operator;
        double value;
        ExpressionNode left;
        ExpressionNode right;

        ExpressionNode(char operator, ExpressionNode left, ExpressionNode right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }

        ExpressionNode(double value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}