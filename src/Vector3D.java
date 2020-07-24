import javafx.scene.paint.Color;

public class Vector3D {

    private final double x, y, z;
    private final Color color;

    public static final Vector3D ZERO_VECTOR = new Vector3D(0, 0, 0, Color.WHITE);

    public Vector3D(double x, double y, double z) {
        this(x, y, z, Color.web("#F7F7FF"));
    }

    public Vector3D(double x, double y, double z, Color color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public Color getColor() { return color; }


    // Rodrigues' rotation formula
    public Vector3D rotate(AxisAngle axisAngle) {
        Vector3D term1 = this.mult(Math.cos(axisAngle.getAngle()));

        Vector3D step1 = this.cross(axisAngle.getAxis());
        Vector3D term2 = step1.mult(Math.sin(axisAngle.getAngle()));

        double step2 = 1 - Math.cos(axisAngle.getAngle());
        double step3 = this.dot(axisAngle.getAxis());
        Vector3D term3 = axisAngle.getAxis().mult(step2 * step3);

        return add(term1, term2, term3);
    }

    // vector dot product
    public double dot(Vector3D v) {
        return x * v.getX() + y * v.getY() + z * v.getZ();
    }

    // vector cross product
    public Vector3D cross(Vector3D v) {
        double newX = y * v.getZ() - z * v.getY();
        double newY = z * v.getX() - x * v.getZ();
        double newZ = x * v.getY() - y * v.getX();
        return new Vector3D(newX, newY, newZ);
    }

    // vector addition
    public static Vector3D add(Vector3D ...v) {
        double newX = 0;
        double newY = 0;
        double newZ = 0;

        for (Vector3D vector : v) {
            newX += vector.getX();
            newY += vector.getY();
            newZ += vector.getZ();
        }

        return new Vector3D(newX, newY, newZ);
    }

    // vector multiplied by scalar
    public Vector3D mult(double scalar) {
        return new Vector3D(x * scalar, y * scalar, z * scalar);
    }

    // 2D projection of this vector with 42/7 config
    public Vector2D project() {
        double SEVEN = Math.toRadians(7);
        double FORTYTWO = Math.toRadians(42);
        double newX = x * Math.cos(SEVEN) + y * Math.cos(FORTYTWO) / 2;
        double newY = z + y * Math.sin(FORTYTWO) / 2 - x * Math.sin(SEVEN);
        return new Vector2D(newX, newY, color);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
