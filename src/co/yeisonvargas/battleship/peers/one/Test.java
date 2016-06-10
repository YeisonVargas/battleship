package co.yeisonvargas.battleship.peers.one;

import co.yeisonvargas.battleship.peers.one.service.Facade;

import java.rmi.RemoteException;

/**
 * Created by yeisonvargas on 10/06/16.
 */
public class Test {

    public static void main(String args [][]) {
        try {
            new Facade(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
