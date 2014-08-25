package characters;


import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import fires.FireTail;

import memento.CaretakerMasterBomber;
import memento.MementoMasterBomber;

import scene.Item;
import scene.MobileWall;
import scene.Wall;
import supports.Const;
import supports.Position;
import supports.ToCollision;

import bombs.Bomb;
import bombs.DropBomb;
import bombs.PatternBomb;

public class MasterBomber extends PatternBomber implements ToCollision{
	
	//Atributos Booleanos
	private boolean start, token = false, onBomb; 
	private boolean invincible, bombKick, wallPass;
	
	//Atributos Inteiros
	private int numBombs, fireLevel;
	private int bombType;
	private int CRAFT_SIZE = Const.TAM_WALL;
	private int dx, dy, delay = Const.INITIALIZE_DELAY, timer = Const.INVINCIBLE_TIME;
	
	//Atributos Outros
	private ArrayList<Bomb> bombs;
	protected int lives;
	
	
	//Construtor
	public MasterBomber(int x, int y) {
		//Atributos referentes a posição e imagem
		this.ID = ID;
		this.craft = "..\\masterImages\\b03.png";
		putImage(craft);
		position = new Position(x, y);
		dx = dy = Const.STOPED;
		
		//Características específicas
		this.lives = Const.INITIALIZE_LIVE;
		speed = Const.INITIALIZE_SPEED;
		numBombs = Const.INITIALIZE_NUM_BOMB;
		fireLevel = Const.INITIALIZE_FIRE_LEVEL;
		bombs = new ArrayList<Bomb>();//É como o bomberman sabe onde e quantas bombas colocou no mapa;
		bombType = Const.INITIALIZE_TYPE_BOMB;
		
		//Atributos Booleanos
		onBomb = false;
		this.visible = true;
		this.live = true;
		this.start = false;
		this.invincible = true;
		this.bombKick = false;
		this.wallPass = false;
	}
	
	
	//Get's
	
	//Get's Booleanos
	public boolean getInvincible() {
		return invincible;
	}
	public boolean isLive() {
		return live;
	}
	public boolean haveWallPass() {
		return wallPass;
	}
	public boolean haveBombKick() {
		return bombKick;
	}
	public boolean isStart() {
		return start;
	}
	public boolean isOnBomb() {
		return onBomb;
	}
	
	//Get's Inteiros
	public int getVelocidade() {
		return speed;
	}
	public int getNumBombs() {
		return numBombs;
	}
	public int getFireNivel() {
		return fireLevel;
	}
	public int getLives() {
		return lives;
	}
	
	//Get's Outros
	public ArrayList<Bomb> getBombs() {
		return bombs;
	}
	
	//Get's Complexos
	
	//Retorna o tipo da bomba a ser adicionada na lista no método "putBomb()"...
	public Bomb getBombType() {
		if(bombType == 1) return new DropBomb(relativeX(), relativeY(), fireLevel);
		return new PatternBomb(relativeX(), relativeY(), fireLevel);
	}
	//Ver se die não entra em move()...
	public void die() {
		if(!invincible) {
			craft = "..\\masterImages\\dead.png";
			putImage(craft);
			live = false;
			invincible = true;
			delay = 100;
		}
	}
	//Retorna a posição para o qual o bomberman está se movendo, este método é apenas usado para Chutar a Bomba.
	public int getDirection() {
		if(dy < 0) 			return Const.UP;
		else if(dx < 0) 	return Const.LEFT;
		else if(dy > 0) 	return Const.DOWN;
		else if(dx > 0) 	return Const.RIGHT;
		return Const.STOPED;
	}
	
	//Set's 
	//Set's Boleanos
	public void setOnBomb(boolean onBomb) {
		this.onBomb = onBomb;
	}
	private void setInvincible(boolean b) {
		invincible = b;
	}
	private void setWallPass(boolean wallPass) {
		this.wallPass = wallPass;
	}
	private void setBombKick(boolean bombKick) {
		this.bombKick = bombKick;
	}
	
	
	//Set's Complexos
	public MementoMasterBomber getMemento() {
		MasterBomber mb = new MasterBomber(100, 0);
		mb.numBombs = this.numBombs;
		mb.speed = this.speed;
		mb.fireLevel = this.fireLevel;
		mb.lives = this.lives;
		return new MementoMasterBomber( mb );
		
	}
	
	private boolean pressSTART() {
		if(start == false) start = true;
		else start = false;
		return start;
	}
	
