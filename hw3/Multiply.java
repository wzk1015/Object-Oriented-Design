import java.math.BigInteger;

public class Multiply extends Operation {
    private Term t1;
    private Term t2;
    private BigInteger zero = BigInteger.ZERO;
    private BigInteger one = BigInteger.ONE;
    private BigInteger two = BigInteger.valueOf(2);
    private Const zeroConst;
    private Const oneConst;

    Multiply(Term t1, Term t2) {
        this.t1 = t1;
        this.t2 = t2;
        this.zeroConst = new Const(zero);
        this.oneConst = new Const(one)
;    }

    public Term t1() {
        return t1;
    }

    public Term t2() {
        return t2;
    }

    public Term derive() {
        return new Add(new Multiply(t1.derive(),t2),new Multiply(t1,t2.derive()));
    }

    public String toString() {
        if (t1.isZero() || t2.isZero()) {
            return "0";
        } else if (t1.isOne()) {
            if (t2 instanceof Add) {
                if (((Add) t2).containsPlus()) {
                    return "(" + t2.toString() + ")";
                }
            }
            return t2.toString();
        } else if (t2.isOne()) {
            if (t1 instanceof Add) {
                if (((Add) t1).containsPlus()) {
                    return "(" + t1.toString() + ")";
                }
            }
            return t1.toString();
        }
        if (t1 instanceof Add && t2 instanceof Add) {
            if (((Add) t1).containsPlus() && ((Add) t2).containsPlus()) {
                return "(" + t1.toString() + ")*(" + t2.toString() + ")";
            }
        } if (t1 instanceof Add) {
            if (((Add) t1).containsPlus()) {
                return "(" + t1.toString() + ")*" + t2.toString();
            }
        } if (t2 instanceof Add) {
            if (((Add) t2).containsPlus()) {
                return t1.toString() + "*(" + t2.toString() + ")";
            }
        }
        return t1.toString() + "*" + t2.toString();
    }

    public String powPow(Term t1, Term t2) {
        if (t1 instanceof Pow && t2 instanceof Pow) {
            return new Pow(((Pow) t1).deg().add(((Pow) t2).deg())).toString();
        } else if (t1 instanceof Pow && t2 instanceof Multiply) {
            if (((Multiply) t2).t1 instanceof  Pow) {     //x*(x*fx)
                return new Pow(((Pow) t1).deg().add(((Pow) ((Multiply) t2).t1).deg())).mult(
                        ((Multiply) t2).t2).toString();
            } else if (((Multiply) t2).t2 instanceof  Pow) {     //x*(fx*x)
                return new Pow(((Pow) t1).deg().add(((Pow) ((Multiply) t2).t2).deg())).mult(
                        ((Multiply) t2).t1).toString();
            }
        } else if (t2 instanceof Pow && t1 instanceof Multiply) {
            if (((Multiply) t1).t1 instanceof  Pow) {     //(x*fx)*x
                return new Pow(((Pow) t2).deg().add(((Pow) ((Multiply) t1).t1).deg())).mult(
                        ((Multiply) t1).t2).toString();
            } else if (((Multiply) t1).t2 instanceof  Pow) {     //(fx*x)*x
                return new Pow(((Pow) t2).deg().add(((Pow) ((Multiply) t1).t2).deg())).mult(
                        ((Multiply) t1).t1).toString();
            }
        } else if (t2 instanceof Multiply && t1 instanceof Multiply) {
            if (((Multiply) t1).t1 instanceof Pow && ((Multiply) t2).t1 instanceof Pow) {
                //(x*fx)*(x*gx)
                return new Pow(((Pow) ((Multiply) t1).t1).deg().add(
                        ((Pow) ((Multiply) t2).t1).deg())).mult(((Multiply) t1).t2).mult(
                                ((Multiply) t2).t2).toString();
            } else if (((Multiply) t1).t1 instanceof Pow && ((Multiply) t2).t2 instanceof Pow) {
                //(x*fx)*(x*gx)
                return new Pow(((Pow) ((Multiply) t1).t1).deg().add(
                        ((Pow) ((Multiply) t2).t2).deg())).mult(((Multiply) t1).t2).mult(
                        ((Multiply) t2).t1).toString();
            } else if (((Multiply) t1).t2 instanceof Pow && ((Multiply) t2).t1 instanceof Pow) {
                //(x*fx)*(x*gx)
                return new Pow(((Pow) ((Multiply) t1).t2).deg().add(
                        ((Pow) ((Multiply) t2).t1).deg())).mult(((Multiply) t1).t1).mult(
                        ((Multiply) t2).t2).toString();
            } else if (((Multiply) t1).t2 instanceof Pow && ((Multiply) t2).t2 instanceof Pow) {
                //(x*fx)*(x*gx)
                return new Pow(((Pow) ((Multiply) t1).t2).deg().add(
                        ((Pow) ((Multiply) t2).t2).deg())).mult(((Multiply) t1).t1).mult(
                        ((Multiply) t2).t1).toString();
            }
        }
        return "";
    }

