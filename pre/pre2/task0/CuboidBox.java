public class CuboidBox {
    private double length;
    private double width;
    private double height;

    public void printVolume() {
        double volume = this.length * this.width * this.height;
        System.out.println("The volume of the box is " + volume + ".");
    }

    public void setLength(double x) {
        this.length = x;
    }

    public void setWidth(double x) {
        this.width = x;
    }

    public void setHeight(double x) {
        this.height = x;
    }
}
