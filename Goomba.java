//William Elliott
//September 24 2020
//Assignemnt 5 Mario
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Goomba extends Sprite{
    static BufferedImage[] images=null;
    Model model;
    String type = "goomba";
    int speed = 10, ground = 391,onFire=0,count =0;
    boolean fire = false,dead = false;

    public Goomba(int x, int y, Model m){
        this.x = x;
        this.y=y;
        width = 99;
        height = 118;
        model=m;
        type = "goomba";
        images = new BufferedImage[2];
        images[0] = View.loadImage("goomba.png");
        images[1] = View.loadImage("goomba_fire.png");
        
    }
    public Goomba(Json ob, Model m){
        y = (int)ob.getLong("y");
		x = (int)ob.getLong("x");
        model=m;
        type = "goomba";
        images = new BufferedImage[2];
        images[0] = View.loadImage("goomba.png");
        images[1] = View.loadImage("goomba_fire.png");
        
    }
    
    //return true if the goomba is alive, if the goomba is dead return false
    boolean update(){
        
        for(int i =0;i<model.sprites.size();i++){
            Sprite currentSprite = model.sprites.get(i);
            //checking to see if goomba hit a tube
            if(collide(currentSprite)&&(currentSprite instanceof Tube)){
                speed = -speed;
            }
            //checking to see if goomba hit a fireball
            if(fireCollide(currentSprite)&&(currentSprite instanceof Fireball)){
                fire = true;
            } 
        }
        x+=speed;
        return true;
    }
    
    
    void drawSelf(Graphics g)
    {
        if(dead==true){
            model.sprites.remove(this);//remove goomba
        }else if(fire==true){
            g.drawImage(images[1],x-model.mario.x+model.mario.placement,273,null);
            onFire++;//allows goomba to stay on fire
            if(onFire>40){
                dead=true;//Goomba has burned long enough
            }
        }if(fire==false)
            g.drawImage(images[0],x-model.mario.x+model.mario.placement,273,null);
        
    }
    //needs edditing still
    boolean collide(Sprite t){
        int goombaRight = x+width;
		int goombaLeft = x;
		int tubeRight = t.x+t.width;
		int tubeLeft = t.x;
		
        //in the tube, previously left
        if(goombaLeft>=tubeRight){
            return true;
        }
        //in the tube, previously right
        if(goombaRight+55<= tubeLeft){
            return true;
        }        
        return false;
    }
    boolean fireCollide(Sprite t){
        int goombaRight = x+width;
		int goombaLeft = x;
		int tubeRight = t.x+t.width;
		int tubeLeft = t.x;
		
        //in the tube, previously left
        if((goombaLeft<=tubeRight)&&(goombaRight>tubeLeft)){
            return true;
        }
             
        return false;
    }
	Json marshal(){
        Json ob = Json.newObject();
        ob.add("Type",type);
		ob.add("y", y);
        ob.add("x", x);
        return ob;
    }
}