//William Elliott
//September 24 2020
//Assignemnt 5 Mario
import java.awt.Graphics;
import java.util.ArrayList;
abstract class Sprite {
    int x,y;
    int width, height;
    String type;
    abstract boolean update();
    abstract void drawSelf(Graphics g);
    abstract Json marshal();
    
}
