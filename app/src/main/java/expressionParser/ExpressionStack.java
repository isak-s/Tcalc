package expressionParser;

import java.util.Set;

import constants.MathUtils;


public class ExpressionStack extends Stack<Character> {


    Set<Character> prioritizedOperators = Set.of('*', '/', '^');
    Set<Character> operators = Set.of('+', '-');

    private char closing = ')';
    private char opening = '(';

    public ExpressionStack(String str) {
        super();
        push(str);
    }

    public ExpressionStack() {
        super();
    }

    public void push(String str) {
        str = MathUtils.convertSymbols(
            str.strip().replace(" ", "")
            );

        pushChars(str);
    }
    /**
     * Pushes the string into the charstack in reverse order.
     * @param str
     */
    private void pushChars(String str) {
        if (str.isEmpty()) {
            return;
        }
        int lastLetterIndex = str.length() - 1;
        char currChar = str.charAt(lastLetterIndex);

        if (prioritizedOperators.contains(currChar)) { // To avoid extra parenthesese && peek() != closing
            parenthesize(str, lastLetterIndex);
        }
        else {
            push(currChar);
            pushChars(str.substring(0, lastLetterIndex));
        }

    }
    /**
     * This function encloses an expression, I.E two operands and an operaor in a opeing and closing parenthesis.
     * All precedence in the recursive parser is handled with parentheses.
     * This structuring enforces strict ordering of calculations.
     * @param str
     * @param i
     */
    private void parenthesize(String str, int i) {

        // Insert the closing parenthesis
        instertClosingParenthesis();

        // push the operator
        push(str.charAt(i));
        i--;

        // push the operand,
        // If it is an expression within a parenthesis...
        if (str.charAt(i) == ')') {
            str = insertOpeningParenthesis(str, i);
            i++;
        }
        else {
            while (i >= 0 && (Character.isDigit(str.charAt(i)) ||
                              MathUtils.containsConstant(str.charAt(i)) // || MathUtils.containsFunction(c)
                              )) {
                push(str.charAt(i));
                i--;
            }
            // push opening parenthesis
            push('(');
        }

        // If there is nothing of the string left, return.
        if (i < 0) {
            return;
        }

        // Process the rest of the expression
        pushChars(str.substring(0, i + 1));
    }

    public void instertClosingParenthesis() {

        Node<Character> newNode = new Node<>(closing);
        Node<Character> node = first;

        if ((char) peek() != '(' && (!MathUtils.containsFunction(node.data))) {

            while(node.next != null && Character.isDigit(node.next.data)){
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
            return;
        }

        int nbrOfOpenings = 0;

        while (node.next != null) {
            if (node.data.equals(opening)) {
                nbrOfOpenings += 1;
            }
            else if (MathUtils.containsFunction(node.data)) {
                // pass
            }
            else if (node.data.equals(closing)) {
                nbrOfOpenings -= 1;
                if (nbrOfOpenings <= 0) {
                    break;
                }
            }
            node = node.next;
        }
        newNode.next = node.next;
        node.next = newNode;
    }

    private String insertOpeningParenthesis(String str, int i) {

        int nbrOfOpenings = 0;
        StringBuilder sb = new StringBuilder(str);

        while(i >= 0) {
            if (str.charAt(i) == closing) {
                nbrOfOpenings += 1;
            }
            else if (str.charAt(i) == opening) {
                nbrOfOpenings -= 1;
                if (nbrOfOpenings <= 0) {
                    break;
                }
            }
            i--;
        }
        sb.insert(i, opening);
        return sb.toString();
    }

    public ExpressionStack deepCopy() {
        ExpressionStack clone = new ExpressionStack();
        ExpressionStack tmp = new ExpressionStack();
        // tmp gets a reversed order
        for (char data : this) {
            tmp.push(data);
        }
        // reversed back
        for (char data : tmp) {
            clone.push(data);
        }
        return clone;
    }

}
