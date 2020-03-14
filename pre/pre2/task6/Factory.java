import java.util.Scanner;

public class Factory {
    public Shape getShape(Scanner sn) {
        String type = sn.next();
        if (type.equals("1")) {
            return new Hex(sn.nextDouble(), sn.nextDouble(), sn.nextDouble(),
                    sn.nextDouble(), sn.nextDouble(), sn.nextDouble());
        } else if (type.equals("1.1")) {
            return new CuboidBox(sn.nextDouble(),
                    sn.nextDouble(), sn.nextDouble());
        } else if (type.equals("1.1.1")) {
            return new Cube(sn.nextDouble());
        } else if (type.equals("2")) {
            return new Circle(sn.nextDouble(),
                    sn.nextDouble(), sn.nextDouble());
        } else if (type.equals("2.1")) {
            return new Cyli(sn.nextDouble(), sn.nextDouble());
        } else if (type.equals("2.2")) {
            return new Cone(sn.nextDouble(), sn.nextDouble());
        } else if (type.equals("3")) {
            return new Sphere(sn.nextDouble());
        } else {
            System.out.println("Wrong!");
        }
        return null;
    }
}
