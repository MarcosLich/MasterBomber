package game;

import game.GameBoard;

import javax.swing.JFrame;



public class GameWindow extends JFrame{
	public GameWindow() {
		add(new GameBoard());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 710);//Numeros M�gicos, defini��o da tela gr�fica
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}
}
