package co.yeisonvargas.battleship.dto;

import co.yeisonvargas.battleship.business.Game;

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

    public String [] insertShip(Ship newShip) {
        System.out.println("Method: insertShip...");
        if(isOcupate(newShip)) {
            System.out.println("It's ocupated.");
            this.printShips();
            return null;
        }
        this.ships.add(newShip);
        switch (newShip.getSize()) {
            case 2:
                Game.shipsLeftTwoSquares--;
                break;
            case 3:
                Game.shipsLeftThreeSquares--;
                break;
            case 4:
                Game.shipsLeftFourSquares--;
                break;
            case 5:
                Game.shipsLeftFiveSquares--;
                break;
            default:
                break;
        }
        System.out.println("Printing ships...");
        this.printShips();
        return newShip.getPositions();
    }

    private void printShips() {
        for(Ship a: this.ships) {
            System.out.println(a.toString());
        }
    }

    public String [] attackShip(String coordinate) {
        for (Ship item: this.ships) {
            if(isThereCollision(coordinate, item.getStart(), item.getEnd(), item.isVertical())){
                // TODO: Return coordinate of sunken ship and register in table

                return item.getPositions();
            }
        }

        // TODO: Return coordinate of shot and register in table
        String [] result = {coordinate};
        return result;
    }

    private boolean isOcupate(Ship newShip) {
        System.out.println("Method: Is Ocupate...");
        String [] positionsNewShip = newShip.getPositions();
        for(Ship item: this.ships) {
            for(String position: item.getPositions()) {
                for(String positionNewShip: positionsNewShip) {
                    if(position.equals(positionNewShip)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isThereCollision(String coordinate, String start, String end, boolean isVertical) {
        if(isVertical) {
            if(coordinate.split(",")[1] == start.split(",")[1]){
                if(Integer.parseInt(coordinate.split(",")[0]) <= Integer.parseInt(start.split(",")[0]) && Integer.parseInt(coordinate.split(",")[0]) >= Integer.parseInt(end.split(",")[0]) ||
                        Integer.parseInt(coordinate.split(",")[0]) >= Integer.parseInt(start.split(",")[0]) && Integer.parseInt(coordinate.split(",")[0]) <= Integer.parseInt(end.split(",")[0])) {
                    return true;
                }
                return false;
            }
            return false;
        }

        // Horizontal Case
        if (coordinate.split(",")[0] == start.split(",")[0]) {
            if(Integer.parseInt(coordinate.split(",")[1]) <= Integer.parseInt(start.split(",")[1]) && Integer.parseInt(coordinate.split(",")[1]) >= Integer.parseInt(end.split(",")[1]) ||
                    Integer.parseInt(coordinate.split(",")[1]) >= Integer.parseInt(start.split(",")[1]) && Integer.parseInt(coordinate.split(",")[1]) <= Integer.parseInt(end.split(",")[1])) {
                return true;
            }
            return false;
        }

        return false;
    }



}
