public class Cube extends CuboidBox {
    Cube(double length) {
        super(length, length, length);
    }

    double getLength() {
        return super.getLength();
    }

    public double getVolume() {
        return super.getVolume();
    }

    void setLength(double length) {
        super.setLength(length);
    }

    public String toString() {
        return "1.1.1 " + super.getLength();
    }

    public boolean equalsto(Shape other) {
        if (other.type().equals(this.type())) {
            return this.getLength() == ((Cube)other).getLength();
        }
        return false;
    }

    public String type() {
        return "1.1.1";
    }
}
