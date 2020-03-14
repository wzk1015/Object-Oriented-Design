public abstract class Term {
    public abstract boolean isOne();

    public abstract boolean isZero();

    public abstract Term derive();

    public abstract String toString();

    public abstract boolean equals(Term other);

    public Term add(Term other) {
        return new Add(this,other);
    }

    public Term mult(Term other) {
        return new Multiply(this,other);
    }
}
