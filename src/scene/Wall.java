package scene;


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import supports.ToMove;

public interface Wall extends ToMove {
	//Get's
	//Get Booleano
	boolean isVisible();
	
	//Get's inteiros
	int getX();
	int getY();
	
	//Get's Outros
	Rectangle getBounds();
	Image getImage();
	
	//Set
	void setVisible(boolean visible);
	
	//Complexo
	void putImage(String craft);
}