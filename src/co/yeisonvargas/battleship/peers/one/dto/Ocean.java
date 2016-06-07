package co.yeisonvargas.battleship.peers.one.dto;

import co.yeisonvargas.battleship.peers.one.business.Game;
import co.yeisonvargas.battleship.peers.one.dto.Player;
import com.sun.xml.internal.bind.v2.model.runtime.RuntimeNonElement;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public class Ocean {
    private Ship map [][];
    private Player owner;

    public Ocean(Player owner) {
        switch (Game.level) {
            case 0:
                this.map = new Ship[9][9];
                break;
            case 1:
                this.map = new Ship[13][13];
                break;
            case 2:
                this.map = new Ship[19][19];
                break;
            default:
                this.map = new Ship[9][9];
        }
        this.owner = owner;
    }




}
