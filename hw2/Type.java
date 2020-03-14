import java.math.BigInteger;

public class Type {
    private BigInteger sinDeg;
    private BigInteger cosDeg;
    private BigInteger powDeg;

    Type(BigInteger s, BigInteger c, BigInteger p) {
        this.sinDeg = s;
        this.cosDeg = c;
        this.powDeg = p;
    }

    public BigInteger sin() {
        return this.sinDeg;
    }

    public BigInteger cos() {
        return this.cosDeg;
    }

    public BigInteger pow() {
        return this.powDeg;
    }

    public boolean isConstant() {
        return sinDeg.equals(BigInteger.ZERO) && cosDeg.equals(BigInteger.ZERO) &&
                powDeg.equals(BigInteger.ZERO);
    }

    public boolean equals(Type other) {
        return sinDeg.equals(other.sinDeg) && cosDeg.equals(other.cosDeg)
                && powDeg.equals(other.powDeg);
    }
}
