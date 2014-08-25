package supports;

import java.util.ArrayList;


import fires.FireExplosion;
import fires.FireProjectil;
import fires.FireTail;

import bombs.Bomb;

public class Action {
	private int i;
	
	//Função Generics
	public <E extends ToMove> void move(ArrayList<E> array) {//E é o elemento da lista
		for (i = 0; i < array.size(); i++) {
			E e = array.get(i);
			if (e.isVisible()) {
				e.move();
			}
			else array.remove(i);
		}
	}
	
	public void moveBombs(ArrayList<Bomb> bs, ArrayList firesE, ArrayList<FireProjectil> fires) {
		for (i = 0; i < bs.size(); i++) {
			Bomb b =  bs.get(i);
			if (b.isVisible()) {
				b.action();
				if(!b.isDontExplosion()) {
					FireExplosion fe = (FireExplosion) b.getFireExplosion();
					firesE.add(fe);
					fires.addAll(fe.getFires());
					b.setVisible(false);
				}
			}
			else {
				bs.remove(i);
			}
		}
	}

	public void moveFires(ArrayList<FireProjectil> fires, ArrayList<FireTail> fireTails) {
		for (i = 0; i < fires.size(); i++) {
			FireProjectil f = fires.get(i);
			if (f.isVisible() == true) {
				f.move();
				if(f.haveFireTail()) {
					fireTails.add(f.getFire());
					f.setHaveFireTail(false);
				}
			}
			else {
				fires.remove(i);
			}
		}
	}
}
