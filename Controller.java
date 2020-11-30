//William Elliott
//September 24 2020
//Assignemnt 5 Mario

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean space;
	boolean ctrl;

	Controller(Model m)
	{
		model = m;
	}
	
	void setView(View v)
	{
		view = v;
	}
	public void actionPerformed(ActionEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
		//model.checkClickedTube(e.getX()+ (model.mario.x-200), e.getY());
		//model.sprites.add(new Goomba(e.getX(),246,model));		
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true;  break;
			case KeyEvent.VK_LEFT: keyLeft = true;   break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: space = true; break;
			case KeyEvent.VK_CONTROL: ctrl = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: space = false; break;
			case KeyEvent.VK_CONTROL: ctrl = false; break;
		}
		char c = e.getKeyChar();
		if(c=='s')
		{
			model.marshal().save("map.json");
			System.out.println("File saved under map.json");
		}
		if (c == 'l')
		{
			System.out.println("You pressed L, loading map.json");
			Json l = Json.load("map.json");
			model.unmarshal(l);
			System.out.println("map.json loaded");
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.savePrevCoord();
		if(ctrl==true){
			model.addFireball();
		}
		if(keyRight){
			if(model.mario.marioImageNum==4){
				model.mario.marioImageNum=0;
			}else{
				model.mario.marioImageNum++;
			}
			model.mario.x+=5;
			
			
		}
		if(keyLeft){
			if(model.mario.marioImageNum==4){
				model.mario.marioImageNum=0;
			}else{
				model.mario.marioImageNum++;
			}
			model.mario.x-=5;
			
		}
		if(keyUp||space){
			if(model.mario.marioImageNum==4){
				model.mario.marioImageNum=0;
			}else{
				model.mario.marioImageNum++;
			}
			if(model.mario.framesInAir>12){}else{model.mario.jump();}
			
		}
		
	}
	
}
