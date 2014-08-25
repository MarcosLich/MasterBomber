package bombs;

import fires.FireExplosion;

import java.awt.Image;
import java.awt.Rectangle;

import supports.ToCollision;

public interface Bomb extends ToCollision{
	
	//Get's
	//Get's Booleanos
	public boolean isUnderMasterBomber();
	public boolean isVisible();
	public boolean isDontExplosion();
	
	//Get's Inteiros
	public abstract int getX();
	public abstract int getY();
	public int getDirection();
	
	//Get's Outros
	public Image getImage();
	public Bomb getBomb();
	public Rectangle getBounds();
	public FireExplosion getFireExplosion();
	
	
	//Set's 
	//Set's Booleanos
	public void setVisible(boolean visible);
	public void setUnderBomber(boolean b);
	
	//Set's Inteiros
	public void setDirection(int direction);
	
	//Set's Outros
	public void setAll(int x, int y, int fireLevel);
	public void action();
	public void dontPass();
	public void rolling();
	public void roll(int direction);
	public void putImage(String craft);
	public void timerOver();
}