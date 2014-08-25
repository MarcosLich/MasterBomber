package builder;

import java.util.ArrayList;

import scene.Scene;

import characters.Bomber;
import characters.MasterBomber;

public class DirectorScene {
	private Scene scene;
	private BuilderScene builderScene;//Cenário
	
	public DirectorScene(BuilderScene builderScene) {
		scene = new Scene();
		this.builderScene = builderScene;
	}
	
	public void builderScene(MasterBomber masterBomber, ArrayList<Bomber> bombers) {
		builderScene.initFloor(scene);
		builderScene.initFixedWalls(scene);
		builderScene.initMobileWalls(scene, bombers);
		builderScene.initMonsters(scene, bombers);
		builderScene.initItemList(scene);
	}
	
	public Scene getScene() {
		return scene;
	}
}
