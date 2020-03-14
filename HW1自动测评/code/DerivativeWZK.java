import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Derivative {
    private static Poly poly = new Poly();
    private static BigInteger zero = new BigInteger("0");
    private static BigInteger one = new BigInteger("1");

    public static void main(String[] args) {
        //System.out.print(new BigInteger("0"));
        /*String sentence = "abcabcdddabcabcabc";
        String regex = "(abc)*";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(sentence);
        int j = 1;
        while (m.find()) {
            System.out.print(m.group(0)+j);
            j++;
        }*/
        input();
        poly.deriv();
        output();

    }

    public static void output() {
        String ans = "";
        for (BigInteger i:poly.keys()) {
            if (poly.coeff(i).compareTo(zero) > 0) {
                if (i.equals(zero)) {
                    ans += poly.coeff(i);
                } else if (i.equals(one)) {
                    ans += poly.coeff(i) + "*x";
                } else {
                    ans += poly.coeff(i) + "*x**" + i;
                }
                poly.resetCo(i);
                break;
            }
        }

        for (BigInteger i:poly.keys()) {
            if (i.equals(one)) {
                if (poly.coeff(one).compareTo(zero) > 0) {
                    ans += "+" + poly.coeff(one) + "*x";
                } else if (poly.coeff(one).compareTo(zero) < 0) {
                    ans += poly.coeff(one) + "*x";
                }
            }
            else if (i.equals(zero)) {
                if (poly.coeff(zero).compareTo(zero) > 0) {
                    ans += "+" + poly.coeff(zero);
                } else if (poly.coeff(zero).compareTo(zero) < 0) {
                    ans += poly.coeff(zero);
                }
            }
            else {
                if (poly.coeff(i).compareTo(zero) > 0) {
                    ans += "+" + poly.coeff(i) + "*x**" + i;
                } else if (poly.coeff(i).compareTo(zero) < 0) {
                    ans += poly.coeff(i) + "*x**" + i;
                }
            }
        }

        if (ans.isEmpty()) {
            ans += "0";
        }

        System.out.println(ans);
    }

    public static void input() {
        Scanner sn = new Scanner(System.in);
        String sentence = sn.nextLine();
        sentence = sentence.replaceAll("[ \t]","");
        String regex = "[+-]?[+-]?([0-9]+\\*)?x?(\\*\\*[+-]?[0-9]+)?";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(sentence);
        while (m.find()) {
            String item = m.group();
            parse(item);
        }
    }

    public static void parse(String item) {
        BigInteger coeff = zero;
        BigInteger deg = zero;
        if (item.replaceAll("[-+\\*]", "").isEmpty()) {
            return;
        }
        if (!item.contains("x")) {
            poly.addCo(zero, new BigInteger(item));
            return;
        }
        if (item.matches("[+-]?[+-]?(1\\*)?x(\\*\\*[+]?1)?")) {
            if (item.matches("[^-]*-[^-]*")) {
                poly.addCo(one, new BigInteger("-1"));
            }
            else {
                poly.addCo(one, one);
            }
            return;
        }

        String[] split = item.split("x");
        if (item.replaceAll("[+\\*]", "").startsWith("x")) {
            coeff = one;
        } else if (item.replaceAll("[+\\*]","").startsWith("-x")) {
            coeff = new BigInteger("-1");
        } else {
            if (split[0].matches(".*-.*-.*")) {
                split[0] = split[0].replaceAll("-","");
            }
            if (split[0].equals("")) {
                coeff = one;
            } else {
                coeff = new BigInteger(split[0].replaceAll("[+\\*]", ""));
            }
        }
        if (item.endsWith("x")) {
            deg = one;
        } else {
            deg = new BigInteger(split[1].replaceAll("[+\\*]",""));
        }
        poly.addCo(deg,coeff);
    }
    /*
    public static void input() {
        poly = new Poly();
        poly.addCo(new BigInteger("4"), new BigInteger("6"));
        poly.addCo(new BigInteger("2"), new BigInteger("-3"));
        poly.addCo(new BigInteger("1"), new BigInteger("0"));
        poly.addCo(new BigInteger("0"), new BigInteger("45"));
        //poly.addCo(new BigInteger("-1"), new BigInteger("-3"));
        //poly.addCo(new BigInteger("-2"), new BigInteger("-3"));
        //poly.addCo(new BigInteger("-3"), new BigInteger("6"));
    }

     */
}
