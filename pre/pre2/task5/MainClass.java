import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        int m = sn.nextInt();
        Shape[][] set = new Shape[n][1000];
        int[] len = new int[n];
        String[] maxinfo = new String[n];
        double[] maxv = new double[n];
        double[] sumv = new double[n];

        for (int k = 0; k < m; k++) {
            int op = sn.nextInt();
            int i = sn.nextInt() - 1;
            switch (op) {
                case 1:
                    System.out.println(maxv[i]);
                    break;
                case 2:
                    System.out.println(maxinfo[i]);
                    break;
                case 3:
                    System.out.println(sumv[i]);
                    break;
                case 4:
                    String type = sn.next();
                    if (type.equals("1")) {
                        Hex hex = new Hex(sn.nextDouble(), sn.nextDouble(),
                                sn.nextDouble(), sn.nextDouble(),
                                sn.nextDouble(), sn.nextDouble());
                        set[i][len[i]] = hex;
                    } else if (type.equals("1.1")) {
                        CuboidBox cbox = new CuboidBox(sn.nextDouble(),
                                sn.nextDouble(), sn.nextDouble());
                        set[i][len[i]] = cbox;
                    } else if (type.equals("1.1.1")) {
                        Cube cube = new Cube(sn.nextDouble());
                        set[i][len[i]] = cube;
                    } else if (type.equals("2")) {
                        Circle circle = new Circle(sn.nextDouble(),
                                sn.nextDouble(), sn.nextDouble());
                        set[i][len[i]] = circle;
                    } else if (type.equals("2.1")) {
                        Cyli cy = new Cyli(sn.nextDouble(), sn.nextDouble());
                        set[i][len[i]] = cy;
                    } else if (type.equals("2.2")) {
                        Cone co = new Cone(sn.nextDouble(), sn.nextDouble());
                        set[i][len[i]] = co;
                    } else if (type.equals("3")) {
                        Sphere ball = new Sphere(sn.nextDouble());
                        set[i][len[i]] = ball;
                    } if (maxv[i] < set[i][len[i]].getVolume()) {
                        maxv[i] = set[i][len[i]].getVolume();
                        maxinfo[i] = type + " " + set[i][len[i]].toString();
                    }
                    sumv[i] += set[i][len[i]].getVolume();
                    len[i]++;
                    break;
                default:
            }

        }
    }
}