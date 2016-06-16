package co.yeisonvargas.battleship.dto;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public class Ship {
    private int size;
    private String start;
    private String end;
    private boolean vertical;

    public Ship(int size, String x, String y, boolean vertical) {
        this.size = size;
        this.start = x;
        this.end = y;
        this.vertical = vertical;
    }

    public int getSize() {
        return size;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public boolean isVertical() {
        return vertical;
    }
}
