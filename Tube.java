//William Elliott
//September 24 2020
//Assignemnt 5 Mario
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tube extends Sprite{
	static BufferedImage tube=null;
	Model model;
	
    Tube(int x,int y,Model m){
        this.x = x;
		this.y = y;
		width =55;
		height=400;
		model = m;
		loadTubeImage();
		type="tube";
	}

	Tube(Json ob,Model m)
    {
		y = (int)ob.getLong("y");
		x = (int)ob.getLong("x");
		width =55;
		height=400;
		loadTubeImage();
		type="tube";
		model = m;
	}

	//update abstract class from the extended sprite class
	boolean update() {
		return true;
	}


	void loadTubeImage(){
		if(tube==null)
		{
			tube = View.loadImage("tube.png");
		}
	}

	void drawSelf(Graphics g){
		//50 is the mario offset
		g.drawImage(tube,x-model.mario.x+model.mario.placement,y,null);
	}
	
	public boolean tubeClicked(int clicked_x, int clicked_y)
	{
		if(clicked_x < x)
		{
			return false;
		}else if(clicked_x > x + width)
		{
			return false;
		}else if(clicked_y < y)
		{
			return false;
		}else if(clicked_y > y + height)
		{
			return false;
		}else
		{
			return true;
		}
	}
	Json marshal(){
        Json ob = Json.newObject();
        ob.add("Type",type);
		ob.add("y", y);
        ob.add("x", x);
        return ob;
    }
}
