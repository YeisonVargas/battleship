package co.yeisonvargas.battleship.dto;

/**
 * Created by yeisonvargas on 7/06/16.
 */
public class Player {
    private String name;
    private String photo;
    private int score;

    public Player(String name, String photo) {
        this.name = name;
        this.photo = photo;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
