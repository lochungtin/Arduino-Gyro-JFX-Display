public class AxisAngle {

    private final Vector3D axis;
    private final double angle;

    public AxisAngle(Vector3D axis, double angle) {
        this.axis = axis;
        this.angle = angle;
    }

    public Vector3D getAxis() { return axis; }
    public double getAngle() { return angle; }
}
