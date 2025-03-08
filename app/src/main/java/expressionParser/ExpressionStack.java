package expressionParser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Set;

public class ExpressionStack implements Iterable<Character> {

    Set<Character> prioritizedOperators = Set.of('*', '/', '^');
    Set<Character> operators = Set.of('+', '-');

    private Stack<Character> charStack;

    private int size;


    public ExpressionStack(String str) {
        size = 0;
        charStack = new Stack<>();
        push(str);
    }

    public ExpressionStack() {
        size = 0;
        charStack = new Stack<>();
    }

    public void push(char c) {
        size++;
        charStack.push(c);
    }


    public void push(String str) {
        pushChars(str.strip().replace(" ", ""));
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

        if (prioritizedOperators.contains(currChar)) {
            parenthesize(str, lastLetterIndex);
        }
        else {
            push(currChar);
            pushChars(str.substring(0, lastLetterIndex));
        }

    }

    private void parenthesize(String str, int i) {

        // Insert the closing parenthesis

        charStack.instertClosingParenthesis(')');

        // push the operator
        push(str.charAt(i));
        i--;

        // push the operand
        System.out.println(str.charAt(i));
        while (i >= 0 && Character.isDigit(str.charAt(i))) {
            push(str.charAt(i));
            i--;
        }
        // push opening parenthesis
        push('(');

        if (i < 0) {
            return;
        }

        pushChars(str.substring(0, i + 1));
    }

    @Override
    public String toString() {
        return charStack.toString();
    }

    public Character pop() throws NoSuchElementException {
        size--;
        return charStack.pop();
    }

    public Character peek() {
        return charStack.peek();
    }

    public boolean isEmpty() {
        return charStack.first == null;
    }

    public int getSize() {
        return size;
    }


    @Override
    public Iterator<Character> iterator() {
        return new ExpressionStackIterator();
    }

    private class ExpressionStackIterator implements Iterator<Character> {

        @Override
        public boolean hasNext() {
            return charStack.first != null;
        }

        @Override
        public Character next() {
            return charStack.pop();
        }

    }

}
