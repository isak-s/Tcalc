package expressionParser;

import java.util.Iterator;

public class FunctionStepper implements Iterable<Double> {

    String input;
    double stopX;
    double step;
    double pos;

    public FunctionStepper(String input, double startX, double stopX, double step) throws IllegalArgumentException {
        if (!input.contains("x")) {
            throw new IllegalArgumentException("A function must contain a variable x");
        }
        this.pos = startX;
        this.stopX = stopX;
        this.step = step;
        this.input = input;
    }
    public double getPos() {
        return pos;
    }
    @Override
    public Iterator<Double> iterator() {
        return new FunctionIterator();
    }
    @Override
    public String toString() {
        // StringBuilder sb = new StringBuilder();
        return "Not implemented";
    }

    private class FunctionIterator implements Iterator<Double> {
        @Override
        public boolean hasNext() {
            return pos + step <= stopX;
        }
        @Override
        public Double next() {
            Expression expression = new Expression(input.replace("x", "(" + String.valueOf((int) pos) + ")"));
            pos += step;
            return expression.evaluate();
        }
    }
}
