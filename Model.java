//William Elliott
//September 24 2020
//Assignemnt 5 Mario
import java.util.*;
import java.awt.event.MouseEvent;
class Model
{
	ArrayList<Sprite> sprites;
	Mario mario;
	
	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(100,250);
		sprites.add(mario);
		
	}
	
	public void update()
	{
	
		for(int i =0;i<sprites.size();i++){
			sprites.get(i).update();
			Sprite currentSprite = sprites.get(i);
			//checking collision
			if(collision(currentSprite)){
				mario.getOutofTube(currentSprite);
			}
			//if goomba is out of jframe, remove goomba
			if(currentSprite instanceof Goomba){
				//800 is the number we set our Jframe size as
				//depending on the size of the window when launched 
				//the goombas might not get removed when mario goes off screen
			 	if((currentSprite.x<(mario.x-800))||(currentSprite.x)>(mario.x+800)){
                     sprites.remove(currentSprite);
             }
			}
		}
	}
	boolean collision(Sprite t){
		int marioRight = mario.x+mario.width;
		int marioLeft = mario.x;
		int spriteRight = t.x+t.width;
		int spriteLeft = t.x;
		int marioHead = mario.y;
		int marioToes = mario.y+mario.height;
		int spriteTop = t.y;
		int spriteBottom = t.y+t.height;
		
		if(marioRight<spriteLeft)
			return false;
		if(marioLeft>spriteRight)
			return false;
		if(marioToes<spriteTop)
			return false;
		if(marioHead>spriteBottom)
			return false;
		return true;
	}

	
	
	//Checking if a tube is clicked using iterator.hasNext()
	public void checkClickedTube(int x, int y)
	{
		boolean clicked = false;
		Tube t = new Tube(x,y,this);
		
		//using an iterator
		for(int i =0;i<sprites.size();i++)
		{
			if(sprites.get(i) instanceof Tube)
			{
				Tube temp = (Tube)sprites.get(i);
				if(temp.tubeClicked(x, y))
				{
					clicked = true;
					sprites.remove(temp);
					break;
				}
			}
		}
		if(clicked == false)
		{
			sprites.add(t);
		}
	}
	//I think this method can be taken out but I am keeping it in just in case
	void addFireball(){
		Fireball fireball = new Fireball(mario.x,mario.y,this);
		sprites.add(fireball);
	}
	
	Json marshal()
    {
        Json ob = Json.newObject();
		Json tubeList = Json.newList();
		Json goombaList =Json.newList();
		ob.add("tubes",tubeList);
		ob.add("goombas",goombaList);
		
	    for(int i = 0; i < sprites.size(); i++){
			if(sprites.get(i).type.equals("tube")) 
				tubeList.add(sprites.get(i).marshal());
			if(sprites.get(i).type.equals("goomba")) 
				goombaList.add(sprites.get(i).marshal());
		 	
		}
        return ob;
	}

	
	
	void unmarshal(Json ob){
		 sprites=new ArrayList<Sprite>();
		 sprites.add(mario);
		 Json tubeList = ob.get("tubes");
		 Json goombaList = ob.get("goombas");
	 	 for(int i = 0; i < tubeList.size(); i++)
         {
			sprites.add(new Tube(tubeList.get(i),this));
		 }	
		for(int i = 0; i < goombaList.size(); i++)
        {
			sprites.add(new Goomba(goombaList.get(i),this));
			sprites.get(sprites.size()-1).type="goomba"; 
		}	
        }
	}
