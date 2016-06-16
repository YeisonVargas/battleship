package co.yeisonvargas.battleship;

import co.yeisonvargas.battleship.business.Game;
import co.yeisonvargas.battleship.service.Facade;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yeisonvargas on 10/06/16.
 */
public class Test {

    public static void main(String args []) {

        // True like a server (First Player), False like a client (Second Player)
        boolean role = false;
        String name = "Yeison Vargas";
        String photo = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpf1/v/t1.0-1/p160x160/11822643_880142592073553_1976185143104301440_n.jpg?oh=9f57971ad6e1d88df300c93442df80cf&oe=57C17F41&__gda__=1476024999_5e0c19a5bc09b78415b6669d5e31ee5b";
        final String externalAddress = "localhost";
        final int externalPort = 3233;

        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Facade.role = role;
        if (role) {
            Game serverGame = new Game(0, name, photo);

            String [] coordinates = {"A2-B3", "A1-C3"};

            serverGame.registerShips(coordinates);


            if(!Game.canPlay) {
                System.out.println("Waiting for a second player");
            }

            // Changed by RMI, default True for first player.
            Game.turn = true;


            //serverGame.



//            player 1 joined 00
//            player 2 joined 01
//            player 1 chose your ships 10
//            player 2 chose your ships 11


            // Waiting that player chooses your ships.

            // Check that second player already chose your ships.

            // Check that It's second player turn.

            // Play!

            // Is it my turn?

        } else {
            Game clientGame = new Game(name, photo);
            if(!clientGame.checkStatusGame(externalAddress, externalPort)) {
                System.out.println("The external game isn't ready for a second player.");
                return;
            }

            // Show that second player has joined.

            // Waiting that player chooses your ships.

            // Check that first player already chose your ships.

            // Check that It's second player turn.

            // Play !

            // Is it my turn?
        }


    }
}
