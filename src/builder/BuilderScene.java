package builder;

import java.util.ArrayList;

import scene.Scene;

import characters.Bomber;
import characters.MasterBomber;

public interface BuilderScene {

	public void initFloor(Scene scene);
	public void initFixedWalls(Scene scene);
	public void initMobileWalls(Scene scene, ArrayList<Bomber> bombers);
	public void initMonsters(Scene scene, ArrayList<Bomber> bombers);
	public void initItemList(Scene scene);
	
}
