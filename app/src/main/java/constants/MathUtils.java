package constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class MathUtils {
    private static final Map<Character, Double> CONSTANTS = new HashMap<>();
    private static final Set<Character> functions = Set.of('s', 'ℂ', 't');

    static {
        CONSTANTS.put('p', Math.PI);      // pi, shortened to p
        CONSTANTS.put('c', 299792458.0);      // Speed of light (m/s)
        CONSTANTS.put('h', 6.62607015e-34);   // Planck's constant (J·s)
    }

    public static double getConstant(char key) {
        return CONSTANTS.get(key);
    }
    public static boolean containsConstant(char key) {
        return CONSTANTS.containsKey(key);
    }

    public static boolean containsFunction(char key) {
        return functions.contains(key);
    }

    /**
     * Converts all symbols and expressions into single character representations.
     * Pi becomes p, sin becomes s etc.
     * @param str
     * @return
     */
    public static String convertSymbols(String str) {
        return str.replace("sin", "s").
            replace("cos", "ℂ").
            replace("tan", "t").
            replace("pi", "p").
            replace("(-", "(0-").  // Handles negative numbers
            // These insert * in between operands that are adjacent to each other.
            replaceAll("(?<=[0-9pℂht])(?=[pℂht(])", "*"). // Number/constant followed by constant or '('
            replaceAll("(?<=\\))(?=[0-9pℂht])", "*").    // ')' followed by number/constant
            replaceAll("(?<=\\))(?=\\()", "*"); // ) followed by (
    }

    public static double calculateFunction(char function, double operand) {
        switch (function) {
            case 's':
                return Math.sin(operand * Math.PI / 180);
            case 'ℂ':
                return Math.cos(operand * Math.PI / 180);
            case 't':
                return Math.tan(operand * Math.PI / 180);
            default:
                throw new IllegalArgumentException();
        }
    }
}
