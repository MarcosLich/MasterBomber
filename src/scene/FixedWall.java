package scene;


import javax.swing.ImageIcon;

import supports.Position;

public class FixedWall extends PatternWall{
	
	//Construtor
	public FixedWall(int x, int y, String craft) {
		this.craft = craft;
		putImage(craft);
		visible = true;
		position = new Position(x, y);
	}
}
