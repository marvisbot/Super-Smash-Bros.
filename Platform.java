import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

public class Platform { //extends JPanel
	private int x, y;
	private int width, height;
	boolean specialPlat;
	public int getPlatX() {
		return x;
	}
	public int getPlatY() {
		return y;
	}
	public int getPlatWidth() {
		return width;
	}
	public int getPlatHeight() {
		return height;
	}
	public Platform(int x, int y, int width, int height, boolean special) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.specialPlat = special;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	public void draw(Graphics g) {
		Graphics2D gg = (Graphics2D) g;		
		gg.fillRect(x, y, width, height);
	}

}