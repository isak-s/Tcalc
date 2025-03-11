package plotting;

public class Point {
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getScaledX(double minX, double scale) {
        return (int) ((x - minX) * scale);
    }
    public int getScaledY(double minY, double scale) {
        return (int) ((y - minY) * scale);
    }
}