    public String triTri(Term t1, Term t2) {
        if (judgeTri(t1,t2)) {
            return mergeTri(t1,t2).toString();
        } else if (t1 instanceof Tri && t2 instanceof Multiply) {
            if (judgeTri(t1,((Multiply) t2).t1)) {     //sin*(sin*fx)
                return mergeTri(t1,((Multiply) t2).t1).mult(((Multiply) t2).t2).toString();
            } else if (judgeTri(t1,((Multiply) t2).t2)) {     //sin*(fx*sin)
                return mergeTri(t1,((Multiply) t2).t2).mult(((Multiply) t2).t1).toString();
            }
        } else if (t2 instanceof Tri && t1 instanceof Multiply) {
            if (judgeTri(t2,((Multiply) t1).t1)) {     //sin*(sin*fx)
                return mergeTri(t2,((Multiply) t1).t1).mult(((Multiply) t1).t2).toString();
            } else if (judgeTri(t2,((Multiply) t1).t2)) {     //sin*(fx*sin)
                return mergeTri(t2,((Multiply) t1).t2).mult(((Multiply) t1).t1).toString();
            }
        }
        else if (t2 instanceof Multiply && t1 instanceof Multiply) {
            if (judgeTri(((Multiply) t1).t1,((Multiply) t2).t1)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Multiply) t1).t1,((Multiply) t2).t1).mult(
                        ((Multiply) t1).t2).mult(((Multiply) t2).t2).toString();
            } else if (judgeTri(((Multiply) t1).t1,((Multiply) t2).t2)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Multiply) t1).t1,((Multiply) t2).t2).mult(
                        ((Multiply) t1).t2).mult(((Multiply) t2).t1).toString();
            } else if (judgeTri(((Multiply) t1).t2,((Multiply) t2).t1)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Multiply) t1).t2,((Multiply) t2).t1).mult(
                        ((Multiply) t1).t1).mult(((Multiply) t2).t2).toString();
            } else if (judgeTri(((Multiply) t1).t2,((Multiply) t2).t2)) {
                //(sinx*fx)*(sinx*gx)
                return mergeTri(((Multiply) t1).t2,((Multiply) t2).t2).mult(
                        ((Multiply) t1).t1).mult(((Multiply) t2).t1).toString();
            }
        }
        return "";
    }

    public boolean judgeTri(Term i, Term j) {
        if (i instanceof Tri && j instanceof Tri) {
            return ((Tri) i).type().equals(((Tri) j).type()) &&
                    ((Tri) i).content().equals(((Tri) j).content());
        }
        return false;
    }

    public Tri mergeTri(Term i, Term j) {
        if (!(i instanceof Tri) || !(j instanceof Tri)) {
            System.out.println("MergeTri Error!");
            System.exit(0);
        }
        Tri a = (Tri) i;
        Tri b = (Tri) j;
        return new Tri(a.type(),a.deg().add(b.deg()),a.content());
    }

    public String constConst(Term t1, Term t2) {
        if (t1 instanceof Const && t2 instanceof Const) {
            return ((Const) t1).value().multiply(((Const) t2).value()).toString();
        } else if (t1 instanceof Const && t2 instanceof Multiply) {
            if (((Multiply) t2).t1 instanceof  Const) {     //2*(2*fx)
                return new Const(((Const) t1).value().multiply(
                        ((Const) ((Multiply) t2).t1).value()))
                        .mult(((Multiply) t2).t2).toString();
            } else if (((Multiply) t2).t2 instanceof  Const) {     //2*(fx*2)
                return new Const(((Const) t1).value().multiply(
                        ((Const) ((Multiply) t2).t2).value()))
                        .mult(((Multiply) t2).t1).toString();
            }
        } else if (t2 instanceof Const && t1 instanceof Multiply) {
            if (((Multiply) t1).t1 instanceof  Const) {         //(2*fx)*2
                return new Const(((Const) t2).value().multiply(
                        ((Const) ((Multiply) t1).t1).value()))
                        .mult(((Multiply) t1).t2).toString();
            } else if (((Multiply) t1).t2 instanceof  Const) {     //(fx*2)*2
                return new Const(((Const) t2).value().multiply(
                        ((Const) ((Multiply) t1).t2).value()))
                        .mult(((Multiply) t1).t1).toString();
            }
        } else if (t2 instanceof Multiply && t1 instanceof Multiply) {
            if (((Multiply) t1).t1 instanceof Const && ((Multiply) t2).t1 instanceof Const) {
                //(2*fx)*(2*gx)
                return new Const(((Const) ((Multiply) t1).t1).value().multiply(
                        ((Const) ((Multiply) t2).t1).value())).mult(((Multiply) t1).t2).mult(
                                ((Multiply) t2).t2).toString();
            }
            else if (((Multiply) t1).t1 instanceof Const && ((Multiply) t2).t2 instanceof Const) {
                //(2*fx)*(gx*2)
                return new Const(((Const) ((Multiply) t1).t1).value().multiply(
                        ((Const) ((Multiply) t2).t2).value())).mult(((Multiply) t1).t2).mult(
                        ((Multiply) t2).t1).toString();
            }
            else if (((Multiply) t1).t2 instanceof Const && ((Multiply) t2).t1 instanceof Const) {
                //(fx*2)*(2*gx)
                return new Const(((Const) ((Multiply) t1).t2).value().multiply(
                        ((Const) ((Multiply) t2).t1).value())).mult(((Multiply) t1).t1).mult(
                        ((Multiply) t2).t2).toString();
            }
            else if (((Multiply) t1).t2 instanceof Const && ((Multiply) t2).t2 instanceof Const) {
                //(fx*2)*(2*gx)
                return new Const(((Const) ((Multiply) t1).t2).value().multiply(
                        ((Const) ((Multiply) t2).t2).value())).mult(((Multiply) t1).t1).mult(
                        ((Multiply) t2).t1).toString();
            }
        }
        return "";
    }

    public boolean equals(Term other) {
        if (other instanceof Multiply) {
            return this.t1.equals(((Multiply) other).t1)
                    && this.t2.equals(((Multiply) other).t2);
        }
        return false;
    }

    public boolean isOne() {
        return t1.isOne() && t2.isOne();
    }

    public boolean isZero() {
        return t1.isZero() || t2.isZero();
    }
}