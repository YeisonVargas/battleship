package co.yeisonvargas.battleship.peers.one.service;

import co.yeisonvargas.battleship.peers.common.Backend;
import co.yeisonvargas.battleship.peers.common.Message;
import co.yeisonvargas.battleship.peers.one.business.Game;

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

    private final String ownAddress;
    private final int ownPort;
    private final Server ownRmiServer;

    public Facade(Game current) throws RemoteException {
        this.ownAddress = "localhost";
        this.ownPort = 3232;
        this.ownRmiServer = new Server(current);
        this.ownRmiServer.startServer();
    }


    public boolean sendMessageChat(String message, String pathAttachment) {
        try {
            Registry myRegister = LocateRegistry.getRegistry(this.ownAddress,
                    this.ownPort);
            Backend backend = (Backend) (myRegister.lookup("server"));
            backend.sendMessage(message, null, "");
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }


    public ArrayList<String []> getConversation() {
        try {
            Registry myRegister = LocateRegistry.getRegistry(this.ownAddress,
                    this.ownPort);
            Backend backend = (Backend) myRegister.lookup("server");
            return normalizate(backend.getConversation());
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
            return null;
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


}
