import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        ArrayList<Vector> vectorSet = new ArrayList<Vector>();
        double maxl = 0;
        double minl = 999999999;
        for (int k = 0; k < n; k++) {
            double vx = sn.nextDouble();
            double vy = sn.nextDouble();
            double vz = sn.nextDouble();
            Vector newv = new Vector(vx, vy, vz);
            vectorSet.add(newv);
            maxl = max(newv.length(),maxl);
            minl = min(newv.length(),minl);

        }
        int q = sn.nextInt();
        for (int k = 0; k < q; k++) {
            int op = sn.nextInt();
            if (op <= 4) {
                Vector vi = vectorSet.get(sn.nextInt() - 1);
                Vector vj = vectorSet.get(sn.nextInt() - 1);
                switch (op) {
                    case 1:
                        n++;
                        vectorSet.add(vi.sum(vj));
                        System.out.println(vi.sum(vj).show());
                        maxl = max(vi.sum(vj).length(),maxl);
                        minl = min(vi.sum(vj).length(),minl);
                        break;
                    case 2:
                        n++;
                        vectorSet.add(vi.dif(vj));
                        System.out.println(vi.dif(vj).show());
                        maxl = max(vi.dif(vj).length(),maxl);
                        minl = min(vi.dif(vj).length(),minl);
                        break;
                    case 3:
                        System.out.println(vi.innerProduct(vj));
                        break;
                    case 4:
                        n++;
                        vectorSet.add(vi.outProduct(vj));
                        System.out.println(vi.outProduct(vj).show());
                        maxl = max(vi.outProduct(vj).length(),maxl);
                        minl = min(vi.outProduct(vj).length(),minl);
                        break;
                    default:
                }
            }
            else if (op == 5) {
                System.out.println(maxl);
            }
            else if (op == 6) {
                System.out.println(minl);
            }
        }
    }
}