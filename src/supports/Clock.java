package supports;

import java.util.ArrayList;

//Esse Programa é um contador com a ideia de um relógio.
public class Clock {
	//Atributos
	private int miliSeg, seg, indice;
	
	//Construtor
	public Clock(int miliSeg) {
		this.miliSeg = miliSeg*3600;
	}
	
	//Get's
	public int getMiliSegTotal() {
		return miliSeg;
	}
	public int getMiliSegundos() {
		return (miliSeg%60);
	}
	public int getSegundos() {
		return (miliSeg/60);
	}
	public int getMinutos() {
		return miliSeg/3600;
	}
	
	
	//Set's
	public void addMiliSeg(int add) {
		this.miliSeg += add;
		if(miliSeg == 86400) miliSeg =0;
	}
	public void setMiliSeg(int miliSeg) {
		this.miliSeg = this.miliSeg + miliSeg;
	}
	
	
	//Outros
	@Override
	public String toString() {
		return "RELÓGIO--> " + getMinutos() + " : " + (getSegundos()%60);
	}	
	void delay() {
		int DELAY = 999999999;
		int DELAY2 = 1;
		for (int i = 0; i < DELAY2; i++) 
			for (int j = 0; j < DELAY;j++) ;
	}
}