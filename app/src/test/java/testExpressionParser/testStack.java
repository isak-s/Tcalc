package testExpressionParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

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
    @Test
    void testIteratorHasNextEmptyStack() {
        Iterator<Character> itr = myStack.iterator();
        assertFalse(itr.hasNext());
    }
    @Test
    void testIteratorHasNextPopulatedStack() {
        Iterator<Character> itr = myStack.iterator();
        myStack.push(')');
        assertTrue(itr.hasNext());
    }

    @Test
    void testIterator() {
        myStack.push(')');
        myStack.push(')');
        myStack.push(')');
        myStack.push(')');
        myStack.push(')');
        myStack.push(')');

        Iterator<Character> itr = myStack.iterator();

        while (itr.hasNext()) {
            assertEquals(itr.next(), ')');
        }

    }
}
