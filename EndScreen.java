import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class EndScreen implements KeyListener {	//KeyListener is like ActionListener but for keyboard
	public static String winner, loser;
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

	public EndScreen(String winner, String loser) {
		this.winner=winner;
		this.loser = loser;
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
			System.exit(0);

		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {	//Call the graphics constructor
		new EndScreen("link", "samus");
	}



	public class canvas extends JPanel {	//Make a new JPanel that you can draw objects onto (Can't draw stuff anywhere you want onto normal JPanels)
		public void paintComponent(Graphics g) {
			super.paintComponent(g);	//Call paintComponent from the overlord JPanel
			
			if(!showStat) {
				g.setFont(font);
				g.drawImage(backgroundImg,0,0, null);
				g.drawString("WINNER: "+winner, 10, 50);
				g.drawString("LOSER: "+loser, 550, 50);
			}
		}
	}
}