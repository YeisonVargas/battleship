package co.yeisonvargas.battleship.service;

import co.yeisonvargas.battleship.common.Backend;
import co.yeisonvargas.battleship.common.Message;
import co.yeisonvargas.battleship.business.Game;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author yeison_vargas
 */
public class Server extends UnicastRemoteObject implements Backend {

    private Registry onRmiRegistry;
    private int port;
    private String address;
    private Game current;
    private ArrayList<Message> messages;

    public Server(Game current) throws RemoteException {
        super();
        this.messages = new ArrayList<>();
        this.current = current;
    }

    public boolean startServer() throws RemoteException {
        try {
            // get the address of this host.
            this.address = (InetAddress.getLocalHost()).toString();
        } catch (Exception e) {
            throw new RemoteException("can't get inet address.");
        }
        this.port = Facade.ownPort;
        System.out.println("Listening at " + this.address + ", port=" + this.port);
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
    public boolean sendMessage(String message, String name) throws RemoteException {
        this.messages.add(new Message(message, name));
        this.current.refreshChat();
        return true;
    }

    @Override
    public void refreshUI() throws RemoteException {
        this.current.refreshChat();
    }


    @Override
    public ArrayList<Message> getConversation() throws RemoteException {
        return this.messages;
    }

    @Override
    public String [] registerAttack(String coordinate) throws RemoteException {
        return this.current.checkDamage(coordinate);
    }

    @Override
    public int getScore() {
        return this.current.getScore();
    }

    @Override
    public int getLevel() throws RemoteException {
        return Game.level;
    }

    @Override
    public String getData() throws RemoteException {
        return String.valueOf(this.current.getScore()) + "-" + this.current.getNamePlayer();
    }


    // For second player
    @Override
    public boolean checkGame() throws RemoteException {
        // Check if first player is online and ready the otherwise false.
        Facade.externalAddress = "";
        Facade.externalPort = 3232;
        return false;
    }

    @Override
    public String showSecondPlayerHasJoined(String ip, int port) throws RemoteException {
        // Show in UI that second player has joined.
        Facade.externalAddress = ip;
        Facade.externalPort = port;
        System.out.println("Se ha unido un segundo jugador a la partida.");
        System.out.println(String.valueOf(this.current.getScore()) + "-" + this.current.getNamePlayer());

        this.current.refreshDataFromPlayer();

        return String.valueOf(this.current.getScore()) + "-" + this.current.getNamePlayer();
    }



    @Override
    public void showSecondPlayerHasRegisteredShips() throws RemoteException {
        // Show in UI that second player has registered your ships.
    }

    @Override
    public void showFirstPlayerHasRegisteredShips() throws RemoteException {
        // Show in UI that second player has registered your ships.
    }

    @Override
    public void showItIsYourTurn() throws RemoteException {

    }


}
