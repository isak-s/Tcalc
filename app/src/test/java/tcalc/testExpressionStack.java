package tcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expressionParser.ExpressionStack;

public class testExpressionStack {

    ExpressionStack exprStack;

    @BeforeEach
    void setUp() {
        exprStack = new ExpressionStack();
    }

    @AfterEach
    void tearDown() {
        exprStack = null;
    }

    @Test
    void testPushChar() {
        // Act
        exprStack.push('a');
        assertEquals(exprStack.pop(), 'a');
    }

    @Test
    void testPushStringAddsAll() {
        // Act
        exprStack.push("(11 + 2) + (3 + 7)");

        Iterator<Character> itr = exprStack.iterator();

        // Assert
        assertEquals(itr.next(), '(');
        assertEquals(itr.next(), '1');
        assertEquals(itr.next(), '1');
        assertEquals(itr.next(), '+');
        assertEquals(itr.next(), '2');
        assertEquals(itr.next(), ')');
        assertEquals(itr.next(), '+');
        assertEquals(itr.next(), '(');
        assertEquals(itr.next(), '3');
        assertEquals(itr.next(), '+');
        assertEquals(itr.next(), '7');
        assertEquals(itr.next(), ')');
    }

    @Test
    void testIteratorEmptyStack() {
        assertFalse(exprStack.iterator().hasNext());
    }
    @Test
    void testAddEmptyString() {
        exprStack.push("");
        assertFalse(exprStack.iterator().hasNext());
    }
    @Test
    void testPopFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> exprStack.pop());
    }



}
