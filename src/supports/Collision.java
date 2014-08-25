package supports;

import java.awt.Rectangle;
import java.util.ArrayList;

import scene.Item;
import scene.MobileWall;
import scene.PatternWall;
import scene.Wall;


import bombs.Bomb;

import characters.Bomber;
import characters.MasterBomber;
import characters.BomberMonster;
import fires.FireExplosion;
import fires.FireProjectil;
import fires.FireTail;

//O objetivo dessa classe é realizar todas as colisões
public class Collision {
	private int i, j;
	
	//Codigo repetido 1
	public <M extends ToCollision, E extends ToCollision> void masterBomberXElement(M masterBomber, ArrayList<E> elementList) {
		for(j = 0; j < elementList.size(); j++) {
			E element = (E) elementList.get(j);
			Rectangle r3 = element.getBounds();//era f.getBoundsPass();
			Rectangle r7 = masterBomber.getBounds();//esse retangulo representa o bomberman// era masterBomber.relativeGetBounds();
			
			if(element.isVisible() && r7.intersects(r3)) {
				masterBomber.collisionAction(element);
				element.collisionAction(masterBomber);
			}
		}
	}
	
	//Codigo repetido 2
	public <M extends ToCollision, N extends ToCollision> void element1XElement2(ArrayList<M> element1List, ArrayList<N> element2List) {//Codigo repetido 1
		for(j = 0; j < element1List.size(); j++) {
			M element1 = element1List.get(j);
			Rectangle r3 = element1.getBounds();
			for (i = 0; i < element2List.size(); i++) {
				N element2 = element2List.get(i);
				Rectangle r7 = element2.getBounds();
				if(element2.isVisible() && r7.intersects(r3)) {
					element1.collisionAction(element2);
					element2.collisionAction(element1);
				}
			}
		}
	}
	
	//Codigo unico, ver se item list pode ser tirado e reaproveitar o generics
	public void fireXMobileWall(ArrayList<FireProjectil> fires, ArrayList<Wall> walls, ArrayList<Item> itemList) {//Unique
		for (i = 64; i < walls.size(); i++) {
			Wall bl = walls.get(i);
			Rectangle r2 = bl.getBounds();
				for (j = 0; j < fires.size(); j++) {
					FireProjectil f = (FireProjectil) fires.get(j);
					Rectangle r3 = f.getBoundsPass();
					
					if(f.isVisible() && r3.intersects(r2)) {//Faz algo
							f.setVisible(false); 
							MobileWall bl2 = (MobileWall) bl;
							if(bl2.getItem() != null) itemList.add(bl2.getItem());
							bl2.setBroken(true);
					}
				}
		}
	}
	
	//Master bomber X bomb
	public void masterBomberXBomb(MasterBomber masterBomber, ArrayList<Bomb> bombs) {//Unique
		boolean dontCross = true;
		Rectangle r7 = masterBomber.getBounds();
		for (i = 0; i < bombs.size(); i++) {
    		Bomb b = (Bomb) bombs.get(i);
			Rectangle r5 = b.getBounds();
			if(r7.intersects(r5) == false) {
				masterBomber.setOnBomb(false);
				b.setUnderBomber(false);
			}
			else if(r7.intersects(r5) == true) {
				if(masterBomber.isOnBomb() == true && b.isUnderMasterBomber() == false) {
					masterBomber.dontPass();
					if(b.getDirection() != Const.STOPED) b.dontPass();
					if(masterBomber.haveBombKick() && b.getDirection() == Const.STOPED) b.roll(masterBomber.getDirection());
				}
				else if(masterBomber.isOnBomb() == false && b.isUnderMasterBomber() == false) {
					masterBomber.dontPass();
					if(b.getDirection() != Const.STOPED) b.dontPass();
					if(masterBomber.haveBombKick() && b.getDirection() == Const.STOPED) b.roll(masterBomber.getDirection());
				}
			}
		}
	}
	
}
