import java.awt.image.BufferedImage;
import java.awt.Graphics;
class Fireball extends Sprite{

    static BufferedImage image;
    Model model;
    double vert_velocity,x_velocity;

    Fireball (int x, int y, Model m){
        this.x = x;
        this.y=y;
        width = 47;
        height= 47;
        this.model=m;
        this.type = "fireball";
        if(image ==null)
            image = View.loadImage("fireball.png");
        
    }
    boolean update(){
        for(int i =0;i<model.sprites.size();i++){
            Sprite currentSprite = model.sprites.get(i);
            if(collide(currentSprite)){
                x_velocity = -x_velocity;
            }
            if(collide(currentSprite)&&(model.sprites.get(i) instanceof Goomba)){
                
			}
        }
        x_velocity=15;
        x+=x_velocity;
        vert_velocity+=1.5;
        y+=vert_velocity;
       
        if(this.y+height>400){
            vert_velocity= 0.0;
            y=250;
        }
        
        if(this.y+height==280){
            vert_velocity=-4.0;
        }
        return true;
    }
    boolean collide(Sprite t){
        int fireRight = x+width;
		int fireLeft = x;
		int spriteRight = t.x+t.width;
		int spriteLeft = t.x;
		boolean collide = false;
        //in the tube, previously left
        if(fireLeft>=spriteRight){
            collide = true;
        }
        //in the tube, previously right
        if(fireRight<= spriteLeft){
            collide = true;
        }        
        
        return collide;
    }
    void drawSelf(Graphics g){
        g.drawImage(image,x-model.mario.x+model.mario.placement,y,null);
    }
    Json marshal(){
        Json ob = Json.newObject();
		ob.add("y", y);
        ob.add("x", x);
        return ob;
    }
}

