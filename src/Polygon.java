import java.util.ArrayList;

public class Polygon {

    private final ArrayList<Vector2D> vectors = new ArrayList<>();

    public Polygon(double width, double height) {
        vectors.add(new Vector2D(width / 2, height / 2));
        vectors.add(new Vector2D(width / 2, -height / 2));
        vectors.add(new Vector2D(-width / 2, height / 2));
        vectors.add(new Vector2D(-width / 2, -height / 2));
    }

    public Polygon(int sides, int length) {
        double angleInc = Math.PI * 2 / sides;
        for (int i = 0; i < sides; i++) {
            double x = length * Math.cos(angleInc * i);
            double y = length * Math.sin(angleInc * i);
            vectors.add(new Vector2D(x, y));
        }
    }

    public ArrayList<Vector2D> getVectors() {
        return vectors;
    }
}
