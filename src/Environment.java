import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Environment {

    private final GraphicsContext gc;
    public final double H_OFFSET, V_OFFSET;

    private final Vector3D xPos, xNeg, yPos, yNeg, zPos, zNeg, origin;

    private final Solid solid = new Solid(new Polygon(6, 50), 200);
    private final Vector3D pointer = new Vector3D(0, 200, 0);

    public Environment(GraphicsContext gc) {
        this.gc = gc;
        H_OFFSET = gc.getCanvas().getWidth() / 2;
        V_OFFSET = gc.getCanvas().getHeight() / 2;

        xPos = new Vector3D(H_OFFSET, 0, 0, Color.web("#BDD5EA"));
        xNeg = new Vector3D(-H_OFFSET, 0, 0, Color.web("#577399"));
        yPos = new Vector3D(0,H_OFFSET,  0, Color.web("#BDD5EA"));
        yNeg = new Vector3D(0,-H_OFFSET,  0, Color.web("#577399"));
        zPos = new Vector3D(0, 0, V_OFFSET, Color.web("#BDD5EA"));
        zNeg = new Vector3D(0, 0, -V_OFFSET, Color.web("#577399"));
        origin = Vector3D.ZERO_VECTOR;
    }

    public void update(AxisAngle axisAngle) {
        axisPlot();

        Vector3D center = pointer.rotate(axisAngle).mult(0.5);

        drawVertex(center);

        for (final Vector3D vector : solid.getVectors()) {
            drawVertex(Vector3D.add(vector.rotate(axisAngle), center));
        }
    }

    public void axisPlot() {
        gc.clearRect(0, 0, H_OFFSET * 2, V_OFFSET * 2);

        drawLine(xPos, origin);
        drawLine(xNeg, origin);
        drawLine(yPos, origin);
        drawLine(yNeg, origin);
        drawLine(zPos, origin);
        drawLine(zNeg, origin);
        drawVertex(origin);
    }

    private void drawVertex(Vector3D v) {
        Vector2D project = v.project();
        gc.setFill(v.getColor());
        gc.fillOval(H_OFFSET + project.getX() - 2.5, V_OFFSET - project.getY() - 2.5, 5, 5);

        //System.out.println(v.toString());
    }

    private void drawLine(Vector3D v1, Vector3D v2) {
        Vector2D p1 = v1.project();
        Vector2D p2 = v2.project();
        gc.setStroke(v1.getColor());
        gc.setLineWidth(2.5);
        gc.strokeLine(H_OFFSET + p1.getX(), V_OFFSET - p1.getY(), H_OFFSET + p2.getX(), V_OFFSET - p2.getY());
    }
}
