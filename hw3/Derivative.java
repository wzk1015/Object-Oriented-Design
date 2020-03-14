import java.math.BigInteger;
import java.util.Scanner;

public class Derivative {
    private static BigInteger zero = BigInteger.ZERO;
    private static BigInteger one = BigInteger.ONE;
    private static BigInteger two = BigInteger.valueOf(2);

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        //testFormat()
        Input input = new Input(false,false);

        String sentence = sn.nextLine();
        Term poly = input.read(sentence);
        if (poly == null) {
            System.out.println("WRONG FORMAT!");
            //continue;
            System.exit(0);
        }
        poly = poly.derive();
        System.out.println(poly.toString());
    }

    public static Const c(BigInteger number) {
        return new Const(number);
    }

    public static Pow p(BigInteger deg) {
        return new Pow(deg);
    }

    public static Tri sin(BigInteger deg, Term content) {
        return new Tri("sin",deg,content);
    }

    public static Tri cos(BigInteger deg, Term content) {
        return new Tri("cos",deg,content);
    }

}
