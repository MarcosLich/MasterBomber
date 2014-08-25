package flyweight;

import java.util.HashMap;


import supports.Const;


public class FactoryMonsterFlyweight {
	private HashMap<Integer, MonsterFlyweight> flyweights;
	
	//Construtor
	public FactoryMonsterFlyweight() {
		flyweights = new HashMap<Integer, MonsterFlyweight>();
	}
	
	
	//Cria a imagem, caso ela não exista//Pode ser usada como teste se existe a imagem  e tomar as providências
	private boolean createImage(int key) {
		if(flyweights.containsKey(key)) return false;
		
		switch(key) {
		case Const.DEAD:
			flyweights.put(key, new ImageMonsterFlyweight("..\\masterImages\\monsterDie.png"));
			break;
		case Const.UP:
			flyweights.put(key, new ImageMonsterFlyweight("..\\masterImages\\monsterC.png"));
			break;
		case Const.LEFT:
			flyweights.put(key, new ImageMonsterFlyweight("..\\masterImages\\monsterE.png"));
			break;
		case Const.DOWN:
			flyweights.put(key, new ImageMonsterFlyweight("..\\masterImages\\monster.png"));
			break;
		case Const.RIGHT:
			flyweights.put(key, new ImageMonsterFlyweight("..\\masterImages\\monsterD.png"));
			break;
		default:
			return false;
		}
		
		return true;
	}
	
	
	//Cria caso não exista a imagem, e retorna o flyweight
	public MonsterFlyweight getFlyweights(int key) {
		createImage(key);
		return (flyweights.get(key));
	}
}