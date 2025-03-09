import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expressionParser.Expression;

public class testMathUtilsIntegration {

    Expression expression;

    @Test
    void testConstructorConvertsSymbol() {
        expression = new Expression("1 + sin(30)");
        assertEquals("1+s(30)", expression.toString());
    }

    @Test
    void testConstructorConvertsSymbols() {
        expression = new Expression("cos(30) + sin(30) + tan(30)");
        assertEquals("ℂ(30)+s(30)+t(30)", expression.toString());
    }
    @Test
    void testConstructorConvertsNestedSymbols() {
        expression = new Expression("cos(sin(tan(30)))");
        assertEquals("ℂ(s(t(30)))", expression.toString());
    }
    @Test
    void testConstructorParenthesisesfunctions() {
        expression = new Expression("5 * cos(30)");
        assertEquals("(5*ℂ(30))", expression.toString());
    }

    @Test
    void testGetResultOfMathFunctionAsOperand() {
        expression = new Expression("1 + sin(90)");
        assertEquals(2, expression.evaluate());
    }
    @Test
    void testGetResultOfMultipleMathFunctionAsOperands() {
        expression = new Expression("cos(0) + sin(90) + tan(45)");
        assertEquals(3, expression.evaluate());
    }
    @Test
    void testGetResultOfMultipleNestedMathFunctionAsOperands() {
        expression = new Expression("cos(sin(tan(0)))");
        assertEquals(1, expression.evaluate());
    }

}
