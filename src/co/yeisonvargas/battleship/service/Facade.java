package co.yeisonvargas.battleship.service;

import co.yeisonvargas.battleship.common.Backend;
import co.yeisonvargas.battleship.common.Message;
import co.yeisonvargas.battleship.business.Game;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author yeison_vargas
 */
public class Facade {

    public static String ownAddress;
    public static int ownPort;
    private Server ownRmiServer;
    public static boolean role;
    public static String externalAddress;
    public static int externalPort;

    public Facade(Game current) throws RemoteException {
        Facade.ownAddress = "localhost";
        Facade.ownPort = 3232;
        this.ownRmiServer = new Server(current);
        this.ownRmiServer.startServer();
    }

    public Facade(Game current, int port) throws RemoteException {
        Facade.ownAddress = "localhost";
        Facade.ownPort = port;
        this.ownRmiServer = new Server(current);
        this.ownRmiServer.startServer();
    }


    public boolean sendMessageChat(String message) {
        String addr = this.ownAddress;
        int prt = this.ownPort;

        if(!Facade.role) {
            addr = this.externalAddress;
            prt = this.externalPort;
        }

        try {
            Registry myRegister = LocateRegistry.getRegistry(addr, prt);
            Backend backend = (Backend) (myRegister.lookup("server"));
            backend.sendMessage(message, "");
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
            return false;
        }

        if(Facade.role) {
            try {
                System.out.println(this.externalAddress);
                System.out.println(this.externalPort);
                Registry myRegister = LocateRegistry.getRegistry(this.externalAddress, this.externalPort);
                Backend backend = (Backend) (myRegister.lookup("server"));
                backend.refreshUI();
            } catch (RemoteException | NotBoundException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }

        return true;
    }

    public ArrayList<String []> getConversation() {
        ArrayList<Message> messages;

        String addr = this.ownAddress;
        int prt = this.ownPort;

        if(!Facade.role) {
            addr = this.externalAddress;
            prt = this.externalPort;
        }

            try {
                Registry myRegister = LocateRegistry.getRegistry(addr,
                        prt);
                Backend backend = (Backend)myRegister.lookup("server");
                messages = backend.getConversation();
                backend.getConversation();
            } catch (RemoteException | NotBoundException e) {
                System.err.println(e.getMessage());
                return null;
            }


        return normalizate(messages);
    }


    public String [] registerAttack(String coordinate) {
        try {
            Registry myRegister = LocateRegistry.getRegistry(externalAddress, externalPort);
            Backend backend = (Backend) (myRegister.lookup("server"));
            return backend.registerAttack(coordinate);
        } catch (RemoteException | NotBoundException e) {
            return null;
        }
    }

    public boolean checkGame(String externalAddress, int externalPort) {
        try {
            Registry myRegister = LocateRegistry.getRegistry(externalAddress, externalPort);
            Backend backend = (Backend) (myRegister.lookup("server"));
            return backend.checkGame();
        } catch (RemoteException | NotBoundException e) {
            return false;
        }
    }

    private ArrayList<String []> normalizate(ArrayList<Message> allMessages) {
        ArrayList<String []> result = new ArrayList<>();
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        for(Message item: allMessages) {
            String [] resumeItem = new String[2];
            resumeItem[0] = item.getContent();
            resumeItem[1] = defaultFormat.format(item.getDate());
            result.add(resumeItem);
        }

        return result;
    }


    public String showThatSecondPlayerIsOnline() {
        try {
            System.out.println(externalAddress);
            System.out.println(externalPort);
            Registry myRegister = LocateRegistry.getRegistry(externalAddress, externalPort);
            Backend backend = (Backend) (myRegister.lookup("server"));
            return backend.showSecondPlayerHasJoined(Facade.ownAddress, Facade.ownPort);
        } catch (RemoteException | NotBoundException e) {
            return null;
        }
    }


    public int getLevel() {
        try {
            System.out.println(externalAddress);
            System.out.println(externalPort);
            Registry myRegister = LocateRegistry.getRegistry(externalAddress, externalPort);
            Backend backend = (Backend) (myRegister.lookup("server"));
            return backend.getLevel();
        } catch (RemoteException | NotBoundException e) {
            return -1;
        }
    }


    public String getData() {
        try {
            System.out.println(externalAddress);
            System.out.println(externalPort);
            Registry myRegister = LocateRegistry.getRegistry(externalAddress, externalPort);
            Backend backend = (Backend) (myRegister.lookup("server"));
            return backend.getData();
        } catch (RemoteException | NotBoundException e) {
            return null;
        }
    }


}
