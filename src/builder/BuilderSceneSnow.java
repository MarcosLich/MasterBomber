package builder;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import scene.FixedWall;
import scene.Item;
import scene.MobileWall;
import scene.Scene;
import scene.Wall;
import supports.Const;
import characters.Bomber;
import characters.BomberMonster;
import characters.MasterBomber;

public class BuilderSceneSnow implements BuilderScene{
	private String path = "..\\masterImages\\backGroundSnow.png";
	private String craftFixedWall = "..\\masterImages\\blocoFixoNeve.png";
	private String craftMobileWall = "..\\masterImages\\blocoQuebravelNeve.png";
	
	private final int XMAP = ( Const.LIM_MAPX_LEFT + Const.TAM_WALL ), YMAP = Const.TAM_WALL;//{XMAP, YMAP}
	private int x, y, i = 0, j = 0;
	private boolean token = false;
	
	//Inicializa o fundo
	public void initFloor(Scene scene) {
		
        ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		scene.setFloor(ii.getImage());
	}
	
	//Inicializa Cenário
	@Override
	public void initFixedWalls(Scene scene) {
		//Adiciona as paredes fixas
		for(i = 0; i < 8 ; i++) {
			y = (YMAP+i*(Const.TAM_WALL + Const.TAM_WALL));
			for (j = 0; j < 8; j++) {
				x = (XMAP+j*(2 * Const.TAM_WALL));
				scene.addWall(new  FixedWall(x, y, craftFixedWall));
			}
		}
	}
	
	//Adiciona as Paredes Móveis, não ocupando posições iniciais dos bombermans
	@Override
	public void initMobileWalls(Scene scene, ArrayList<Bomber> bombers) {
		x = y = 0;
		int AllWalls = Const.NUM_MOBILE_WALLS;
		
		//Adiciona as Paredes Móveis baseado no número de bombermans 
		for(int i = 0; i < Const.NUM_BLOCKS_IN_COLUMN; i++) {//Const.NUM_ALL_WALLS
			y = Const.LIM_MAPY_UP + (i*Const.TAM_WALL);
			for (int j = 0; j < Const.NUM_BLOCKS_IN_LINE; j++) {
				x = Const.LIM_MAPX_LEFT + ( j * Const.TAM_WALL );
				boolean passed = true;
				if((x == 100 && y == 0) || (x == 100 && y == 40) || (x == 140 && y == 0)) {//Superior Esquerda
					passed = false;
					break;
				}
				if((x-140)%80 == 0 && (y-40)%80 == 0) {//Se não está na posição de um bloco fixo
					passed = false;
				}
				//Se passou pelas verificacao
				if(passed) {
					int n = (int) (Math.random()*101);//random de 0 a 100
					if(n < 40) {//40% de chance de pör um bloco
						scene.addWall(new  MobileWall(x, y, craftMobileWall));
					}
				}
			}//2nd FOR
		}//1st FOR
	}
	
	//Adiciona as Monsters, não ocupando posições iniciais dos bombermans
	public void initMonsters(Scene scene, ArrayList<Bomber> bombers) {
		int numMonster = 0;
		//Por os monstros de forma a não ficarem presos
		
		do {
			x = (((int) (Math.random()*Const.NUM_ALL_WALLS)) * 40) +100;
			y = ((int) (Math.random()*Const.NUM_ALL_WALLS)) * 40;
			
			
			
			boolean free = true;
			for (int j = 0; j < scene.getWalls().size(); j++) {
				//Verificações para não pôr blocos nas posições onde tem bombermans//Retirar para n ser código repetido
				if((x == 100 && y == 0) || (x == 100 && y == 40) || (x == 140 && y == 0)) {//Superior Esquerda
					free = false;
					break;
				}
				Wall bl = scene.getWall(j);
				int x2,  y2;
				x2 = bl.getX();
				y2 = bl.getY();
				if(x == x2 && y == y2) {
					free = false;
					break;
				}
			}//Fim do FOR
			if(free) {
				bombers.add(new BomberMonster(x, y));
				numMonster++;
			}
		}while(numMonster < Const.NUM_MONSTERS_TO_ADD);//Fim do Do/While
	}
	
	//Esse método põe itens nos blocos
	public void initItemList(Scene scene) {
		//Pôr itens
		int aux = 0;
		if((scene.getWalls().size() - Const.NUM_FIX_WALLS) > Const.NUM_ITEM_LIST_TO_ADD)
		while(aux < Const.NUM_ITEM_LIST_TO_ADD) {
			
			int chosenWall = ((int)(Math.random()*scene.getWalls().size()));//Parede escolhida
			
			boolean hasItem = false;//por hipotese n'ao tem item na parede
			
			if(chosenWall > 126 && scene.getWall(chosenWall) instanceof MobileWall) {//as paredes m[oveis estáo acima do indice 63
				
				MobileWall mw = (MobileWall) scene.getWall(chosenWall);
				if(mw.getItem() != null) {
					hasItem = true; 
				}
				else {
					int itemOption = ((int)(1+Math.random()*8));
					mw.setItem(new Item(mw.getX(), mw.getY(), itemOption));
				}
			}	
			if(hasItem == true) aux++;
		}
	}//FIM DE INIT BLOCOS !!!!!
}
