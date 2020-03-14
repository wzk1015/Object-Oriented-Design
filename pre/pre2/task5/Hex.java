public class Hex implements Shape {
    private double ax;
    private double bx;
    private double by;
    private double cx;
    private double cy;
    private double cz;

    Hex(double ax, double bx, double by,
        double cx, double cy, double cz) {
        this.ax = ax;
        this.bx = bx;
        this.by = by;
        this.cx = cx;
        this.cy = cy;
        this.cz = cz;
    }

    public double getVolume() {
        return ax * by * cz;
    }

    double getAx() {
        return ax;
    }

    double getBx() {
        return bx;
    }

    double getBy() {
        return by;
    }

    double getCx() {
        return cx;
    }

    double getCy() {
        return cy;
    }

    double getCz() {
        return cz;
    }

    void setAx(double x) {
        ax = x;
    }

    void setBx(double x) {
        bx = x;
    }

    void setBy(double x) {
        by = x;
    }

    void setCx(double x) {
        cx = x;
    }

    void setCy(double x) {
        cy = x;
    }

    void setCz(double x) {
        cz = x;
    }

    public String toString() {
        return ax + " " + bx + " " + by + " " + cx + " " + cy + " " + cz;
    }

}
