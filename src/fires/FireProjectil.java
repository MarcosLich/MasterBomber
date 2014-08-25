package fires;


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import bombs.Bomb;

import scene.FixedWall;
import scene.Item;
import supports.Const;
import supports.ToCollision;

public class FireProjectil extends PatternFire{
	//Atributos Booleanos
	private boolean haveFireTail;
	//Atributos inteiros
	int xAnt, yAnt, aux = Const.STOPED;
	int FIRE_SPEED = Const.TAM_WALL;
	int fireLevel;
	int delayAux = 3, delay = delayAux;
	
	//Construtor
	public FireProjectil(int x, int y, int fireLevel, int direction) {
		if(direction == Const.UP) 			craft = "..\\masterImages\\explosaoCruzNF.png";
		if(direction == Const.LEFT) 		craft = "..\\masterImages\\explosaoCruzOF.png";
		if(direction == Const.DOWN) 	craft = "..\\masterImages\\explosaoCruzSF.png";
		if(direction == Const.RIGHT) 	craft = "..\\masterImages\\explosaoCruzLF.png";
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		
		this.fireLevel = fireLevel;
		this.direction = direction;
		position.setX(x);
		position.setY(y);
		//booleanos
		visible = true;
		haveFireTail = false;
	}
	
	//Get's
	public boolean haveFireTail() {
		return haveFireTail;
	}
	
	//Get's Outros
	public FireTail getFire() {
		return new FireTail(xAnt, yAnt, direction);
	}
	
	//Set's Booleanos
	public void setHaveFireTail(boolean b){
		haveFireTail = b;
	}
	
	//Complexo
	public boolean move() {
		delay = Const.INITIALIZE_DELAY;
		while(delay-- < 0) ;
		
		xAnt = position.getX();
		yAnt = position.getY();
		
		if(aux < fireLevel) {
			if(direction == Const.UP) {
				position.addY(- FIRE_SPEED); 
			}
			else if(direction == Const.LEFT)	{
				position.addX(- FIRE_SPEED);
			}
			else if(direction == Const.DOWN)  {
				position.addY( FIRE_SPEED);
			}
			else if(direction == Const.RIGHT)  {
				position.addX( FIRE_SPEED);
			}
			aux++;
			delay = delayAux;
		} else {
			delay--;
			if(timer == Const.OVER) setVisible(false);
		}
		haveFireTail = true;
		
		return true;
	}
	
	//Esse método retorna um retangulo com um pixel a mais na direcão especifica 
	public Rectangle getBoundsPass() {
		int pass = -1;
		if(direction == Const.UP) 			return new Rectangle(position.getX(), position.getY()-pass, Const.TAM_WALL, Const.TAM_WALL);
		if(direction == Const.LEFT) 		return new Rectangle(position.getX()-pass, position.getY(), Const.TAM_WALL, Const.TAM_WALL);
		if(direction == Const.DOWN) 	return new Rectangle(position.getX(), position.getY(), Const.TAM_WALL, Const.TAM_WALL+pass);
		if(direction == Const.RIGHT) 	return new Rectangle(position.getX(), position.getY(), Const.TAM_WALL+pass, Const.TAM_WALL);
		return null;
	}
	
	@Override
	public <K> void collisionAction(K object) {
		if(object instanceof FixedWall || object instanceof Bomb) {
			setVisible(false);
		}
	}
	
}