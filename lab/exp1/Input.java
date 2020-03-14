import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Input {
    public static void main(final String[] args) {
        Scanner kb = new Scanner(System.in);
        ArrayList<Term> poly = new ArrayList<Term>();
        while (kb.hasNextInt()) {
            int co = kb.nextInt();
            int in = kb.nextInt();
            poly.add(new Term(co,in));
        }

        // TODO
        // code for sort
        Collections.sort(poly);
        // code for output
        for (Term t : poly) {
            int c = t.getCoef();
            int i = t.getIndex();
            if (c == 0) {
                System.out.println(0);
            } else if (i == 0) {
                System.out.println(c);
            } else if (c == 1 && i != 1) {
                System.out.println("x^" + i);
            } else if (c == 1) {
                System.out.println("x");
            } else if (c == -1 && i != 1) {
                System.out.println("-x^" + i);
            } else if (c == -1) {
                System.out.println("-x");
            } else if (i == 1) {
                System.out.println(c + "*x");
            } else {
                System.out.println(t.toString());
            }
        }
    }
}