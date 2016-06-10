package co.yeisonvargas.battleship.peers.two.facade;

import co.yeisonvargas.battleship.peers.common.Backend;
import co.yeisonvargas.battleship.peers.common.Message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by yeisonvargas on 10/06/16.
 */
public class Facade {

    private final String externalAddress;
    private final int externalPort;


    public Facade(String externalAddress, int externalPort) {
        this.externalAddress = externalAddress;
        this.externalPort = externalPort;
    }


    public boolean sendMessageChat(String message, String pathAttachment) {
        try {
            Registry myRegister = LocateRegistry.getRegistry(this.externalAddress,
                    this.externalPort);
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
            Registry myRegister = LocateRegistry.getRegistry(this.externalAddress,
                    this.externalPort);
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
