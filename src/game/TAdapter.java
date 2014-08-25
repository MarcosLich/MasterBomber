package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import characters.MasterBomber;

class TAdapter extends KeyAdapter {
	
    public void keyReleased(KeyEvent e) {
		GameBoard.masterBomber.keyReleased(e);
    }
    
    public void keyPressed(KeyEvent e) {
    	GameBoard.masterBomber.keyPressed(e);
	}
}
