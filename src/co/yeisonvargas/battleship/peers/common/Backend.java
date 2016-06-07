package co.yeisonvargas.battleship.peers.common;

import java.rmi.RemoteException;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public interface Backend {

    public boolean registerSecondPlayer(String name, String photo) throws RemoteException;

}
