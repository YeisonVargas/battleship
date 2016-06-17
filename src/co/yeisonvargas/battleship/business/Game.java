package co.yeisonvargas.battleship.business;

import co.yeisonvargas.battleship.dto.Ocean;
import co.yeisonvargas.battleship.dto.Player;
import co.yeisonvargas.battleship.dto.Ship;
import co.yeisonvargas.battleship.service.Facade;

import java.rmi.RemoteException;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public class Game {
    public static int level;
    private Player firstPlayer;
    private Player secondPlayer;
    private Ocean firstOcean;
    private Ocean secondOcean;
    // True is I can Play.
    public static boolean turn;
    public static boolean canPlay;
    public static int shipsLeftTwoSquares = 3;
    public static int shipsLeftThreeSquares = 2;
    public static int shipsLeftFourSquares = 2;
    public static int shipsLeftFiveSquares = 1;
    private Facade networkService;

    public Game(String name, String photo) {
        this.registerSecondPlayer(name, photo);
        Facade.role = false;
        this.initService();
    }

    public Game(int level, String name, String photo) {
        Game.level = level;
        this.firstPlayer = new Player(name, photo);
        this.firstOcean = new Ocean(this.firstPlayer);
        Facade.role = true;
        this.initService();
    }

    private void initService() {
        try {
            this.networkService = new Facade(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void registerSecondPlayer(String name, String photo) {
        this.secondPlayer = new Player(name, photo);
        this.secondOcean = new Ocean(this.secondPlayer);
        this.networkService.showThatSecondPlayerIsOnline();
    }

    public boolean checkStatusGame(String externalAddress, int externalPort) {
        return this.networkService.checkGame(externalAddress, externalPort);
    }

    public String [] registerShip(String coordinate) {
        System.out.println(coordinate);
            String start = coordinate.split("-")[0];
            String end = coordinate.split("-")[1];

        System.out.println("El tamaño es: " + this.getSize(start, end));

            if(this.getSize(start, end) == 2 && shipsLeftTwoSquares == 0 ||
               this.getSize(start, end) == 3 && shipsLeftThreeSquares == 0 ||
               this.getSize(start, end) == 4 && shipsLeftFourSquares == 0 ||
               this.getSize(start, end) == 5 && shipsLeftFiveSquares == 0 ) {
                return null;
            }

            if(start.equals(end)) {
                return null;
            }

            // TODO: Check that the ships are 3 of size 2, 2 of size 3, 2 of size 4 and 1 of 5 size.
            if(this.getSize(start, end) != 2 && this.getSize(start, end) != 3 && this.getSize(start, end) != 4 &&
                    this.getSize(start, end) != 5) {
                return null;
            }


            if(Facade.role) {
                // TODO: Check that one ship hasn't been put in the same coordinates that another.
                System.out.println("Insertando como Rol servidor.");
                return this.firstOcean.insertShip(new Ship(this.getSize(start, end), start, end, this.isVertical(start, end)));
            }

        return this.secondOcean.insertShip(new Ship(this.getSize(start, end), start, end, this.isVertical(start, end)));
    }

    private int getSize(String start, String end) {
        if(this.isVertical(start, end)){
            System.out.println(Integer.parseInt(start.split(",")[0]));
            System.out.println(Integer.parseInt(end.split(",")[0]));
            return Math.abs(Integer.parseInt(start.split(",")[0]) - Integer.parseInt(end.split(",")[0])) + 1;
        }

        return Math.abs(Integer.parseInt(start.split(",")[1]) - Integer.parseInt(end.split(",")[1])) + 1;
    }

    private boolean isVertical(String start, String end) {
        System.out.println(start.split(",")[1]);
        System.out.println(end.split(",")[1]);
        if(start.split(",")[1].equals(end.split(",")[1])) {
            return true;
        }
        return false;
    }

    public void checkDamage(String coordinate) {
        if(Facade.role) {
            this.firstOcean.attackShip(coordinate);
        } else {
            this.secondOcean.attackShip(coordinate);
        }

        // TODO: Check if shot was successful and calculate the score, 15 points for each square.

    }

    public String registerAttack(String coordinate) {
        return this.networkService.registerAttack(coordinate);
    }

    public void sendMessage(String message) {
        this.networkService.sendMessageChat(message, Facade.externalAddress, Facade.externalPort);
    }



}
