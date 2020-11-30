//William Elliott
//September 24 2020
//Assignemnt 5 Mario

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model = new Model();
	Controller controller = new Controller(model);
	View view = new View(controller, model);
	
	//Creates the JFrame as well as
	//instantiates listeners for mouse and keyboard
	//inputs
	public Game()
	{
		model.unmarshal(Json.load("map.json"));
		this.addKeyListener(controller);
		view.addMouseListener(controller);
		this.setTitle("Starting to look like Mario!!");
		this.setSize(800, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run()
	{
		
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen	
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}	
				
		}
	}
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
