package tcalc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expressionParser.Parser;

public class testParser {

    Parser parser;

    @BeforeEach
    void setUp() {

    }

    @AfterEach

    @Test
    void testConstructor() {
        parser = new Parser("1 + 1");
    }

}
