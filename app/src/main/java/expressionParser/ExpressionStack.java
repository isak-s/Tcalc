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
        push(')');
        push(str);
    }

    public ExpressionStack() {
        size = 0;
        charStack = new Stack<>();
        push(')');
    }

    // public void push(char c) {
    //     if (!prioritizedOperators.contains(c)) {
    //         charStack.push(c);
    //     }
    //     Stack.Node<Character> pre = charStack.first.next;
    //     charStack.push(')');
    // }
    public void push(char c) {
        size++;
        System.out.println("Pushing " + String.valueOf(c));
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
        push(str.charAt(lastLetterIndex));
        pushChars(str.substring(0, lastLetterIndex));
    }

    private void parenthesize() {

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
            return charStack.peek() != null;
        }

        @Override
        public Character next() {
            return charStack.pop();
        }

    }

}
