package testExpressionParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expressionParser.Expression;

public class testEvaluate {

    Expression expression;

    @Test
    void testConstructor() {
        expression = new Expression("1 + 1");

        assertEquals(expression.evaluate(), 2);
    }
    @Test
    void testZero() {
        expression = new Expression("1 + 0 + 0 + 0");

        assertEquals(expression.evaluate(), 1);
    }
    @Test
    void testMultipleAdditions() {
        expression = new Expression("1 + 1 + 1 + 1 + 1 + 1");

        assertEquals(expression.evaluate(), 6);
    }
    @Test
    void testMultipleSubtractions() {
        expression = new Expression("1 - 2 - 3 - 4 - 5 - 6");

        assertEquals(expression.evaluate(), -19);
    }
    @Test
    void testMultipleAdditionsAndSubtractions() {
        expression = new Expression("1 + 2 + 3 - 4 - 5 - 6");

        assertEquals(expression.evaluate(), -9);
    }
    @Test
    void testmultiplications() {
        expression = new Expression("5 * 5");

        assertEquals(expression.evaluate(), 25);
    }
    @Test
    void testMultipleMultiplications() {
        expression = new Expression("5 * 5 * 5");

        assertEquals(expression.evaluate(), 125);
    }
    @Test
    void testMultipleMultiplicationsAndAdditions() {
        expression = new Expression("5 * 5  + 1 + 2 * 5");

        assertEquals(expression.evaluate(), 36);
    }
    @Test
    void testMultipleMultiplicationsAndAdditionsOrder() {
        expression = new Expression("1 - 5 * 5  + 1 + 2 * 5");

        assertEquals(expression.evaluate(), -13);
    }
    @Test
    void testParenthesis() {
        expression = new Expression("5 * (5 + 2) - 2");

        assertEquals(expression.evaluate(), 33);
    }

    @Test
    void testManyParenthesese() {
        expression = new Expression("(((5 * ((5 + 2) - 2))))");

        assertEquals(expression.evaluate(), 25);
    }
    @Test
    void testMultipleParenthesese() {
        expression = new Expression("5 * ((5 + 2) - 2)");

        assertEquals(expression.evaluate(), 25);
    }

    @Test
    void testMultiplicationDivisionOrderWithParenthesese() {
        expression = new Expression("((2 * 3) / 4) * 5");

        assertEquals(expression.evaluate(), 7.5);
    }

    @Test
    void testMultiplicationDivisionOrder() {
        expression = new Expression("(2 * 3) / (4 * 5)");

        assertEquals(expression.evaluate(), 0.3);
    }

}
