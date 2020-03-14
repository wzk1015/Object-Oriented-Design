import java.math.BigInteger;

public class Tri extends Term {
    private Term content;
    private String type;
    private boolean sin;
    private boolean cos;
    private BigInteger deg;
    private BigInteger zero = BigInteger.ZERO;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);

    Tri(String type, BigInteger deg, Term content) {
        this.content = content;
        this.type = type;
        this.deg = deg;
        if (type.equals("sin")) {
            sin = true;
            cos = false;
        } else if (type.equals("cos")) {
            sin = false;
            cos = true;
        } else {
            System.out.println("Tri Constrution error!");
            System.exit(0);
        }
    }

    public Term content() {
        return this.content;
    }

    public BigInteger deg() {
        return this.deg;
    }

    public String type() {
        return this.type;
    }

    public boolean sin() {
        return sin;
    }

    public boolean cos() {
        return cos;
    }

    public Term derive() {
        if (deg.equals(zero)) {
            return new Const(zero);
        }
        if (sin) {      //(sin(g(x))^m)' = m*sin(g(x))^(m-1)*cos(g(x))*g'(x)
            Term ans = new Multiply(new Const(deg),content.derive());    //m*g'(x)
            ans = new Multiply(ans, new Tri("cos",one,content));    //cos(g(x))
            ans = new Multiply(ans, new Tri("sin",deg.subtract(one),content));  //sin(g(x))^(m-1)
            return ans;
        } else {        //(cos(g(x))^m)' = -m*cos(g(x))^(m-1)*sin(g(x))*g'(x)
            Term ans = new Multiply(new Const(deg.negate()),content.derive());    //-m*g'(x)
            ans = new Multiply(ans, new Tri("sin",one,content));    //sin(g(x))
            ans = new Multiply(ans, new Tri("cos",deg.subtract(one),content));  //cos(g(x))^(m-1)
            return ans;
        }
    }

    public String toString() {
        if (content.toString().equals("0")) {
            return "0";
        } else if (deg.equals(zero)) {
            return "1";
        } if (deg.equals(one)) {
            if (content.toString().contains("+")
                    || content.toString().replaceAll("\\*\\*","^").contains("*")) {
                return type + "((" + content.toString() + "))";
            } else {
                return type + "(" + content.toString() + ")";
            }
        } else {
            if (content instanceof Operation) {
                return type + "((" + content.toString() + "))**" + deg;
            } else {
                return type + "(" + content.toString() + ")**" + deg;
            }
        }
    }

    public boolean equals(Term other) {
        if (other instanceof Tri) {
            return this.type.equals(((Tri) other).type) && this.deg.equals(((Tri) other).deg)
                    && this.content.equals(((Tri) other).content);
        }
        return false;
    }

    public boolean isOne() {
        return deg.equals(zero);
    }

    public boolean isZero() {
        return content.isZero();
    }
}
