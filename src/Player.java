
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author migue
 */
public class Player implements Serializable {
    
    private int speed;
    private int bombAreaEffect;
    private int bombCount;
    private String name;
    private int playerNumber;

    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.speed = 5;
        this.bombAreaEffect = 1;
        this.bombCount = 1;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBombAreaEffect() {
        return bombAreaEffect;
    }

    public void setBombAreaEffect(int bombAreaEffect) {
        this.bombAreaEffect = bombAreaEffect;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

}
