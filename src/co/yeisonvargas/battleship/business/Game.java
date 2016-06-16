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
    private Facade networkService;

    public Game(String name, String photo) {
        this.registerSecondPlayer(name, photo);
        this.initService();
    }

    public Game(int level, String name, String photo) {
        Game.level = level;
        this.firstPlayer = new Player(name, photo);
        this.firstOcean = new Ocean(this.firstPlayer);
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

    public boolean registerShips(String [] coordinates) {
        for(int i=0; i<coordinates.length; i++) {
            String start = coordinates[i].split("-")[0];
            String end = coordinates[i].split("-")[1];

            // TODO: Check that one ship hasn't been put in the same coordinates that another.
            // TODO: Check that the ships are 3 of size 2, 2 of size 3, 2 of size 4 and 1 of 5 size.
            if(Facade.role) {
                this.firstOcean.insertShip(new Ship(this.getSize(start, end), start, end, this.isVertical(start, end)));
            } else {
                this.secondOcean.insertShip(new Ship(this.getSize(start, end), start, end, this.isVertical(start, end)));
            }
        }

        return false;
    }

    private boolean checkCoordinate(String coordinate) {
        String start = coordinate.split("-")[0];
        String end = coordinate.split("-")[1];

        if(start.equals(end)) {
            return false;
        }

        // Vertical Case, 5A-10A (A == A)
        if(this.isVertical(start, end)) {
            return true;
        }

        // Horizontal Case, 1A-1D (1 == 1)
        if(!this.isVertical(start, end)) {
            return true;
        }


        return false;
    }

    private int getSize(String start, String end) {
        if(this.isVertical(start, end)){
            return Math.abs((int)start.charAt(0) - (int)end.charAt(0));
        }

        return Math.abs((int)start.charAt(1) - (int)end.charAt(1));
    }

    private boolean isVertical(String start, String end) {
        if(start.charAt(1) == end.charAt(1)) {
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
