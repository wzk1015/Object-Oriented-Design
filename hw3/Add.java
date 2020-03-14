import java.math.BigInteger;

public class Add extends Operation {
    private Term t1;
    private Term t2;
    private BigInteger zero = BigInteger.ZERO;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);
    private Const zeroConst;
    private Const oneConst;
    private boolean containsPlus;

    Add(Term t1, Term t2) {
        this.t1 = t1;
        this.t2 = t2;
        this.zeroConst = new Const(zero);
        this.oneConst = new Const(one);
        this.containsPlus = containsPlus();//////////////////
    }

    public Term t1() {
        return t1;
    }

    public Term t2() {
        return t2;
    }

    public Term derive() {
        return new Add(t1.derive(),t2.derive());
    }

    public String toString() {
        /*
        String tt = triTri(t1,t2);
        String pp = powPow(t1,t2);
        String cc = constConst(t1,t2);
        String mm = mulMul(t1,t2);
         */

        if (t1.isZero()) {
            return t2.toString();
        } else if (t2.isZero()) {
            return t1.toString();
        } else if (t1.equals(t2)) {
            return new Const(two).mult(t1).toString();
        }

        return t1.toString() + "+" + t2.toString();

    }

    public boolean containsPlus() {
        if (t1.isZero() || t2.isZero() || t1.equals(t2)) {
            if (t1 instanceof Add) {
                if (((Add) t1).containsPlus()) {
                    return true;
                }
            }
            if (t2 instanceof Add) {
                if (((Add) t2).containsPlus()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public String mulMul(Term t1, Term t2) {
        if (t1 instanceof Multiply && t2 instanceof Multiply) {
            if (t1.equals(t2)) {
                return t1.mult(new Const(two)).toString();
            }
            else if (((Multiply) t1).t1() instanceof Multiply && ((Multiply) t1).t1().equals(t2)) {
                return t2.mult(new Const(two)).add(((Multiply) t1).t2()).toString();
            }
            else if (((Multiply) t1).t2() instanceof Multiply && ((Multiply) t1).t2().equals(t2)) {
                return t2.mult(new Const(two)).add(((Multiply) t1).t1()).toString();
            }
            else if (((Multiply) t2).t1() instanceof Multiply && ((Multiply) t2).t1().equals(t1)) {
                return t1.mult(new Const(two)).add(((Multiply) t2).t2()).toString();
            }
            else if (((Multiply) t2).t2() instanceof Multiply && ((Multiply) t2).t2().equals(t1)) {
                return t1.mult(new Const(two)).add(((Multiply) t2).t1()).toString();
            }
            else if (((Multiply) t1).t1() instanceof Multiply && ((Multiply) t2).t1() instanceof
                    Multiply && ((Multiply) t1).t1().equals(((Multiply) t2).t1())) {
                return ((Multiply) t1).t1().mult(new Const(two)).add(((Multiply) t1).t2()).add(
                        ((Multiply) t2).t2()).toString();
            }
            else if (((Multiply) t1).t1() instanceof Multiply && ((Multiply) t2).t2() instanceof
                    Multiply && ((Multiply) t1).t1().equals(((Multiply) t2).t2())) {
                return ((Multiply) t1).t1().mult(new Const(two)).add(((Multiply) t1).t2()).add(
                        ((Multiply) t2).t1()).toString();
            }
            else if (((Multiply) t1).t2() instanceof Multiply && ((Multiply) t2).t1() instanceof
                    Multiply && ((Multiply) t1).t2().equals(((Multiply) t2).t1())) {
                return ((Multiply) t1).t2().mult(new Const(two)).add(((Multiply) t1).t1()).add(
                        ((Multiply) t2).t2()).toString();
            }
            else if (((Multiply) t1).t2() instanceof Multiply && ((Multiply) t2).t2() instanceof
                    Multiply && ((Multiply) t1).t2().equals(((Multiply) t2).t2())) {
                return ((Multiply) t1).t2().mult(new Const(two)).add(((Multiply) t1).t1()).add(
                        ((Multiply) t2).t1()).toString();
            }
        }
        return "";
    }

    public String triTri(Term t1, Term t2) {
        if (judgeTri(t1,t2)) {
            return mergeTri(t1,t2).toString();
        } else if (t1 instanceof Tri && t2 instanceof Add) {
            if (judgeTri(t1,((Add) t2).t1)) {     //sin*(sin*fx)
                return mergeTri(t1,((Add) t2).t1).add(((Add) t2).t2).toString();
            } else if (judgeTri(t1,((Add) t2).t2)) {     //sin*(fx*sin)
                return mergeTri(t1,((Add) t2).t2).add(((Add) t2).t1).toString();
            }
        } else if (t2 instanceof Tri && t1 instanceof Add) {
            if (judgeTri(t2,((Add) t1).t1)) {     //sin*(sin*fx)
                return mergeTri(t2,((Add) t1).t1).add(((Add) t1).t2).toString();
            } else if (judgeTri(t2,((Add) t1).t2)) {     //sin*(fx*sin)
                return mergeTri(t2,((Add) t1).t2).add(((Add) t1).t1).toString();
            }
        }
        else if (t2 instanceof Add && t1 instanceof Add) {
            if (judgeTri(((Add) t1).t1,((Add) t2).t1)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Add) t1).t1,((Add) t2).t1).add(
                        ((Add) t1).t2).add(((Add) t2).t2).toString();
            } else if (judgeTri(((Add) t1).t1,((Add) t2).t2)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Add) t1).t1,((Add) t2).t2).add(
                        ((Add) t1).t2).add(((Add) t2).t1).toString();
            } else if (judgeTri(((Add) t1).t2,((Add) t2).t1)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Add) t1).t2,((Add) t2).t1).add(
                        ((Add) t1).t1).add(((Add) t2).t2).toString();
            } else if (judgeTri(((Add) t1).t2,((Add) t2).t2)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Add) t1).t2,((Add) t2).t2).add(
                        ((Add) t1).t1).add(((Add) t2).t1).toString();
            }
        }
        return "";
    }

    public boolean judgeTri(Term i, Term j) {
        if (i instanceof Tri && j instanceof Tri) {
            return i.equals(j);
        }
        return false;
    }

    public Term mergeTri(Term i, Term j) {
        if (!(i instanceof Tri) || !(j instanceof Tri)) {
            System.out.println("MergeTri Error!");
            System.exit(0);
        }
        return i.mult(new Const(two));
    }

    public String powPow(Term t1, Term t2) {
        if (judgePow(t1,t2)) {
            return t1.mult(new Const(two)).toString();
        } else if (t1 instanceof Pow && t2 instanceof Add) {
            if (judgePow(t1,((Add) t2).t1)) {     //x*(x*fx)
                return t1.mult(new Const(two)).add(((Add) t2).t2).toString();
            } else if (((Add) t2).t2 instanceof  Pow) {     //x*(fx*x)
                return t1.mult(new Const(two)).add(((Add) t2).t1).toString();
            }
        } else if (t2 instanceof Pow && t1 instanceof Add) {
            if (judgePow(t2,((Add) t1).t1)) {     //(x*fx)*x
                return t2.mult(new Const(two)).add(((Add) t1).t2).toString();
            } else if (((Add) t1).t2 instanceof  Pow) {     //(fx*x)*x
                return t2.mult(new Const(two)).add(((Add) t1).t1).toString();
            }
        } else if (t2 instanceof Add && t1 instanceof Add) {
            if (judgePow(((Add) t1).t1,((Add) t2).t1)) {
                //(x*fx)*(x*gx)
                return ((Add) t1).t1.mult(new Const(two)).add(
                        ((Add) t1).t2).add(((Add) t2).t2).toString();
            } else if (judgePow(((Add) t1).t1,((Add) t2).t2)) {
                //(x*fx)*(x*gx)
                return ((Add) t1).t1.mult(new Const(two)).add(
                        ((Add) t1).t2).add(((Add) t2).t1).toString();
            } else if (judgePow(((Add) t1).t2,((Add) t2).t1)) {
                //(x*fx)*(x*gx)
                return ((Add) t1).t2.mult(new Const(two)).add(
                        ((Add) t1).t1).add(((Add) t2).t2).toString();
            } else if (judgePow(((Add) t1).t2,((Add) t2).t2)) {
                //(x*fx)*(x*gx)
                return ((Add) t1).t2.mult(new Const(two)).add(
                        ((Add) t1).t1).add(((Add) t2).t1).toString();
            }
        }
        return "";
    }

    public boolean judgePow(Term i, Term j) {
        if (i instanceof Pow && j instanceof Pow) {
            return i.equals(j);
        }
        return false;
    }

    public String constConst(Term t1, Term t2) {
        if (t1 instanceof Const && t2 instanceof Const) {
            return ((Const) t1).value().add(((Const) t2).value()).toString();
        } else if (t1 instanceof Const && t2 instanceof Add) {
            if (((Add) t2).t1 instanceof  Const) {     //2*(2*fx)
                return new Const(((Const) t1).value().add(
                        ((Const) ((Add) t2).t1).value()))
                        .add(((Add) t2).t2).toString();
            } else if (((Add) t2).t2 instanceof  Const) {     //2*(fx*2)
                return new Const(((Const) t1).value().add(
                        ((Const) ((Add) t2).t2).value()))
                        .add(((Add) t2).t1).toString();
            }
        } else if (t2 instanceof Const && t1 instanceof Add) {
            if (((Add) t1).t1 instanceof  Const) {         //(2*fx)*2
                return new Const(((Const) t2).value().add(
                        ((Const) ((Add) t1).t1).value()))
                        .add(((Add) t1).t2).toString();
            } else if (((Add) t1).t2 instanceof  Const) {     //(fx*2)*2
                return new Const(((Const) t2).value().add(
                        ((Const) ((Add) t1).t2).value()))
                        .add(((Add) t1).t1).toString();
            }
        } else if (t2 instanceof Add && t1 instanceof Add) {
            if (((Add) t1).t1 instanceof Const && ((Add) t2).t1 instanceof Const) {
                //(2*fx)*(2*gx)
                return new Const(((Const) ((Add) t1).t1).value().add(
                        ((Const) ((Add) t2).t1).value())).add(((Add) t1).t2).add(
                        ((Add) t2).t2).toString();
            }
            else if (((Add) t1).t1 instanceof Const && ((Add) t2).t2 instanceof Const) {
                //(2*fx)*(gx*2)
                return new Const(((Const) ((Add) t1).t1).value().add(
                        ((Const) ((Add) t2).t2).value())).add(((Add) t1).t2).add(
                        ((Add) t2).t1).toString();
            }
            else if (((Add) t1).t2 instanceof Const && ((Add) t2).t1 instanceof Const) {
                //(fx*2)*(2*gx)
                return new Const(((Const) ((Add) t1).t2).value().add(
                        ((Const) ((Add) t2).t1).value())).add(((Add) t1).t1).add(
                        ((Add) t2).t2).toString();
            }
            else if (((Add) t1).t2 instanceof Const && ((Add) t2).t2 instanceof Const) {
                //(fx*2)*(2*gx)
                return new Const(((Const) ((Add) t1).t2).value().add(
                        ((Const) ((Add) t2).t2).value())).add(((Add) t1).t1).add(
                        ((Add) t2).t1).toString();
            }
        }
        return "";
    }

    public boolean equals(Term other) {
        if (other instanceof Add) {
            return this.t1.equals(((Add) other).t1)
                    && this.t2.equals(((Add) other).t2);
        }
        return false;
    }

    public boolean isOne() {
        return (t1.isOne() && t2.isZero()) ||
                (t1.isZero() && t2.isOne());
    }

    public boolean isZero() {
        return t1.isZero() && t2.isZero();
    }
}
