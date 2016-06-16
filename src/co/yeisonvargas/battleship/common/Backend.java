package co.yeisonvargas.battleship.common;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public interface Backend {


    public boolean checkGame() throws RemoteException;

    public boolean sendMessage(String message, String name) throws RemoteException;

    public ArrayList<Message> getConversation() throws RemoteException;

    public String registerAttack(String coordinate) throws RemoteException;


    public void showSecondPlayerHasJoined() throws RemoteException;

    public void showSecondPlayerHasRegisteredShips() throws RemoteException;

    public void showFirstPlayerHasRegisteredShips() throws RemoteException;


    public void showItIsYourTurn() throws RemoteException;


}
