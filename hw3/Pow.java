import java.math.BigInteger;

public class Pow extends Term {
    private BigInteger deg;
    private BigInteger zero = BigInteger.ZERO;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);

    Pow(BigInteger deg) {
        this.deg = deg;
    }

    public BigInteger deg() {
        return this.deg;
    }

    public boolean isConst() {
        return deg.equals(zero);
    }

    public Term derive() {
        if (deg.equals(one)) {
            return new Const(one);
        } else if (deg.equals(zero)) {
            return new Const(zero);
        }
        return new Multiply(new Const(deg), new Pow(deg.subtract(one)));
    }

    public String toString() {
        if (deg.equals(one)) {
            return "x";
        } else if (deg.equals(zero)) {
            return "1";
        } else {
            return "x**" + deg.toString();
        }
    }

    public boolean equals(Term other) {
        if (other instanceof Pow) {
            return this.deg.equals(((Pow) other).deg);
        }
        return false;
    }

    public boolean isOne() {
        return deg.equals(zero);
    }

    public boolean isZero() {
        return false;
    }
}
