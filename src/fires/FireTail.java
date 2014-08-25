package fires;


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import supports.Const;
import supports.ToCollision;
import supports.ToMove;

public class FireTail extends PatternFire{
	
	//Construtor
	public FireTail(int x, int y, int direction) {
		if(direction == Const.UP) 			craft = "..\\masterImages\\explosaoCruzN.png";
		if(direction == Const.LEFT) 		craft = "..\\masterImages\\explosaoCruzO.png";
		if(direction == Const.DOWN) 	craft = "..\\masterImages\\explosaoCruzS.png";
		if(direction == Const.RIGHT) 	craft = "..\\masterImages\\explosaoCruzL.png";
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		
		this.direction = direction;
		position.setX(x);
		position.setY(y);
		timer = Const.INITIALIZE_TIME;
		visible = true;
	}
	
	//Complexos
	@Override
	public boolean move() {
		timer--;
		if(timer == 0) setVisible(false);
		return visible;
	}
	
	@Override
	public <K> void collisionAction(K object) {}
}
