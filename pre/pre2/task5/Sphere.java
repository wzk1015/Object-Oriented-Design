import static java.lang.Math.PI;

public class Sphere implements Shape {
    private double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    public double getVolume() {
        return 4 * PI * radius * radius * radius / 3;
    }

    public double getRadius() {
        return radius;
    }

    public String toString() {
        return radius + "";
    }
}
