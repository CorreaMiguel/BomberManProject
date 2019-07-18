
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author migue
 */
public class PlayerSprite extends Sprite {
    
    public PlayerSprite() {
        super(ImageIO.read(Sprite.class.getResource("player"+Player.getNumber+".png")));
    }
    
}
