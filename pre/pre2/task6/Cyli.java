public class Cyli extends Circle {

    Cyli(double r, double h) {
        super(r, r, h);
    }

    public String toString() {
        return "2.1 " + super.getR1() + " " + super.getHeight();
    }

    public double getR() {
        return super.getR1();
    }

    public double getH() {
        return super.getHeight();
    }

    public boolean equalsto(Shape other) {
        if (other.type().equals(this.type())) {
            return this.getR() == ((Cyli)other).getR() &&
                    this.getH() == ((Cyli)other).getH();

        }
        return false;
    }

    public String type() {
        return "2.1";
    }
}
