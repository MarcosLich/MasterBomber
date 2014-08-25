package flyweight;

import java.awt.Image;

import javax.swing.ImageIcon;


import supports.Position;

public class ImageMonsterFlyweight implements MonsterFlyweight {
	private Image imageMonster;
	
	public ImageMonsterFlyweight(String craft) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		imageMonster = ii.getImage();
	}
	
	//Vou tirar a responsabilidade de retornar a imagem do BomberMonster para o Flyweight
	public Image monsterGetImage() {
		return imageMonster;
	}
}