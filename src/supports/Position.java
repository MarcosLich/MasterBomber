package supports;

public class Position {
	//Atributos
	int x, y;
	
	//Construtores
	public Position() {
		x = 100;
		y = 0;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Get's
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	//Set's
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void addX(int i) {
		x += i;
	}

	public void addY(int i) {
		y += i;
	}
}
