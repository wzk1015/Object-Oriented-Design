public class CuboidBox extends Hex {

    CuboidBox(double length, double width, double height) {
        super(length, 0, width, 0, 0, height);

    }

    public double getVolume() {
        return super.getAx() * super.getBy() * super.getCz();
    }

    double getLength() {
        return super.getAx();
    }

    double getWidth() {
        return super.getBy();
    }

    double getHeight() {
        return super.getCz();
    }

    void setLength(double length) { super.setAx(length); }

    void setWidth(double width) {
        super.setBy(width);
    }

    void setHeight(double height) { super.setCz(height); }

    public String toString() {
        return "1.1 " + getLength() + " " + getWidth() + " " + getHeight();
    }

    public boolean equalsto(Shape other) {
        if (other.type().equals(this.type())) {
            return this.getLength() == ((CuboidBox)other).getLength() &&
                    this.getWidth() == ((CuboidBox)other).getWidth() &&
                    this.getHeight() == ((CuboidBox)other).getHeight();
        }
        return false;
    }

    public String type() {
        return "1.1";
    }
}
