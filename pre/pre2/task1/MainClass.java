import java.util.Scanner;

public class MainClass {
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public static void main(String[] args) {
        double length;
        double width;
        double height;
        Scanner sn = new Scanner(System.in);
        length = sn.nextInt();
        width = sn.nextInt();
        height = sn.nextInt();
        ScaleCuboidBox mybox = new ScaleCuboidBox(length, width, height);

        int n;
        n = sn.nextInt();
        for (int i = 0; i < n; i++) {
            int op;
            op = sn.nextInt();
            switch (op) {
                case 1:
                    System.out.println(mybox.getLength());
                    break;
                case 2:
                    System.out.println(mybox.getWidth());
                    break;
                case 3:
                    System.out.println(mybox.getHeight());
                    break;
                case 4:
                    mybox.setLength(sn.nextInt());
                    break;
                case 5:
                    mybox.setWidth(sn.nextInt());
                    break;
                case 6:
                    mybox.setHeight(sn.nextInt());
                    break;
                case 7:
                    System.out.println(mybox.getVolume());
                    break;
                default:
            }
        }

    }

}