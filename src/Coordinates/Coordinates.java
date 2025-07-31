package Coordinates;

import java.util.Objects;

public class Coordinates {
    public static final int MAX_LINE=10;
    public static final int MAX_COLUMN=15;

    private int line;
    private int column;

    public Coordinates(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return line == that.line && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public boolean isValid (){
        return  ((getLine()<MAX_LINE && getLine()>=0) &&
                (getColumn()<MAX_COLUMN && getColumn()>=0));
    }
}
