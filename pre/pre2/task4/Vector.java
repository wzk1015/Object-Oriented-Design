import static java.lang.Math.sqrt;

public class Vector {
    private double vx;
    private double vy;
    private double vz;

    Vector(double x, double y, double z) {
        this.vx = x;
        this.vy = y;
        this.vz = z;
    }

    double length() {
        return sqrt(vx * vx + vy * vy + vz * vz);
    }

    double getVx() {
        return vx;
    }

    double getVy() {
        return vy;
    }

    double getVz() {
        return vz;
    }

    String show() {
        return vx + " " + vy + " " + vz;
    }

    Vector sum(Vector other) {
        double newx = this.getVx() + other.getVx();
        double newy = this.getVy() + other.getVy();
        double newz = this.getVz() + other.getVz();
        Vector newv = new Vector(newx, newy, newz);
        return newv;
    }

    Vector dif(Vector other) {
        double newx = this.getVx() - other.getVx();
        double newy = this.getVy() - other.getVy();
        double newz = this.getVz() - other.getVz();
        Vector newv = new Vector(newx, newy, newz);
        return newv;
    }

    Vector outProduct(Vector other) {
        double newx = this.getVy() * other.getVz() -
                other.getVy() * this.getVz();
        double newy = this.getVz() * other.getVx() -
                other.getVz() * this.getVx();
        double newz = this.getVx() * other.getVy() -
                other.getVx() * this.getVy();
        Vector newv = new Vector(newx, newy, newz);
        return newv;
    }

    double innerProduct(Vector other) {
        double ip = this.getVx() * other.getVx() +
                this.getVy() * other.getVy() +
                this.getVz() * other.getVz();
        return ip;
    }

}
