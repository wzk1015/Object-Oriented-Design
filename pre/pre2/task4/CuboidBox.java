public class CuboidBox extends Hex {
    private double length;
    private double width;
    private double height;

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
}
