package characters;


import java.awt.Image;
import java.awt.Rectangle;

import bombs.Bomb;

import fires.FireTail;
import flyweight.FactoryMonsterFlyweight;


import scene.Wall;
import supports.Const;
import supports.Position;


public class BomberMonster extends PatternBomber {
	
	//Atributos Booleanos
	private boolean onTheBomb;
	//Atributos Inteiros
	private int direction;
	private int delay= Const.BIG_TIME_DELAY;
	
	private int statusImage;
	
	//Construtor
	public BomberMonster(int x, int y) {
		statusImage = Const.DOWN;
		position = new Position(x, y);
		
		//Inteiros
		direction  = (int) (1 + Math.random() * 4);//Isso gera um número entre 1 e 4);
		speed = 1;
		//Booleanos
		this.visible = true;
		live = true;
		onTheBomb = false;
	}
	
	
	//Get's
	@Override
	public int getKeyImage() {
		return statusImage;
	}
	
	//Set's
	
	//Complexos
	//Muda a Direção Aleatotamente
	public void changeDirection() {
		this.direction  = (int) (1 + Math.random() * 4);
		statusImage = direction;
	}
	
	@Override
	public void dontPass() {
		super.dontPass();
		
		changeDirection();//Muda a Direção se não conseguir passar 
	}
	
	
	//Método de movimento do monstro
	@Override
	public boolean move() {
		//Rotina da morte do monstro
		if(isLive() == false) {
			if(delay-- > 0)  {
				return false;
			}
			else {
				setVisible(false);
				return false;
			}
		}
		
		while(delay-- > 0) ;
		
		if(direction == Const.UP) {
			position.setY(position.getY() - speed);
			statusImage = Const.UP;
		}
		if(direction == Const.LEFT) {
			position.setX(position.getX() - speed);
			statusImage = Const.LEFT;
		}
		if(direction == Const.DOWN) {
			position.setY(position.getY() + speed);
			statusImage = Const.DOWN;
		}
		if(direction == Const.RIGHT) {
			position.setX(position.getX() + speed);
			statusImage = Const.RIGHT;
		}
		
		//Esse trecho do código faz a colisão com os limites da tela
		int MINX = Const.LIM_MAPX_LEFT;
		int MINY = Const.LIM_MAPY_UP; 
		int MAXX = 740;
		int MAXY = 640;
		
		if(position.getX() < MINX)  {
			position.setX( MINX );
			changeDirection();
		}
		if(position.getX() > MAXX) {
			position.setX( MAXX );
			changeDirection();
		}
		if(position.getY() < MINY)  {
			position.setY( MINY );
			changeDirection();
		}
		if(position.getY() > MAXY)  {
			position.setY( MAXY );
			changeDirection();
		}
		
		delay = Const.INITIALIZE_DELAY;
		return true;
	}
	
	//Muda o estado para o move() mostra a morte do monstro
	public void die(){
		live = false;
		statusImage = Const.DEAD;
		delay = Const.INITIALIZE_DELAY;
	}
	
	@Override
	public <K> void collisionAction(K object) {
		
		if(object instanceof FireTail) {
			die();
		}
		else if(object instanceof Bomb) {//Isso está bugado porque o monstro fica preso se a bomba for colocada em baixo dele
			dontPass();
		}
		else if(object instanceof Wall) {
			dontPass();
		}
	}
}
