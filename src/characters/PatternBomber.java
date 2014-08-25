package characters;


import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;

import bombs.Bomb;
import supports.Const;
import supports.Position;


public class PatternBomber implements Bomber{
	//Atributo Booleno
	protected boolean visible, live;
	//Atributos Inteiros
	protected int ID, speed;
	//Atributos Outros
	protected String craft = "";
	protected Position position;
	protected Image image;
	
	
	//Get's Booleanos
	@Override
	public boolean isVisible() {
		return visible;
	}
	@Override
	public boolean move() {
		return visible;
	}
	@Override
	public void die() {
	}
	@Override
	public boolean isOnBomb() {
		return false;
	}
	@Override
	public boolean isLive() {
		return live;
	}
	
	//Get's Inteiros
	@Override
	public int getX() {
		return position.getX();
	}
	@Override
	public int getY() {
		return position.getY();
	}
	@Override
	public int getDirection() {
		return 0;
	}
	@Override
	public int getKeyImage() {
		return 0;
	}
	//Get's Outros
	@Override
	public Rectangle getBounds() {
		return new Rectangle(position.getX(), position.getY(), Const.TAM_WALL, Const.TAM_WALL);
	}
	@Override
	public Image getImage() {
		return image;
	}
	public ArrayList<Bomb> getBombs() {
		return null;
	}
	public Rectangle relativeGetBounds() {
		return new Rectangle(relativeX(), relativeY(), Const.TAM_WALL, Const.TAM_WALL);
	}
	
	
	//Set's
	
	//Set's Booleanos
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	
	@Override
	public void setOnBomb(boolean b) {
	}
	
	
	//Complexos
	@Override
	public void putImage(String craft) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
	}
	@Override
	//Ajusta a posição do bomberman de forma a ele não colidir com as paredes fixas
	public void dontPass() {
		position.setX( relativeX() );
		position.setY( relativeY() ); 
	}	
	
	//Essas funções retornam a posição relativa para passar entre duas Paredes Fixas, atualmente se estiver 25% na proxima ksa, ele está na outra casa :)
	protected int relativeX() {
		int rx = (((position.getX() - 90)/Const.TAM_WALL)*Const.TAM_WALL)+Const.LIM_MAPX_LEFT;
		return rx;
	}
	protected int relativeY() {
		int ry = (((position.getY() + 30)/Const.TAM_WALL)*Const.TAM_WALL);
		return ry;
	}
	@Override
	public <K> void collisionAction(K object) {
		// TODO Auto-generated method stub
	}
	
}