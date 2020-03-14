public class Position implements java.lang.Comparable<Position> {
    private int row;
    private int col;

    Position(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int compareTo(Position other) {
        if (this.row != other.row) {
            return this.row - other.row;
        }
        return this.col - other.col;
    }
}
