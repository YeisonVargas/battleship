package co.yeisonvargas.battleship.peers.one.business;

import co.yeisonvargas.battleship.peers.one.dto.Ocean;
import co.yeisonvargas.battleship.peers.one.dto.Player;
import co.yeisonvargas.battleship.peers.one.service.Facade;

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

    public Game(int level, String name, String photo) {
        Game.level = level;
        this.firstPlayer = new Player(name, photo);
        this.firstOcean = new Ocean(this.firstPlayer);
        try {
            new Facade(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean registerSecondPlayer(String name, String photo) {
        this.secondPlayer = new Player(name, photo);
        this.secondOcean = new Ocean(this.secondPlayer);
        return true;
    }



}
