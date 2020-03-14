import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Poly {
    private HashMap<Type, BigInteger> co;
    private boolean wrongFormat;
    private BigInteger zero = BigInteger.ZERO;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);

    Poly() {
        this.co = new HashMap<>();
        this.wrongFormat = false;
    }

    public void setWrongFormat() {
        this.wrongFormat = true;
    }

    public void printCo(BigInteger s, BigInteger c, BigInteger p) {
        Type deg = new Type(s,c,p);
        for (Type i: copy(co.keySet())) {
            if (i.equals(deg)) {
                System.out.print("Coeff of sin(x)**" + s + "*cos(x)**" + c + "*x**" + p + " is: ");
                System.out.println(co.get(i));
                return;
            }
        }
        System.out.println("Wrong Key!");
    }

    public void addCo(BigInteger s, BigInteger c, BigInteger p, BigInteger coeff) {
        Type deg = new Type(s,c,p);
        boolean containsKey = false;
        for (Type i: co.keySet()) {
            if (i.equals(deg)) {
                containsKey = true;
                deg = i;
            }
        }
        if (containsKey) {
            co.put(deg, co.get(deg).add(coeff));
        } else {
            co.put(deg,coeff);
        }
    }

    public Set<Type> copy(Set<Type> a) {
        Set<Type> newSet = new HashSet<>();
        for (Type i:a) {
            Type newType = new Type(i.sin(),i.cos(),i.pow());
            newSet.add(newType);
        }
        return newSet;
    }

    public HashMap<Type,BigInteger> copy(HashMap<Type,BigInteger> a) {
        HashMap<Type,BigInteger> newMap = new HashMap<>();
        for (Type i:a.keySet()) {
            Type newType = new Type(i.sin(),i.cos(),i.pow());
            BigInteger newValue = new BigInteger(a.get(i).toString());
            newMap.put(newType,newValue);
        }
        return newMap;
    }

    public void deriv() {
        if (wrongFormat) {
            return;
        }
        Poly newPoly = new Poly();

        for (Type i : co.keySet()) {
            if (i.isConstant()) {
                continue;
            }
            BigInteger a = i.pow();
            BigInteger b = i.sin();
            BigInteger c = i.cos();
            BigInteger k = co.get(i);
            if (b.equals(zero) && c.equals(zero)) {
                newPoly.addCo(zero,zero,a.subtract(one), a.multiply(k));
            }
            else if (b.equals(zero)) {
                newPoly.addCo(one,c.subtract(one),a, c.multiply(k).negate());
                newPoly.addCo(zero,c,a.subtract(one), a.multiply(k));
            }
            else if (c.equals(zero)) {
                newPoly.addCo(b.subtract(one), one, a, b.multiply(k));
                newPoly.addCo(b,zero, a.subtract(one), a.multiply(k));
            }
            else {
                newPoly.addCo(b, c, a.subtract(one), a.multiply(k));
                newPoly.addCo(b.add(one), c.subtract(one), a, c.multiply(k).negate());
                newPoly.addCo(b.subtract(one), c.add(one), a, b.multiply(k));
            }
        }

        if (newPoly.co.isEmpty()) {
            newPoly.addCo(zero,zero,zero,zero);
        }
        this.co = newPoly.co;
    }

    public String toString() {
        String ans = "";
        HashMap<Type,BigInteger> cocopy = copy(co);
        if (wrongFormat) {
            ans = "WRONG FORMAT!";
        }
        else {
            for (Type i:cocopy.keySet()) {
                if (cocopy.get(i).compareTo(zero) > 0) {
                    ans = term(i,cocopy);
                    cocopy.put(i,zero);
                    break;
                }
            }
            for (Type i:cocopy.keySet()) {
                if (term(i,cocopy).isEmpty()) {
                    ans += "";
                } else if (term(i,cocopy).startsWith("-")) {
                    ans += term(i,cocopy);
                } else {
                    ans += "+" + term(i,cocopy);
                }
            }
            if (ans.isEmpty()) {
                ans = "0";
            }
        }
        if (ans.endsWith("+")) {
            ans = ans.substring(0,ans.length() - 1);
        }
        return ans;
    }

    public String term(Type i, HashMap<Type,BigInteger> cocopy) {

        //"+" sign added by poly.toString()
        String ans = "";
        //coeff=0
        if (cocopy.get(i).equals(zero)) {
            return "";
        }
        //constant
        if (i.isConstant()) {
            return cocopy.get(i).toString();
        }
        //coeff
        if (cocopy.get(i).equals(one)) {
            ans = "";
        } else if (cocopy.get(i).equals(one.negate())) {
            ans = "-";
        } else {
            ans = cocopy.get(i).toString() + "*";
        }
        //pow
        if (i.pow().equals(zero)) {
            ans += "";
        } else if (i.pow().equals(one)) {
            ans += "x*";
        } else if (i.pow().equals(BigInteger.valueOf(2))) {
            ans += "x*x*";
        } else {
            ans += "x**" + i.pow() + "*";
        }
        //sin
        if (i.sin().equals(zero)) {
            ans += "";
        } else if (i.sin().equals(one)) {
            ans += "sin(x)*";
        } else {
            ans += "sin(x)**" + i.sin() + "*";
        }
        //cos
        if (i.cos().equals(zero)) {
            ans += "";
        } else if (i.cos().equals(one)) {
            ans += "cos(x)";
        } else {
            ans += "cos(x)**" + i.cos();
        }
        if (ans.endsWith("*")) {
            ans = ans.substring(0,ans.length() - 1);
        }
        return ans;
    }

    public void parse(String item, boolean negateBeforeItem) {
        String number = "([+-]?[0-9]+)";
        String deg = "(\\*\\*" + number + ")";
        String sin = "(sin\\(x\\)(" + deg + ")?)";
        String cos = "(cos\\(x\\)(" + deg + ")?)";
        String pow = "(x(" + deg + ")?)";
        String variable = "(" + pow + "|" + sin + "|" + cos + ")";
        String factor = "(" + variable + "|" + number + ")";

        boolean startsWithNegate = false;
        String itemPro = item;
        if (item.startsWith("-")) {
            itemPro = item.replaceFirst("-","");
            startsWithNegate = true;
        }

        Pattern pf = Pattern.compile(factor);
        Matcher mf = pf.matcher(itemPro);

        BigInteger sinDeg = zero;
        BigInteger cosDeg = zero;
        BigInteger powDeg = zero;
        BigInteger coeff = one;

        while (mf.find()) {
            String fac = mf.group(0);
            if (fac.matches(number)) {
                coeff = coeff.multiply(new BigInteger(fac));
            }
            else if (fac.matches(pow)) {
                coeff = coeff.multiply(parseCoeff(fac,"x"));
                powDeg = powDeg.add(parseDegree(fac,"x"));
            }
            else if (fac.matches(sin)) {
                coeff = coeff.multiply(parseCoeff(fac,"sin(x)"));
                sinDeg = sinDeg.add(parseDegree(fac,"sin(x)"));
            }
            else if (fac.matches(cos)) {
                coeff = coeff.multiply(parseCoeff(fac,"cos(x)"));
                cosDeg = cosDeg.add(parseDegree(fac,"cos(x)"));
            }
        }

        if (startsWithNegate) {
            coeff = coeff.negate();
        } if (negateBeforeItem) {
            coeff = coeff.negate();
        }
        addCo(sinDeg,cosDeg,powDeg,coeff);
    }

    public BigInteger parseCoeff(String fac, String type) {
        BigInteger ans;
        if (fac.startsWith(type)) {
            ans = one;
        } else {
            ans = new BigInteger(fac.split("\\*" + type)[0]);
        }
        return ans;
    }

    public BigInteger parseDegree(String fac, String type) {
        BigInteger ans;
        if (fac.endsWith(type)) {
            ans = one;
        } else {
            String reg = type.replace("(","\\(")
                    .replace(")","\\)") + "\\*\\*";
            String[] sl = fac.split(reg);
            ans = new BigInteger(sl[sl.length - 1]);
        }
        if (type.equals("x") && ans.abs().compareTo(BigInteger.valueOf(10000)) > 0) {
            this.setWrongFormat();
        }
        return ans;
    }

    public void optimize() {
        Poly newPoly = new Poly();
        for (Type i : co.keySet()) {
            newPoly.addCo(i.sin(),i.cos(),i.pow(),co.get(i));
        }
        for (Type i : co.keySet()) {
            for (Type j : co.keySet()) {
                if (i.pow().equals(j.pow()) && i.sin().equals(j.sin()) &&
                        i.cos().add(two).equals(j.cos())) {
                    newPoly.addCo(i.sin().add(two), i.cos(), i.pow(), co.get(j).negate());
                    newPoly.addCo(i.sin(), i.cos(), i.pow(), co.get(j));
                    newPoly.addCo(j.sin(), j.cos(), j.pow(), co.get(j).negate());
                }
            }
        }
        if (newPoly.toString().length() < this.toString().length()) {
            co = newPoly.co;
        }

        newPoly = new Poly();
        for (Type i : co.keySet()) {
            newPoly.addCo(i.sin(),i.cos(),i.pow(),co.get(i));
        }
        for (Type i : co.keySet()) {
            for (Type j : co.keySet()) {
                if (i.pow().equals(j.pow()) && i.cos().equals(j.cos()) &&
                        i.sin().add(two).equals(j.sin())) {
                    newPoly.addCo(i.sin(), i.cos().add(two), i.pow(), co.get(j).negate());
                    newPoly.addCo(i.sin(), i.cos(), i.pow(), co.get(j));
                    newPoly.addCo(j.sin(), j.cos(), j.pow(), co.get(j).negate());
                }
            }
        }
        if (newPoly.toString().length() < this.toString().length()) {
            co = newPoly.co;
        }

        newPoly = new Poly();
        for (Type i : co.keySet()) {
            newPoly.addCo(i.sin(),i.cos(),i.pow(),co.get(i));
        }
        for (Type i : co.keySet()) {
            for (Type j : co.keySet()) {
                if (i.pow().equals(j.pow()) && i.sin().add(two).equals(j.sin())
                        && j.cos().add(two).equals(i.cos())) {
                    BigInteger coeff = co.get(i).min(co.get(j));
                    newPoly.addCo(i.sin(), j.cos(), i.pow(), coeff);
                    newPoly.addCo(i.sin(), i.cos(), i.pow(), coeff.negate());
                    newPoly.addCo(j.sin(), j.cos(), j.pow(), coeff.negate());
                }
            }
        }
        if (newPoly.toString().length() < this.toString().length()) {
            co = newPoly.co;
        }

    }
}
