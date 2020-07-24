import javafx.scene.paint.Color;

public class Vector2D {

    private final double x, y;
    private final Color color;

    public Vector2D(double x, double y) {
        this(x, y, Color.web("#F7F7FF"));
    }

    public Vector2D(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Color getColor() { return color; }
}
