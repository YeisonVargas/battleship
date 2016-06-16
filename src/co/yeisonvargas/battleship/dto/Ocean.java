package co.yeisonvargas.battleship.dto;

import java.util.ArrayList;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public class Ocean {
    private ArrayList<Ship> ships;
    private Player owner;

    public Ocean(Player owner) {
        this.ships = new ArrayList<>();
        this.owner = owner;
    }

    public void insertShip(Ship newShip) {
        this.ships.add(newShip);
    }

    public void attackShip(String coordinate) {
        for (Ship item: this.ships) {
            if(isThereCollision(coordinate, item.getStart(), item.getEnd(), item.isVertical())){
                // TODO: Return coordinate of sunken ship and register in table
            }

            // TODO: Return coordinate of shot and register in table
        }
    }

    private boolean isThereCollision(String coordinate, String start, String end, boolean isVertical) {
        if(isVertical) {
            if(coordinate.charAt(1) == start.charAt(1)){
                if((int)coordinate.charAt(0) <= (int)start.charAt(0) && (int)coordinate.charAt(0) >= (int)end.charAt(0) ||
                        (int)coordinate.charAt(0) >= (int)start.charAt(0) && (int)coordinate.charAt(0) <= (int)end.charAt(0)) {
                    return true;
                }
                return false;
            }
            return false;
        }

        // Horizontal Case
        if (coordinate.charAt(0) == start.charAt(0)) {
            if((int)coordinate.charAt(1) <= (int)start.charAt(1) && (int)coordinate.charAt(1) >= (int)end.charAt(1) ||
                    (int)coordinate.charAt(1) >= (int)start.charAt(1) && (int)coordinate.charAt(1) <= (int)end.charAt(1)) {
                return true;
            }
            return false;
        }

        return false;
    }



}
