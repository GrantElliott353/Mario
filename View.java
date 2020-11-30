//William Elliott
//September 24 2020
//Assignemnt 5 Mario

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	//Member Variables	
	Model model;
	

	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
	}
	//load in the images, filename is the name of the image file
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{			
			im = ImageIO.read(new File(filename));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(97, 204, 248));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.drawSelf(g);
		}
		g.setColor(new Color(10, 218, 70));
		g.fillRect(0, 346, this.getWidth(), this.getHeight());
	}
}
