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

public class BuilderSceneArena implements BuilderScene{
	private String path = "..\\masterImages\\backGroundArena.png";
	private String craftFixedWall = "..\\masterImages\\blocoFixoArena.png";
	
	private final int XMAP = ( Const.LIM_MAPX_LEFT + Const.TAM_WALL ), YMAP = Const.TAM_WALL;//{XMAP, YMAP}
	private int x, y, i = 0, j = 0;
	
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
				x = (XMAP+j*(Const.TAM_WALL + Const.TAM_WALL));
				scene.addWall(new  FixedWall(x, y, craftFixedWall));//walls.add(new  FixedWall(x, y, craftFixedWall))
			}
		}
	}
	
	//Adiciona as Paredes Móveis, não ocupando posições iniciais dos bombermans
	@Override
	public void initMobileWalls(Scene scene, ArrayList<Bomber> bombers) {}
	
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
				Wall bl = scene.getWall(j);//.get(j);
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
	public void initItemList(Scene scene) {}
}
