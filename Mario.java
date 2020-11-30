//William Elliott
//September 24 2020
//Assignemnt 5 Mario
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Mario extends Sprite {
    int prevX,prevY;    //Saves the last position for marion, used for collision detection
    int marioImageNum,framesInAir;
    boolean onSurface=true;
    double vert_velocity;    
    int placement =200;
    static BufferedImage[] mario_Images = null;

    Mario(int x, int y){
        this.x=x;
        this.y=y;
        this.width = 50;
        this.height = 95;
        marioImageNum = 0;
        loadMarioImage();
        type = "mario";
    }
    
    void loadMarioImage(){
        mario_Images= new BufferedImage[5];
		mario_Images[0]=View.loadImage("mario1.png");
		mario_Images[1]=View.loadImage("mario2.png");
		mario_Images[2]=View.loadImage("mario3.png");
		mario_Images[3]=View.loadImage("mario4.png");
        mario_Images[4]=View.loadImage("mario5.png");
    }
    //Saves the last known coordinates of mario
    void savePrevCoord()
    {
        prevX=x;
        prevY=y;
    }

    void jump()
    {
        
        if(framesInAir>=20){
            vert_velocity=1.2;
            
        }else{
            vert_velocity=-17.0;
            
        }
        
    }

    void getOutofTube(Sprite t){
        int marioRight = x+width;
		int marioLeft = x;
		int tubeRight = t.x+t.width;
		int tubeLeft = t.x;
		int marioToes = y+height;
		int tubeTop = t.y;
        int tubeBottom = t.y+t.height;
        
        //in the tube, previously left
        if(marioRight>=tubeLeft&&prevX+width<=tubeLeft){
            x=t.x-width;
        }
        //in the tube, previously right
        if(marioLeft<= tubeRight && prevX>=tubeRight){            
            x = tubeRight;
        }
        //On top of the tube, previously above
        if(marioToes>=tubeTop&& prevY+height < tubeTop){
            y=tubeTop-(height+1);
            framesInAir=0;
        }
        
    }

    void drawSelf(Graphics g){
            g.drawImage(mario_Images[marioImageNum],placement,y,null);
    }
    //made update to boolean in order to keep track of whether a chararcter is alive or not

    boolean update()
    {
        vert_velocity+=1.2;
        y+=vert_velocity;
        if(marioImageNum>4){
            marioImageNum=0;
        }
        //keep mario on the ground
        if(y>=251){
            vert_velocity= 0.0;
            y=250;
        }
        //Count how long mario is in the air
        if(y<250){
            framesInAir++;
        }
        //if mario is on the ground, resent the amount of time he in the air
        if(y==250){
            framesInAir=0;
        }
        return true;
        
    }
	Json marshal(){
        Json ob = Json.newObject();
        ob.add("Type",type);
		ob.add("y", y);
        ob.add("x", x);
        return ob;
    }
}
