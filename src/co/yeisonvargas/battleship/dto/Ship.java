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

    public String [] getPositions() {
        if(this.isVertical()) {
            int colum = Integer.parseInt(String.valueOf(this.start.split(",")[1]));

            int reallyStart = Integer.parseInt(String.valueOf(this.start.split(",")[0]));
            int reallyEnd = Integer.parseInt(String.valueOf(this.end.split(",")[0]));
            if(Integer.parseInt(String.valueOf(this.start.split(",")[0])) - Integer.parseInt(String.valueOf(this.end.split(",")[0])) > 0) {
                reallyStart = Integer.parseInt(String.valueOf(this.end.split(",")[0]));
                reallyEnd = Integer.parseInt(String.valueOf(this.start.split(",")[0]));
            }

            String [] coordinates = new String [(reallyEnd - reallyStart) + 1];

            for(int i=0; i<coordinates.length; i++) {
                coordinates[i] = reallyStart + "-" + colum;
                reallyStart++;
            }

            return coordinates;

            }

        int row = Integer.parseInt(String.valueOf(this.start.split(",")[0]));

        int reallyStart = Integer.parseInt(String.valueOf(this.start.split(",")[1]));
        int reallyEnd = Integer.parseInt(String.valueOf(this.end.split(",")[1]));
        if(Integer.parseInt(String.valueOf(this.start.split(",")[1])) - Integer.parseInt(String.valueOf(this.end.split(",")[1])) > 0) {
            reallyStart = Integer.parseInt(String.valueOf(this.end.split(",")[1]));
            reallyEnd = Integer.parseInt(String.valueOf(this.start.split(",")[1]));
        }

        String [] coordinates = new String [(reallyEnd - reallyStart) + 1];

        System.out.println("Tamaño del array: " + coordinates.length);
        System.out.println("Empiezo realmente: " +  reallyStart);
        System.out.println("Termino realmente: " + reallyEnd);
        for(int i=0; i<coordinates.length; i++) {
            coordinates[i] = row + "-" + reallyStart;
            reallyStart++;
            System.out.println("Coordenadas en for" + coordinates[i]);
        }

        return coordinates;
    }

    @Override
    public String toString() {
        String var = "Ship{" +
                "size=" + size +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", vertical=" + vertical +
                "} - Positions: ";

        for(String a: this.getPositions()){
            var += a + " ,";
        }

        return var;
    }
}
