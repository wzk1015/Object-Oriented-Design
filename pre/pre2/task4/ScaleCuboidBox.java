public class ScaleCuboidBox {
    private double length;
    private double width;
    private double height;

    public double getVolume() {
        return this.length * this.width * this.height;
    }

    ScaleCuboidBox(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
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

    void setLength(double length) {
        this.length = length;
    }

    void setWidth(double width) {
        this.width = width;
    }

    void setHeight(double height) {
        this.height = height;
    }
}

