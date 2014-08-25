package memento;

import characters.MasterBomber;

public class MementoMasterBomber {
	private MasterBomber masterBomber;
	
	public MementoMasterBomber(MasterBomber masterBomber) {
		this.masterBomber = masterBomber;
	}
	
	public MasterBomber getState() {
		return masterBomber;
	}
}