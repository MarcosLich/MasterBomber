package bombs;

import supports.Const;

public class DropBomb extends PatternBomb{
	//Construtor
	public DropBomb(int x, int y, int fireLevel) {
		super(x, y, fireLevel);
		this.craft = "..\\masterImages\\bombGota.png";
		putImage(craft);
	}
	
	//Método local
	//Muda direção para a direção oposta
	public void changeDirection() {
		if(getDirection() == Const.UP) 					setDirection(Const.DOWN);
		else if(getDirection() == Const.LEFT) 		setDirection(Const.RIGHT);
		else if(getDirection() == Const.DOWN) 		setDirection(Const.UP);
		else if(getDirection() == Const.RIGHT) 		setDirection(Const.LEFT);
	}
	
	//Quando colide a bomba muda de direção
	@Override
	public void dontPass() {
		super.dontPass();
		
		changeDirection();
	}
}