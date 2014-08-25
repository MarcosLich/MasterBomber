package scene;


import java.awt.Component;
import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import supports.Const;

import characters.Bomber;
import characters.MasterBomber;
import characters.BomberMonster;

//Esta classe tem o objetivo de ter todos os objetos referentes ao cenário fundo, paredes fixas e móveis  
public class Scene {
	//Atributos
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private String path = "..\\masterImages\\backGround1.png";
	private Image floor;

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public Image getFloor() {
		return floor;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public Wall getWall(int index) {
		return walls.get(index);
	}

	public void addWall(Wall wall) {
		walls.add(wall);
	}

	public void setFloor(Image image) {
		this.floor = image;
	}
	
}