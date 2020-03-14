public class Cyli extends Circle {

    Cyli(double r, double h) {
        super(r, r, h);
    }

    public String toString() {
        return super.getR1() + " " + super.getHeight();
    }
}
