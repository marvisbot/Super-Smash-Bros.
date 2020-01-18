import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class EndScreen implements KeyListener {	//KeyListener is like ActionListener but for keyboard
	public PhysicsObject winner, loser;
	private int rectX,rectY,rectWidth,rectHeight;
	private int currentSelection = 0;
	private final int height = 600;	//Window dimensions
	private final int width = 900;
	private int[][] wordBoundsX= new int[][] {
		{610-5,200+10}, {500-5,395+9}, {560-5,300+10}
	};//Order: SMASH, How to play, settings
	private int[][] wordBoundsY = new int[][] {
		{240-5, 40+10},{340-5,40+10},{440-5,40+10}
	};
	private Font font = null,fontSmaller=null, fontMedium=null;

	private boolean closed = false;
	private Image backgroundImg  = Toolkit.getDefaultToolkit().createImage("gameOver.jpg").getScaledInstance(width, height,java.awt.Image.SCALE_SMOOTH);
	boolean showStat = false;
	private JFrame frame;	
	private JPanel panel = new canvas();	

	public EndScreen() {
		String fName = "superFont.ttf";
		File fontFile = new File(fName);		

		try {
			Font tempfont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			font = tempfont.deriveFont((float)(40));
			fontSmaller = tempfont.deriveFont((float)(20));
			fontMedium = tempfont.deriveFont((float)(25));

		} catch (FontFormatException e) {
		} catch (IOException e) {
		}

		frame = new JFrame("Super Smash");	//Frame stuff
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.addKeyListener(this);		

		panel.setLayout(new BorderLayout());	

		frame.add(panel);

		frame.setLocationRelativeTo(null);	//Make the frame visible
		frame.setVisible(true);	
		Thread updateMenu = new Thread(new Runnable() {	//The main loop
			public void run() {	

				while (!closed) {	
					frame.repaint();	//Refresh frame and panel
					panel.repaint();
					try {Thread.sleep(17);} catch (Exception ex) {}	//10 millisecond delay between each refresh
				}
				frame.dispose();

			}
		});	
		updateMenu.start();	//Start the main loop

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Physics.playSound("menuSelect");
			showStat = true;

		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {	//Call the graphics constructor
		new EndScreen();
	}



	public class canvas extends JPanel {	//Make a new JPanel that you can draw objects onto (Can't draw stuff anywhere you want onto normal JPanels)
		public void paintComponent(Graphics g) {
			super.paintComponent(g);	//Call paintComponent from the overlord JPanel
			
			if(!showStat) {
				g.drawImage(backgroundImg,0,0, null);
			}else {
				g.setColor(new Color(34,33,34));
				g.fillRect(0, 0, width, height);
				g.setColor(new Color(255,255,255));
				g.setFont(font);
				g.drawString("WINNER:", 50, 100);
				
				g.setColor(new Color(200, 0,0));
				g.drawRect(rectX, rectY, rectWidth, rectHeight);
				g.drawRect(rectX+1, rectY+1, rectWidth-2, rectHeight-2);
				g.drawRect(rectX-1, rectY-1, rectWidth+2, rectHeight+2);
			}
		}
	}
}