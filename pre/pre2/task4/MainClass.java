import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        int m = sn.nextInt();
        //ArrayList<Hex>[] set = new ArrayList[n];
        //ArrayList<Hex> set[] = new ArrayList[n];
        Hex[][] set = new Hex[n][1000];
        int[] len = new int[n];
        String[] maxtype = new String[n];
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
                    System.out.println(maxtype[i]);
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
                        len[i]++;
                    } else if (type.equals("1.1")) {
                        CuboidBox cbox = new CuboidBox(sn.nextDouble(),
                                sn.nextDouble(), sn.nextDouble());
                        set[i][len[i]] = cbox;
                        len[i]++;
                    } else if (type.equals("1.1.1")) {
                        Cube cube = new Cube(sn.nextDouble());
                        set[i][len[i]] = cube;
                        len[i]++;
                    }
                    double volume = set[i][len[i] - 1].getVolume();
                    if (maxv[i] < volume) {
                        maxv[i] = volume;
                        maxtype[i] = type;
                    }
                    sumv[i] += volume;
                    break;
                default:
            }

        }
    }
}