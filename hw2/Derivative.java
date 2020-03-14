import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Derivative {
    private static Poly poly;
    private static BigInteger one = BigInteger.ONE;
    private static BigInteger zero = BigInteger.ZERO;
    private static BigInteger minusOne = BigInteger.ONE.negate();

    public static void main(String[] args) {
        //CHANGE HERE WHEN SUBMIT
        /*while (true) {
            //testInput();
            //testFormat();
            poly = new Poly();
            input();
            poly.deriv();
            poly.optimize();
            System.out.println(poly.toString());
        }*/
        poly = new Poly();
        input();
        poly.deriv();
        poly.optimize();
        System.out.println(poly.toString());

    }

    public static void testFormat() {
        String blank = "([ \t]*)";
        String number = "([+-]?[0-9]+)";
        String deg = "(\\*\\*" + blank + number + ")";
        String sin = "(sin" + blank + "\\(" + blank + "x" + blank + "\\)"
                + "(" + blank + deg + ")?)";
        String cos = "(cos" + blank + "\\(" + blank + "x" + blank + "\\)"
                + "(" + blank + deg + ")?)";
        String pow = "(x" + "(" + blank + deg + ")?)";
        String variable = "(" + pow + "|" + sin + "|" + cos + ")";
        String factor = "(" + variable + "|" + number + ")";
        String item = "(([+-]" + blank + ")?" + factor
                + "(" + blank + "\\*" + blank + factor + ")*)";
        String expression = blank + "(([+-]" + blank + ")?" + item + blank
                + "([+-]" + blank + item + blank + ")*)";
        System.out.println(expression);
        while (true) {
            Scanner sn = new Scanner(System.in);
            String sentence = sn.nextLine();
            sentence = sentence.replaceAll("[ \t]","");
            if (sentence.matches(expression)) {
                System.out.println("OK!");
            } else {
                System.out.println("WRONG FORMAT!");
            }
        }
    }

    public static BigInteger B(int n) {
        return BigInteger.valueOf(n);
    }

    public static void testInput() {
        poly.addCo(B(2), zero, zero, B(2));
        poly.addCo(zero, B(3), zero, B(6));
    }

    public static void input() {
        String blank = "([ \t]*)";
        String number = "([+-]?[0-9]+)";
        String deg = "(\\*\\*" + blank + number + ")";
        String sin = "(sin" + blank + "\\(" + blank + "x" + blank + "\\)"
                + "(" + blank + deg + ")?)";
        String cos = "(cos" + blank + "\\(" + blank + "x" + blank + "\\)"
                + "(" + blank + deg + ")?)";
        String pow = "(x" + "(" + blank + deg + ")?)";
        String variable = "(" + pow + "|" + sin + "|" + cos + ")";
        String factor = "(" + variable + "|" + number + ")";
        String item = "(([+-]" + blank + ")?" + factor
                + "(" + blank + "\\*" + blank + factor + ")*)";
        String expression = blank + "(([+-]" + blank + ")?" + item + blank
                + "([+-]" + blank + item + blank + ")*)";
        //System.out.println(item);
        //System.out.println(expression);
        Scanner sn = new Scanner(System.in);
        String sentence = sn.nextLine();
        if (!sentence.matches(expression)) {
            poly.setWrongFormat();
            return;
        }
        sentence = sentence.replaceAll("[ \t]","");
        //negative mark at beginning
        boolean firstMark = sentence.startsWith("-");
        if (firstMark) {
            sentence = sentence.substring(1);
        }
        Pattern r = Pattern.compile("[+-]?" + item);
        Matcher m = r.matcher(sentence);
        while (m.find()) {
            //System.out.println(m.group());
            String word = m.group().replaceAll("\\+","");
            if (word.startsWith("-")) {
                poly.parse(word.replaceFirst("-",""), !firstMark);
            } else {
                poly.parse(word, firstMark);
            }
            firstMark = false;
        }
    }

}