	//Funções Ligadas a movimento
	//Upgrade o bomberman ganha novas habilidades	
	public void upgrade(int item) {
		if(item == Const.SPEED && speed < Const.MAX_LEVEL) speed++;
		if(item == Const.BOMB && numBombs < Const.MAX_LEVEL) numBombs++;
		if(item == Const.FIRE && fireLevel < Const.MAX_LEVEL) fireLevel++;
		if(item == Const.LIVE) lives++;
		if(item == Const.KICK_BOMB) setBombKick(true);
		if(item == Const.WALL_PASS) setWallPass(true);
		if(item == Const.LIFE_JACKET) {
			setInvincible(true);
			timer = Const.INVINCIBLE_TIME;
		}
		if(item == Const.DROP_BOMB) bombType = 1;
	}
	//Esse método é chamado para que o bomberman "Ande".
	public boolean move() {
		if(isLive()) {
			
			//Nesse trecho ocorre a troca de imagem do bomber com "..\\masterImages\\bomberNull.png" para criar o efeito de piscar
			if(invincible == true && token) {
				putImage("..\\masterImages\\bomberNull.png");
				token = false;
			}
			else {
				putImage(craft);
				token = true;
			}

			if(timer-- < 0) invincible = false;

			while(delay-- > Const.STOPED) ;
			
			//Esta parte do código faz a colisão com os limites da tela
			position.setX( position.getX() + dx );
			position.setY( position.getY() + dy );

			int MINX = Const.LIM_MAPX_LEFT;
			int MINY = Const.LIM_MAPY_UP; 
			int MAXX = 740;
			int MAXY = 640;

			if(position.getX() < MINX)  position.setX( MINX );
			if(position.getX() > MAXX)  position.setX( MAXX );
			if(position.getY() < MINY)  position.setY( MINY );
			if(position.getY() > MAXY)  position.setY( MAXY );

			delay = Const.BIG_TIME_DELAY;
			return true;
		}
		else {
			if(delay > Const.STOPED)  {
				delay--;
				return false;
			}
			else if(lives > 0) {
				lives--;
				putImage("..\\masterImages\\bomberNull.png");
				position.setX(Const.LIM_MAPX_LEFT);
				position.setY(Const.LIM_MAPY_UP);
				live = true;
				craft = "..\\masterImages\\b03.png";
				putImage(craft);
				timer = Const.INVINCIBLE_TIME;
				return false;
			}


			setVisible(false);
			return false;
		}
	}
	//Método que adiciona uma bomba a lista de bombas
	public boolean putBomb() {

		if(bombs.size() >= numBombs) {
			return false;
		}
		if(bombs.size() == 0) {
			bombs.add(getBombType());
			setOnBomb(true);
		}
		else if(bombs.size() <= numBombs){
			if(isOnBomb() == false) bombs.add(getBombType());
		}
		return true;
	}
	//Eventos Quando o botão é pressionado
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_P) {
			pressSTART();
		}
		
		if (key == KeyEvent.VK_SPACE) {
			putBomb();
		}

		if (key == KeyEvent.VK_LEFT) {
			dx = -speed;
			craft = "..\\masterImages\\b33.png";
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = speed;
			craft = "..\\masterImages\\b32.png";
		}

		if (key == KeyEvent.VK_UP) {
			dy = -speed;
			craft = "..\\masterImages\\b31.png";
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = speed;
			craft = "..\\masterImages\\b03.png";
		}
		putImage(craft);
		
		
	}
	//Eventos quando o botão é liberado
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			dx = Const.STOPED;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = Const.STOPED;
		}

		if (key == KeyEvent.VK_UP) {
			dy = Const.STOPED;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = Const.STOPED;
		}
		putImage(craft);
	}


	public void setVidas(int i) {
		lives = i;
	}


	@Override
	public <K> void collisionAction(K object) {
		//MasterBomber X FireTail
		
		if(object instanceof BomberMonster) {
			if(isVisible()) die();
		}
		if(object instanceof FireTail || object instanceof BomberMonster) {
			die();
		}
		//MasterBomber X MobileWall
		else if(object instanceof Wall ) {
			if(haveWallPass() && object instanceof MobileWall) /*Nothing*/;
			else dontPass();
		}
		else if(object instanceof Item) {
			Item item = (Item) object;
			upgrade(item.getID());	
		}
	}
	
	public void setStart(boolean b) {
		start = b;
	}
}