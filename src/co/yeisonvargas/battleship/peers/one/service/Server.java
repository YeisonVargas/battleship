package co.yeisonvargas.battleship.peers.one.service;

import co.yeisonvargas.battleship.peers.common.Backend;
import co.yeisonvargas.battleship.peers.one.business.Game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author yeison_vargas
 */
public class Server extends UnicastRemoteObject implements Backend {

    private Registry onRmiRegistry;
    private final int port = 3232;
    private Game current;

    public Server(Game current) throws RemoteException {
        super();
        this.current = current;
    }

    public boolean startServer() {
        try {
            this.onRmiRegistry = LocateRegistry.createRegistry(this.port);
            this.onRmiRegistry.rebind("server", this);
        } catch (Exception e) {
            System.err.println("Server stopped while It was starting.");
            System.err.println(e.getMessage());
            return false;
        }

        System.out.println("Server is ON.");
        return true;
    }

    public boolean stopServer() {
        try {
            this.onRmiRegistry.unbind("server");
            unexportObject(this, true);
            unexportObject(this.onRmiRegistry, true);
        } catch (Exception e) {
            System.err.println("Server isn't stopped.");
            System.err.println(e.getMessage());
            return false;
        }

        System.out.println("Server is stopped.");
        return true;
    }


    @Override
    public boolean registerSecondPlayer(String name, String photo) throws RemoteException {
        return this.current.registerSecondPlayer(name, photo);
    }
}
