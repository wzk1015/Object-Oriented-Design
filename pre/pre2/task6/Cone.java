public class Cone extends Circle {
    Cone(double r, double h) {
        super(0, r, h);
    }

    public String toString() {
        return "2.2 " + super.getR2() + " " + super.getHeight();
    }

    public boolean equalsto(Shape other) {
        if (other.type().equals(this.type())) {
            return this.getR() == ((Cone)other).getR() &&
                    this.getH() == ((Cone)other).getH();

        }
        return false;
    }

    public double getR() {
        return super.getR2();
    }

    public double getH() {
        return super.getHeight();
    }

    public String type() {
        return "2.2";
    }
}
