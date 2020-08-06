package guia2;

public class Point {
    private double x;
    private double y;
    private double z;
    private double distToOrigin;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.computeDistToOrigin();
    }

    private void computeDistToOrigin() {
        this.distToOrigin = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public double getDistToOrigin() {
        return this.distToOrigin;
    }

    @Override
    public String toString() {
        return ("(" + this.x + ", " + this.y + ", " + this.z + ")");
    }
}
