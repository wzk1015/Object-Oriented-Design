public class CuboidBox {
    private double length;
    private double width;
    private double height;

    CuboidBox(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getVolume() {
        return this.length * this.width * this.height;
    }

    double getLength() {
        return this.length;
    }

    double getWidth() {
        return this.width;
    }

    double getHeight() {
        return this.height;
    }
}
