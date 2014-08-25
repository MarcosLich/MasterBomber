package scene;

import supports.Const;
import supports.Position;

public class MobileWall extends PatternWall{
	//Atributos
	private boolean broken;
	private int timer;
	private Item item;
	
	//Construtor
	public MobileWall(int x, int y, String craft) {
		this.craft = craft;
		putImage(craft);
		position = new Position(x, y);
		//Inteiros
		timer = Const.INITIALIZE_TIME;
		this.item = null;
		//Booleanos
		visible = true;
		broken = false;
	}
	
	//Get's
	public boolean isBroken() {
		return broken;
	}
	public Item getItem() {
		return item;
	}
	
	//Set's
	public void setBroken(boolean broken) {
		this.broken = broken;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	//Complexo
	public boolean move() {
		if(broken == true) {
			putImage("..\\masterImages\\blocoDestruido.png");
			timer--; 
			if(timer == 0) setVisible(false); 
		}
		return visible;
	}
}
