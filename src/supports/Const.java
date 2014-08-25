package supports;

public class Const {
	
	//Constantes de Posição
	public final static int STOPED = 0;
	public final static int TAM_WALL = 40;//40 pixels
	public final static int LIM_MAPX_LEFT = 100;//Limite a esquerda de X onde o mapa começa a ser desenhado
	public final static int LIM_MAPX_RIGHT = 780;
	public final static int LIM_MAPY_UP = 0;	//Limite em cima de Y onde o mapa começa a ser desenhado
	public final static int LIM_MAPY_DOWN = 680;
	
	//Constantes de Direção
	public final static int UP = 1;
	public final static int LEFT = 2;
	public final static int DOWN = 3;
	public final static int RIGHT = 4;
	
	//Constantes de Status
	public final static int DEAD = 0;
	
	//Constantes de Itens
	public final static int SPEED = 1;
	public final static int BOMB = 2;
	public final static int FIRE = 3;
	public final static int LIVE = 4;
	public final static int KICK_BOMB = 5;
	public final static int WALL_PASS = 6;
	public final static int LIFE_JACKET = 7;
	public final static int DROP_BOMB = 8;
	
	//Constantes de quantidade e caracteristicas iniciais
	public final static int OVER = 0;
	public final static int INITIALIZE_LIVE = 1;
	public final static int INITIALIZE_SPEED = 2;
	public final static int INITIALIZE_NUM_BOMB = 1;
	public final static int INITIALIZE_FIRE_LEVEL = 2;
	public final static int INITIALIZE_TYPE_BOMB = 0;
	public final static int INITIALIZE_BOMB_TIMER = 200;
	
	public final static int MAX_LEVEL = 9;//Nível máximo para itens como Vida, numBombs, Velocidade;
	public final static int NUM_ALL_WALLS = 17;/*/Se dividir a tela jogável em blocos,/*/ 
																					/*/então ela é um array de 17 por 17 blocos quadrados, cada um com 40 pixels de lado./*/ 
	public final static int NUM_FIX_WALLS  = 64;
	public final static int NUM_MOBILE_WALLS  = 150;//PAREDES_MOVEIS
	public final static int NUM_BOMBERS_TO_ADD = 1;
	public final static int NUM_ITEM_LIST_TO_ADD = 30;
	public final static int NUM_MONSTERS_TO_ADD = 3;//Número de Monstros para serem adicionados no mapa
	public final static int NUM_BLOCKS_IN_LINE = 16;
	public final static int NUM_BLOCKS_IN_COLUMN = 16;
	
	//Constantes de tempo
	public final static int INITIALIZE_TIME = 30;
	public final static int INITIALIZE_DELAY = 100;
	public final static int INVINCIBLE_TIME = 500;
	public final static int SMALL_TIME_DELAY = 2000;
	public final static int BIG_TIME_DELAY = 4000000;
	public final static int EVENT_TIME = 5;
	public final static int CLOCK_TIME = 5;
}