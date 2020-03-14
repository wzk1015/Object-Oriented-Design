import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Set;

public class Poly {
    private Hashtable<BigInteger, BigInteger> co;

    Poly() {
        this.co = new Hashtable<>();
    }

    public Set<BigInteger> keys() {
        return this.co.keySet();
    }

    public BigInteger coeff(BigInteger deg) {
        return co.get(deg);
    }

    public void addCo(BigInteger deg, BigInteger coeff) {
        if (co.containsKey(deg)) {
            co.put(deg, co.get(deg).add(coeff));
        }
        else {
            co.put(deg,coeff);
        }
    }

    public void resetCo(BigInteger deg) {
        co.put(deg, new BigInteger("0"));
    }

    public void deriv() {
        Hashtable<BigInteger,BigInteger> newCo = new Hashtable<>();
        for (BigInteger i : co.keySet()) {
            if (i.equals(new BigInteger("0"))) {
                continue;
            }
            BigInteger temp = co.get(i).multiply(i);
            newCo.put(i.add(new BigInteger("-1")),temp);
        }
        if (newCo.isEmpty()) {
            newCo.put(new BigInteger("0"),new BigInteger("0"));
        }
        this.co = newCo;
    }

}
