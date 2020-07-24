public class Quaternion {

    private final double w, x, y, z;

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double[] getQuaternion() {
        return new double[]{w, x, y ,z};
    }

    public AxisAngle toAxisAngle() {
        double angle = Math.acos(w) * 2;
        double denom = Math.sqrt(1 - w * w);
        denom = denom > Double.MAX_VALUE ? 1 : 1 / denom;
        double x = this.x * denom;
        double y = this.y * denom;
        double z = this.z * denom;
        return new AxisAngle(new Vector3D(x, y, z), angle);
    }
}
