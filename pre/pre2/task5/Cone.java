public class Cone extends Circle {
    Cone(double r, double h) {
        super(0, r, h);
    }

    public String toString() {
        return super.getR2() + " " + super.getHeight();
    }
}
