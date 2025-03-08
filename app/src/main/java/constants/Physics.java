package constants;

import java.util.HashMap;
import java.util.Map;

public class Physics {
    private static final Map<Character, Double> CONSTANTS = new HashMap<>();

    static {
        CONSTANTS.put('c', 299792458.0);      // Speed of light (m/s)
        CONSTANTS.put('h', 6.62607015e-34);   // Planck's constant (J·s)
    }

    public double getConstant(char key) {
        return CONSTANTS.get(key);
    }
    public boolean contains(char key) {
        return CONSTANTS.containsKey(key);
    }
}
