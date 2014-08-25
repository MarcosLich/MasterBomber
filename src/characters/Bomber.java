package characters;


import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import supports.ToMove;

import bombs.Bomb;

public interface Bomber extends ToMove {
	//Get's 
	
	//Get's Booleanos
	boolean isVisible();
	//boolean move();
	void die();
	boolean isLive();
	boolean isOnBomb();
	
	//Get's Inteiros
	int getX();
	int getY();
	int getDirection();
	int getKeyImage();
	//Get's Outros
	Rectangle getBounds();
	Image getImage();
	//Rectangle relativeGetBounds();
	
	//Set's 
	
	//Set's Boleanos
	void setVisible(boolean visible);
	void setOnBomb(boolean b);
	
	//Set's Inteiros
	void putImage(String craft);
	
	//Set's Outros
	void dontPass();
	
}