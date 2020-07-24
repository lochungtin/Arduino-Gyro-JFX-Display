import java.util.ArrayList;

public class Solid {

    private final ArrayList<Vector3D> vectors = new ArrayList<>();

    public Solid(Polygon base, double length) {

        for (final Vector2D vertex : base.getVectors()) {
            vectors.add(new Vector3D(vertex.getX(), length / 2, vertex.getY()));
            vectors.add(new Vector3D(vertex.getX(), -length / 2, vertex.getY()));
        }
    }

    public ArrayList<Vector3D> getVectors() {
        return vectors;
    }
}
