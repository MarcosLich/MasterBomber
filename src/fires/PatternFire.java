package fires;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import supports.Const;
import supports.Position;

public class PatternFire implements Fire{
	boolean visible;
	int timer;
	int x, y;
	int direction;
	protected String craft = "";
	Image image;
	Position position = new Position();
	
	
	@Override
	public boolean isVisible() {
		return visible;
	}
	
	@Override
	public int getX() {
		return position.getX();
	}
	
	@Override
	public int getY() {
		return position.getY();
	}
	
	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), Const.TAM_WALL, Const.TAM_WALL);
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public boolean move() {
		return false;
	}
	
	@Override
	public <K> void collisionAction(K object) {
	}
}
