import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int n;
        int m;
        Scanner sn = new Scanner(System.in);
        n = sn.nextInt();
        m = sn.nextInt();
        CuboidBox[][] groups = new CuboidBox[n][200];
        int[] len = new int[n];

        for (int k = 0; k < m; k++) {
            int op = sn.nextInt();
            int i = sn.nextInt() - 1;

            switch (op) {
                case 1:
                    double maxv = 0;
                    for (int j = 0; j < len[i]; j++) {
                        if (groups[i][j].getVolume() > maxv) {
                            maxv = groups[i][j].getVolume();
                        }
                    }
                    System.out.println(maxv);
                    break;
                case 2:
                    double sumv = 0;
                    for (int j = 0; j < len[i]; j++) {
                        sumv += groups[i][j].getVolume();
                    }
                    System.out.println(sumv);
                    break;
                case 3:
                    double l = sn.nextDouble();
                    double w = sn.nextDouble();
                    double h = sn.nextDouble();
                    groups[i][len[i]] = new CuboidBox(l, w, h);
                    len[i]++;
                    break;
                default:
            }
        }

    }

}