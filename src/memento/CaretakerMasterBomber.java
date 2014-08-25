package memento;

import java.util.ArrayList;

import supports.Const;

import builder.BuilderScene;
import builder.BuilderSceneBattle;
import characters.Bomber;
import characters.MasterBomber;

public class CaretakerMasterBomber {
	private MementoMasterBomber memento;
	
	public void takeMemento(MementoMasterBomber memento) {
		this.memento = memento;
	}
	
	public MementoMasterBomber getMemento() {
		return memento;
	}
	
}
