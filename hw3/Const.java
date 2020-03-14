import java.math.BigInteger;

public class Const extends Term {
    private BigInteger number;

    Const(BigInteger number) {
        this.number = number;
    }

    public BigInteger value() {
        return this.number;
    }

    public Term derive() {
        return new Const(BigInteger.ZERO);
    }

    public String toString() {
        return number.toString();
    }

    public boolean equals(Term other) {
        if (other instanceof Const) {
            return this.number.equals(((Const) other).number);
        }
        return false;
    }

    public boolean isOne() {
        return number.equals(BigInteger.ONE);
    }

    public boolean isZero() {
        return number.equals(BigInteger.ZERO);
    }
}
