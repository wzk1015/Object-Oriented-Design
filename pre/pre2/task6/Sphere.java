import static java.lang.Math.PI;

public class Sphere implements Shape {
    private double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    public boolean equalsto(Shape other) {
        if (other.type().equals(this.type())) {
            return this.radius == ((Sphere) other).radius;
        }
        return false;
    }

    public double getVolume() {
        return 4 * PI * radius * radius * radius / 3;
    }

    public double getRadius() {
        return radius;
    }

    public String toString() {
        return "3 " + radius;
    }

    public String type() {
        return "3";
    }
}
