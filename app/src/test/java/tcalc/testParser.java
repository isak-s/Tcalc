package tcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import expressionParser.Expression;

public class testParser {

    Expression parser;

    // @BeforeEach
    // void setUp() {

    // }

    // @AfterEach

    @Test
    void testConstructor() {
        parser = new Expression("1 + 1");

        assertEquals(parser.evaluate(), 2);
    }
    @Test
    void testMultipleAdditions() {
        parser = new Expression("1 + 1 + 1 + 1 + 1 + 1");

        assertEquals(parser.evaluate(), 6);
    }
    @Test
    void testMultipleSubtractions() {
        parser = new Expression("1 - 2 - 3 - 4 - 5 - 6");

        assertEquals(parser.evaluate(), -19);
    }
    @Test
    void testMultipleAdditionsAndSubtractions() {
        parser = new Expression("1 + 2 + 3 - 4 - 5 - 6");

        assertEquals(parser.evaluate(), -9);
    }
    @Test
    void testmultiplications() {
        parser = new Expression("5 * 5");

        assertEquals(parser.evaluate(), 25);
    }
    @Test
    void testMultipleMultiplications() {
        parser = new Expression("5 * 5 * 5");

        assertEquals(parser.evaluate(), 125);
    }
    @Test
    void testMultipleMultiplicationsAndAdditions() {
        parser = new Expression("5 * 5  + 1 + 2 * 5");

        assertEquals(parser.evaluate(), 36);
    }
    @Test
    void testMultipleMultiplicationsAndAdditionsOrder() {
        parser = new Expression("1 - 5 * 5  + 1 + 2 * 5");

        assertEquals(parser.evaluate(), -13);
    }
    @Test
    void testParenthesis() {
        parser = new Expression("5 * (5 + 2) - 2");

        assertEquals(parser.evaluate(), 33);
    }

    @Test
    void testManyParenthesese() {
        parser = new Expression("(((5 * ((5 + 2) - 2))))");

        assertEquals(parser.evaluate(), 25);
    }
    @Test
    void testMultipleParenthesese() {
        parser = new Expression("5 * ((5 + 2) - 2)");

        assertEquals(parser.evaluate(), 25);
    }
    @Test
    void testMultiplicationDivisionOrder() {
        parser = new Expression("2 * 3 / 4 * 5");

        assertEquals(parser.evaluate(), 7.5);
    }

    @Test
    void testMultiplicationDivisionOrderWithParenthesese() {
        parser = new Expression("((2 * 3) / 4) * 5");

        assertEquals(parser.evaluate(), 7.5);
    }


}
