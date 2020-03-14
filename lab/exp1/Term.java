public class Term implements Comparable<Term> {
    private int coef;
    private int index;
    public Term(int c,int i) {
        this.coef = c;
        this.index = i;
    }

    public int getCoef() {
        return this.coef;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        String ret;
        if (coef > 0) {
            ret = "" + coef;
        }
        else if (coef < 0) {
            ret = String.valueOf(coef);
        }
        else {
            ret = "0";
        }

        if (index == 0) {
            return ret;
        }
        else if (index == 1) {
            return ret + "x";
        }
        return ret + "*x^" + index;
    }

    public int compareTo(Term o) {
        // the first key : index
        if (this.index > o.getIndex()) {
            return -1;
        }
        if (this.index < o.getIndex()) {
            return 1;
        }

        // the second key : coeff
        return Integer.compare(this.coef, o.getCoef());

    }
}