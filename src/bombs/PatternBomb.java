package bombs;

import fires.FireExplosion;
import fires.FireProjectil;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import characters.Bomber;
import characters.BomberMonster;
import characters.MasterBomber;

import scene.Wall;
import supports.Const;
import supports.Position;


public class PatternBomb implements Bomb {//Essa Vai ser a bomba de refêrncia para as outras, as outras vão herdar dela
	
	//Atributos 
	protected int fireLevel;
	protected String craft = "..\\masterImages\\bomb1.png";
	protected Image image;
	protected boolean visible, dontExplosion, underBomber;
	protected int timer;
	protected boolean wasKicked;//foi chutada
	protected int direction = Const.STOPED;
	protected Position position;
		
	public PatternBomb(int x, int y, int fireLevel) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		
		this.fireLevel = fireLevel;
		position = new Position(x, y);
		visible = true;
		dontExplosion = true;
		underBomber = true;
		timer = 200;
	}
	
	
	
	public int getX() {
		return position.getX();
	}
	public int getY() {
		return position.getY();
	}
	public int getDirection() {
		return direction;
	}
	
	public Image getImage() {
		return image;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isDontExplosion() {
		return dontExplosion;
	}
	
	public void dontPass() {
		position.setX( relativeX() );
		position.setY( relativeY() );
		
		setDirection(Const.STOPED);
	}
	
	public void action() {
		if(direction != Const.STOPED) rolling();
		if(dontExplosion) {
			if(timer == 0) {
				dontExplosion = false;
			}
			else timer--;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(position.getX(), position.getY(), Const.TAM_WALL, Const.TAM_WALL);
	}

	public void setUnderBomber(boolean b) {
		underBomber = b;
	}

	public boolean isUnderMasterBomber() {
		return underBomber;
	}

	public void rolling() {
		int BOMB_SPEED = 2;
		if(direction == Const.UP) 			position.setY(position.getY() - BOMB_SPEED);
		if(direction == Const.LEFT) 		position.setX(position.getX() - BOMB_SPEED);
		if(direction == Const.DOWN) 	position.setY(position.getY() + BOMB_SPEED);
		if(direction == Const.RIGHT) 	position.setX(position.getX() + BOMB_SPEED);
		
		if(position.getX() < Const.LIM_MAPX_LEFT)  dontPass();
		if(position.getX() > (Const.LIM_MAPX_RIGHT - Const.TAM_WALL))  dontPass();
		if(position.getY() < Const.LIM_MAPY_UP)  dontPass();
		if(position.getY() > Const.LIM_MAPY_DOWN - Const.TAM_WALL)  dontPass();
	}

	public void roll(int direction) {
		this.direction = direction;
	}
	
	public FireExplosion getFireExplosion() {
		return new FireExplosion(relativeX(), relativeY(), fireLevel);
	}
	
	@Override
	public void setAll(int x, int y, int fireLevel) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		direction = Const.STOPED;
		this.fireLevel = fireLevel;
		position = new Position(x, y);
		visible = true;
		dontExplosion = true;
		underBomber = true;
		timer = Const.INITIALIZE_BOMB_TIMER;
	}
	
	@Override
	public Bomb getBomb() {
		return this;
	}
	
	@Override
	public void putImage(String craft) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
	}
	
	@Override
	public void timerOver() {
		timer = Const.OVER;
	}
	
	public int relativeX() {
		int rx = (((getX()-90)/Const.TAM_WALL)*Const.TAM_WALL)+Const.LIM_MAPX_LEFT;
		return rx;
	}
	public int relativeY() {
		int ry = (((getY()+30)/Const.TAM_WALL)*Const.TAM_WALL);
		return ry;
	}
	
	@Override
	public <K> void collisionAction(K object) {
		if(object instanceof BomberMonster) {
			if(getDirection() != Const.STOPED) {
				dontPass();
			}
		}
		else if(object instanceof FireProjectil) {
			timerOver();
		}
		else if(object instanceof Wall) {
			if(getDirection() != Const.STOPED) {
				dontPass();
			}
		}
	}
	
}