package testExpressionParser;

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
    @Test
    void testParenthesiseSingleDigitNumbers() {

        exprStack.push("5 * 5 * 5");

        assertEquals(exprStack.toString(), "(5*(5*5))");
    }
    @Test
    void testParenthesiseMultiplicationsAndAdditions() {
        exprStack.push("5 * 5  + 1 + 2 * 5");

        assertEquals("(5*5)+1+(2*5)", exprStack.toString());
    }
    @Test
    void testmanyParenthesese() {
        exprStack.push("(((5 * ((5 + 2) - 2))))");

        assertEquals(exprStack.toString(), "((((5*((5+2)-2)))))");
    }
    @Test
    void testParenthesisOrdering() {
        exprStack.push("1 - 5 * 5  + 1 + 2 * 5");

        assertEquals("1-(5*5)+1+(2*5)", exprStack.toString());
    }
    @Test
    void testAdditionParenthesis() {
        exprStack.push("5 * (5 + 2) - 2");

        assertEquals(exprStack.toString(), "(5*(5+2))-2");
    }

    // AI GENERATED TESTS
        @Test
        void testNestedParenthesesWithMultiplication() {
            exprStack.push("(2 + 3) * (4 + 5)");

            assertEquals(exprStack.toString(), "((2+3)*(4+5))");
        }

        @Test
        void testMultipleMultiplicationsInSequence() {
            exprStack.push("2 * 3 * 4 * 5");

            assertEquals(exprStack.toString(), "(2*(3*(4*5)))");
        }

        // This is not how math works btw..
        @Test
        void testMultipleMultiplicationsAndDivisions() {
            exprStack.push("2 * 3 / 4 * 5");

            assertEquals(exprStack.toString(), "(2*(3/(4*5)))");
        }
        @Test
        void testMultipleMultiplicationsAndDivisionsWithParenthese() {
            exprStack.push("((2 * 3) / 4) * 5");

            assertEquals(exprStack.toString(), "(((((2*3))/4))*5)");
        }


        @Test
        void testDivisionInsideParentheses() {
            exprStack.push("(10 / 2) * (3 + 4)");

            assertEquals(exprStack.toString(), "(((10/2))*(3+4))");
        }

        @Test
        void testComplexExpressionWithDifferentOperators() {
            exprStack.push("1 + 2 * 3 - 4 / 2 + 5");

            assertEquals(exprStack.toString(), "1+(2*3)-(4/2)+5");
        }

        // @Test
        // void testParenthesesWithExponentiation() {
        //     exprStack.push("2 ^ (3 + 1) * 5");

        //     assertEquals(exprStack.toString(), "(2^(3+1))*5");
        // }

        @Test
        void testParenthesesWithNegativeNumbers() {
            exprStack.push("-5 * (-2 + 3)");

            assertEquals(exprStack.toString(), "-(5*(0-2+3))");
        }

        @Test
        void testOnlyParenthesesShouldNotChange() {
            exprStack.push("(((((5)))))");

            assertEquals(exprStack.toString(), "(((((5)))))");
        }

}
