package scene;


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import characters.MasterBomber;
import fires.FireProjectil;

import supports.Const;
import supports.Position;
import supports.ToMove;

public class Item implements ToMove{
	//Atributos Booleanos
	private boolean broken;
	private boolean visible;
	//Atributos Inteiros
	private int timer;
	private int ID;
	//Atributos Outros
	private String craft="";
	private Image image=null;
	private Position position;
	
	
	//Construtor
	public Item(int x, int y, int ID) {
		if(ID == Const.SPEED) 				craft = "..\\masterImages\\itemPatins.png";
		if(ID == Const.BOMB) 				craft = "..\\masterImages\\itemBomb.png";
		if(ID == Const.FIRE) 				craft = "..\\masterImages\\itemFire.png";
		if(ID == Const.LIVE) 				craft = "..\\masterImages\\itemVida.png";
		if(ID == Const.KICK_BOMB) 		craft = "..\\masterImages\\itemChuteBomba.png";
		if(ID == Const.WALL_PASS) 	craft = "..\\masterImages\\itemPasseParede.png";
		if(ID == Const.LIFE_JACKET) 	craft = "..\\masterImages\\colete.png";
		if(ID == Const.DROP_BOMB) 	craft = "..\\masterImages\\itemBombGota.png";
		
		putImage();
		position = new Position(x, y);
		
		//Inteiros
		this.ID = ID;
		timer = Const.INITIALIZE_TIME;
		
		//Booleanos
		visible = true;
		broken = false;
	}

	//Get's
	//Get's Booleanos
	public boolean isVisible() {
		return visible;
	}
	public boolean isQuebrado() {
		return broken;
	}
	
	//Get's Inteiros
	public int getX() {
		return position.getX();
	}
	public int getY() {
		return position.getY();
	}
	public int getID() {
		return ID;
	}
	
	//Get's Outros
	public Rectangle getBounds() {
		return (new Rectangle(position.getX(), position.getY(), Const.TAM_WALL, Const.TAM_WALL));
	}
	public Image getImage() {
		return image;
	}
	
	
	//Set's
	//Set's Booleanos
	public void setBroken(boolean broken) {
		this.broken = broken;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//Set's Complexos
	private void putImage() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
	}	
	//O iten não se move mas tem uma pequena "animação" se destruído
	public boolean move() {
		if(broken == true) {
			craft ="..\\masterImages\\itemDestruido.png";
			putImage();
			timer--; 
			if(timer == Const.STOPED) setVisible(false); 
		}
		return visible;
	}

	@Override
	public <K> void collisionAction(K object) {
		if(object instanceof MasterBomber) {
			setVisible(false);
		}
		else if(object instanceof FireProjectil) {
			setBroken(true);
		}
	}	
}