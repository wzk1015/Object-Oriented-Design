import static java.lang.Math.PI;

public class Circle implements Shape {
    private double r1;
    private double r2;
    private double height;

    Circle(double r1, double r2, double height) {
        this.r1 = r1;
        this.r2 = r2;
        this.height = height;
    }

    public double getVolume() {
        return PI * (r1 * r1 + r1 * r2 + r2 * r2) * height / 3;
    }

    public double getR1() {
        return r1;
    }

    public double getR2() {
        return r2;
    }

    public double getHeight() {
        return height;
    }

    public String toString() {
        return "2 " + getR1() + " " + getR2() + " " + getHeight();
    }

    public boolean equalsto(Shape other) {
        if (other.type().equals(this.type())) {
            return this.r1 == ((Circle) other).r1 &&
                this.r2 == ((Circle) other).r2 &&
                this.height == ((Circle) other).height;
        }
        return false;
    }

    public String type() {
        return "2";
    }
}
