package co.yeisonvargas.battleship.peers.common;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public interface Backend {

    public boolean registerSecondPlayer(String name, String photo) throws RemoteException;

    public boolean sendMessage(String message, byte [] contentFile, String nameFile) throws RemoteException;

    public ArrayList<Message> getConversation() throws RemoteException;


}
