package fires;


import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import supports.Const;
import supports.ToCollision;
import supports.ToMove;

public class FireExplosion extends PatternFire{
	
	int fireNivel;
	ArrayList<FireProjectil> fires = new ArrayList<FireProjectil>();
	
	//Construtor
	public FireExplosion(int x, int y, int fireLevel) {
		fires.add(new FireProjectil(x, y, fireLevel, Const.LEFT));
		fires.add(new FireProjectil(x, y, fireLevel, Const.RIGHT));
		fires.add(new FireProjectil(x, y, fireLevel, Const.UP));
		fires.add(new FireProjectil(x, y, fireLevel, Const.DOWN));
		
		craft = "..\\masterImages\\explosaoCruz.png";
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		
		//Adiciona os projeteis que avaçam nas 4 direções 
		
		this.fireNivel = fireNivel;
		position.setX(x);
		position.setY(y);
		this.direction = direction;
		timer = Const.INITIALIZE_TIME;
		visible = true;
	}
	
	
	//Get's
	
	//Get Booleno
	
	//Get's Outros
	
	public ArrayList<FireProjectil> getFires() {
		return fires;
	}
	
	//Set's
	
	//Complexo
	public boolean move() {
		timer--;
		if(timer == Const.OVER) { 
			setVisible(false);
			return false;
		}
		return true;
	}
	
	@Override
	public <K> void collisionAction(K object) {}
}
