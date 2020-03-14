import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        int m = sn.nextInt();
        Shape[][] set = new Shape[10000][6000];
        int setcount = n;
        int[] len = new int[10000];
        String[] maxinfo = new String[10000];
        double[] maxv = new double[10000];
        double[] sumv = new double[10000];

        for (int k = 0; k < m; k++) {
            int op = sn.nextInt();
            int i = sn.nextInt() - 1;
            switch (op) {
                case 1:
                    printWithError(len[i],maxv[i] + "");
                    break;
                case 2:
                    printWithError(len[i],maxinfo[i]);
                    break;
                case 3:
                    System.out.println(sumv[i]);
                    break;
                case 4:
                    Factory factory = new Factory();
                    Shape newshape = factory.getShape(sn);
                    if (Inside(newshape, set[i])) {
                        break;
                    }
                    set[i][len[i]] = newshape;
                    if (maxv[i] <= newshape.getVolume()) {
                        maxv[i] = newshape.getVolume();
                        maxinfo[i] = newshape.toString();
                    }
                    sumv[i] += newshape.getVolume();
                    len[i]++;
                    break;
                case 5:
                    int j = sn.nextInt() - 1;
                    set[setcount] = getUnionSet(set[i], set[j], sumv,
                            len, setcount);
                    if (maxv[i] >= maxv[j] && maxinfo[i] != null) {
                        maxv[setcount] = maxv[i];
                        maxinfo[setcount] = maxinfo[i];
                    } else {
                        maxv[setcount] = maxv[j];
                        maxinfo[setcount] = maxinfo[j];
                    }
                    setcount++;
                    break;
                default:
            }
        }
    }

    public static boolean Inside(Shape newshape, Shape[] seti) {

        for (Shape oldshape: seti) {
            if (oldshape == null) {
                return false;
            }
            if (oldshape.equalsto(newshape)) {
                return true;
            }
        }
        return false;
    }

    public static Shape[] getUnionSet(Shape[] set1, Shape[] set2,
                              double[] sumv, int[] len, int setcount) {
        int count = 0;
        Shape[] newset = new Shape[10000];
        for (Shape shape1:set1) {
            if (shape1 == null) {
                break;
            }
            newset[count] = shape1;
            sumv[setcount] += shape1.getVolume();
            count++;
        }
        for (Shape shape2:set2) {
            if (shape2 == null) {
                break;
            }
            boolean mark = true;
            for (Shape shape3:newset) {
                if (shape3 == null) {
                    break;
                }
                if (shape3.equalsto(shape2)) {
                    mark = false;
                    break;
                }
            }
            if (mark) {
                newset[count] = shape2;
                sumv[setcount] += shape2.getVolume();
                count++;
            }
        }
        len[setcount] = count;
        return newset;
    }

    public static void printWithError(int leni, String out) {
        if (leni != 0) {
            System.out.println(out);
        }
        else {
            System.out.println("Sorry, the set is empty!");
        }
    }

    public static void test() {
        Cube cb1 = new Cube(5);
        Cube cb2 = new Cube(5);
        System.out.println(cb1.equalsto(cb2));
    }
}