package fires;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import supports.Const;
import supports.ToCollision;
import supports.ToMove;

public interface Fire extends ToMove{
	//Get Booleno
	public boolean isVisible();
	
	//Get's Inteiro
	public int getX();
	public int getY();
	
	//Get's Outros
	public Image getImage();
	public Rectangle getBounds();
	
	//Set's
	public void setVisible(boolean visible);
	
	//Complexo
	public boolean move();
}
