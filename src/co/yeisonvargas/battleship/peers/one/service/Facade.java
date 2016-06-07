package co.yeisonvargas.battleship.peers.one.service;

import co.yeisonvargas.battleship.peers.one.business.Game;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

/**
 * @author yeison_vargas
 */
public class Facade {

    private final String ownAddress;
    private final int ownPort;
    private final Server ownRmiServer;

    public Facade(Game current) throws RemoteException {
        this.ownAddress = "localhost";
        this.ownPort = 3232;
        this.ownRmiServer = new Server(current);
        this.ownRmiServer.startServer();
    }


}
