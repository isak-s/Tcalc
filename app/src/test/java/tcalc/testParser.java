package tcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expressionParser.Parser;

public class testParser {

    Parser parser;

    // @BeforeEach
    // void setUp() {

    // }

    // @AfterEach

    @Test
    void testConstructor() {
        parser = new Parser("1 + 1");

        assertEquals(parser.evaluate(), 2);
    }
    @Test
    void testMultipleAdditions() {
        parser = new Parser("1 + 1 + 1 + 1 + 1 + 1");

        assertEquals(parser.evaluate(), 6);
    }
    @Test
    void testMultipleSubtractions() {
        parser = new Parser("1 - 2 - 3 - 4 - 5 - 6");

        assertEquals(parser.evaluate(), -19);
    }
    @Test
    void testMultipleAdditionsAndSubtractions() {
        parser = new Parser("1 + 2 + 3 - 4 - 5 - 6");

        assertEquals(parser.evaluate(), -9);
    }
    @Test
    void testmultiplications() {
        parser = new Parser("5 * 5");

        assertEquals(parser.evaluate(), 25);
    }
    @Test
    void testMultipleMultiplications() {
        parser = new Parser("5 * 5 * 5");

        assertEquals(parser.toString(), "(5*(5*5))");
        assertEquals(parser.evaluate(), 125);
    }
    @Test
    void testMultipleMultiplicationsAndAdditions() {
        parser = new Parser("5 * 5  + 1 + 2 * 5");

        assertEquals("(5*5)+1+(2*5)", parser.toString());
        assertEquals(parser.evaluate(), 36);
    }
    @Test
    void testMultipleMultiplicationsAndAdditionsOrder() {
        parser = new Parser("1 - 5 * 5  + 1 + 2 * 5");

        assertEquals("1-(5*5)+1+(2*5)", parser.toString());
        assertEquals(parser.evaluate(), -13);
    }
    @Test
    void testParenthesis() {
        parser = new Parser("5 * (5 + 2) - 2");

        assertEquals(parser.toString(), "(5*(5+2))-2");
        assertEquals(parser.evaluate(), 33);
    }

    @Test
    void testManyParenthesese() {
        parser = new Parser("(((5 * ((5 + 2) - 2))))");

        assertEquals(parser.toString(), "((((5*((5+2)-2)))))");
        assertEquals(parser.evaluate(), 25);
    }
    @Test
    void testMultipleParenthesese() {
        parser = new Parser("5 * ((5 + 2) - 2)");

        assertEquals(parser.evaluate(), 25);
    }


}
