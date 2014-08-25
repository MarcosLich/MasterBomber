package scene;


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import supports.Position;
import supports.ToCollision;


public abstract class PatternWall implements Wall, ToCollision{
	//Atributos
	protected boolean visible;
	protected Position position;
	protected Image image;
	protected String craft;
	
	//Get's
	//Get Booleano
	@Override
	public boolean isVisible() {
		return visible;
	}
	
	//Get's Inteiros
	@Override
	public int getX() {
		return position.getX();
	}
	@Override
	public int getY() {
		return position.getY();
	}
	
	//Get's Outros
	@Override
	public Rectangle getBounds() {
		return new Rectangle(position.getX(), position.getY(), image.getWidth(null), image.getHeight(null));
	}
	@Override
	public Image getImage() {
		return image;
	}
	
	
	//Set
	@Override
	public void setVisible(boolean visible) {
	 	this.visible = visible;
	}
	
	//Complexo
	@Override
	public void putImage(String craft) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
	}

	@Override
	public boolean move() {
		return false;
	}

	@Override
	public <K> void collisionAction(K object) {
		
	}
}