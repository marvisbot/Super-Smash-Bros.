import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ladder  {
	
	private int x, y, width, height;
	
	public int getLadX() {
		return x;
	}
	public int getLadY() {
		return y;
	}
	public int getLadWidth() {
		return width;
	}
	public int getLadHeight() {
		return height;
	}
	public Ladder(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	public void draw(Graphics g) {
		Graphics2D gg = (Graphics2D) g;		
		gg.fillRect(this.x, this.y, this.width, this.height);
		System.out.println(x + " "+y+" "+width+" "+height);
	}
	
	
}
