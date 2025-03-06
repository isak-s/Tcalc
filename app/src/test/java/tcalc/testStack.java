package tcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expressionParser.Stack;

public class testStack {

    Stack<Character> myStack;


    @BeforeEach
    void setUp() {
        myStack = new Stack<>();
    }

    @AfterEach
    void tearDown() {
        myStack = null;
    }

    @Test
    void testPushOne() {
        myStack.push('(');
        assertEquals('(', myStack.pop());
    }
    @Test
    void testPushMany() {
        myStack.push(')');
        myStack.push(')');
        myStack.push(')');
        myStack.push('(');
        myStack.push('(');
        myStack.push('(');
        assertEquals('(', myStack.pop());
        assertEquals('(', myStack.pop());
        assertEquals('(', myStack.pop());
        assertEquals(')', myStack.pop());
        assertEquals(')', myStack.pop());
        assertEquals(')', myStack.pop());

    }

}
