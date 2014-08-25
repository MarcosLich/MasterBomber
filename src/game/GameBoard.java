package game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import memento.CaretakerMasterBomber;
import memento.MementoMasterBomber;

import scene.Item;
import scene.PatternWall;
import scene.Scene;
import scene.Wall;
import supports.Action;
import supports.Collision;
import supports.Const;
import supports.Clock;

import bombs.Bomb;
import builder.BuilderScene;
import builder.BuilderSceneArena;
import builder.BuilderSceneBattle;
import builder.BuilderSceneSnow;
import builder.DirectorScene;

import characters.Bomber;
import characters.MasterBomber;
import characters.BomberMonster;
import fires.Fire;
import fires.FireExplosion;
import fires.FireProjectil;
import fires.FireTail;
import flyweight.MonsterFlyweight;
import flyweight.FactoryMonsterFlyweight;


//Este programa está muito acoplado a Board, criar mais objetos pra desacoplar desse;
public class GameBoard extends JPanel implements ActionListener{
	
	//Atributos Boolenos
	private boolean ingame;
	private boolean pause;
	//Atributos inteiros
	private int stage;
	private int i;
	//Atributos Outros
	public static MasterBomber masterBomber;
	private Timer timer;
	private Scene scene;
	private DirectorScene directorScene;
	//Atributos Arrays
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<FireProjectil> fireProjectils; 
	private ArrayList<FireTail>fireTails;
	private ArrayList firesE;
	private ArrayList<Bomber> bomberMonsters;
	//Suports
	private Clock clock;
	private Collision collision;
	private Action action;
	//Builder
	private BuilderScene builder;
	//Flyweight
	private FactoryMonsterFlyweight monsterFactory;
	private MonsterFlyweight monsterFlyweight;
	//Memento
	private CaretakerMasterBomber caretakerMB;
	
	//Construtor
	public GameBoard() {
		addKeyListener(new TAdapter());
		
        setFocusable(true);
        setBackground(Color.BLACK);//fundo preto
        setDoubleBuffered(true);//Otimiza para desenhar 
		
        //Supports
        collision = new Collision();
        action = new Action();
        
        //Inicializa os Arrays
        fireProjectils = new ArrayList();
        firesE = new ArrayList();
        fireTails = new ArrayList();
        stage = 1;
        bomberMonsters = new ArrayList<Bomber>();
        masterBomber = new MasterBomber(Const.LIM_MAPX_LEFT, Const.LIM_MAPY_UP);
        caretakerMB = new CaretakerMasterBomber();
        
        takeStateBombers();
        beginStage();
        
        //Essa parte define o intervalo dos eventos
        timer = new Timer(Const.EVENT_TIME, this);
        timer.start();
	}
	
	private void takeMemento() {
		masterBomber = caretakerMB.getMemento().getState();
	}
	public void takeStateBombers() {
		caretakerMB.takeMemento(masterBomber.getMemento());
	}
	
	public void beginStage() {
		//Pega o memento do caretaker e põe no MasterBomber
		takeMemento();
		
		ingame = true;
        pause = false;
        clock = new Clock(5);//5 min
        //Builder Scene
        scene = new Scene();
        
        switch(stage) {
        	case 1:
        		builder = new BuilderSceneBattle();
        		break;
        	case 2:
        		builder = new BuilderSceneSnow();
        		break;
        	case 3:
        		builder = new BuilderSceneArena();
        		break;
        }
        
        directorScene = new DirectorScene(builder);
        directorScene.builderScene(masterBomber, bomberMonsters);
        scene = directorScene.getScene();
        walls = scene.getWalls();
        //Inicializa o Flyweight
        monsterFactory = new FactoryMonsterFlyweight();
        //salva o memento no caretaker 
        takeStateBombers();
        masterBomber.setStart(false);
	}
	
	//Desenha tudooooooooooooo
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        
        //Enquanto o jogo não acabar
        if(ingame) {
        	
        	
        	g2d.drawImage(scene.getFloor(), Const.LIM_MAPX_LEFT, Const.LIM_MAPY_UP, null);
        	
        	ArrayList<Bomb> bs =  masterBomber.getBombs();
        	
	        for (i = 0; i < fireTails.size(); i++) {
	            FireTail ft = fireTails.get(i);
	            if (ft.isVisible()) 
	            	g2d.drawImage(ft.getImage(), ft.getX(), ft.getY(), this);//Desenha fire tails
	        }
	        
	        for (i = 0; i < fireProjectils.size(); i++) {
	            FireProjectil f = fireProjectils.get(i);
	            if (f.isVisible())
	            	g2d.drawImage(f.getImage(), f.getX(), f.getY(), this);//Desenha fires projectil
	        }
	        
	        for (i = 0; i < firesE.size(); i++) {
	            FireExplosion fe =  (FireExplosion) firesE.get(i);
	            if (fe.isVisible())
	            	g2d.drawImage(fe.getImage(), fe.getX(), fe.getY(), this);//desenha fire explosion
	        }
	        
	        for (i = 0; i < bs.size(); i++ ) {
				    Bomb b = bs.get(i);
				    g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);//Desenha bombas
			}
	        for (i = 0; i < walls.size()/*blocos.size()*/; i++ ) {
	        
	        	Wall bl =  walls.get(i);
	        	if(bl.isVisible())
	        		g2d.drawImage(bl.getImage(), bl.getX(), bl.getY(), this);//Desenha Paredes
	        }
	        
	        for (i = 0; i < scene.getItemList().size(); i++ ) {
	            
	        	Item it =  (Item) scene.getItemList().get(i);
	        	if(it.isVisible())
	        		g2d.drawImage(it.getImage(), it.getX(), it.getY(), this);//Desenha itens
	        }
	        
	        g2d.drawImage(masterBomber.getImage(), masterBomber.getX(), masterBomber.getY(), this);
	        
	        for (i = 0; i < bomberMonsters.size(); i++ ) {
	        	Bomber bm =  bomberMonsters.get(i);
	        	int key = bm.getKeyImage();
	        	monsterFlyweight = monsterFactory.getFlyweights(key);
	        	
				if(bm.isVisible() && monsterFlyweight != null)
		        	g2d.drawImage(monsterFlyweight.monsterGetImage(), bm.getX(), bm.getY(), this);//desenha monsters
				
	        }
	        g2d.setColor(Color.WHITE);
	        g2d.drawString("Fase: " + stage, 5, 15);//Escreve número da fase
	        
	        g2d.setColor(Color.WHITE);
	        g2d.drawString("Vidas: " + masterBomber.getLives(), 5, 30);//Escreve número de vidas
	        
	        g2d.setColor(Color.WHITE);
	        g2d.drawString("Carrinho: " + masterBomber.getVelocidade(), 5, 45);//Escreve número de "Patins"
	        
	        g2d.setColor(Color.WHITE);
	        g2d.drawString("Bombas: " + masterBomber.getNumBombs(), 5, 60);//Escreve número de bombas
	        
	        g2d.setColor(Color.WHITE);
	        g2d.drawString("Fire: " + masterBomber.getFireNivel(), 5, 75);//Escreve nível do fogo
	        
	        g2d.setColor(Color.WHITE);
	        g2d.drawString(clock.toString(), 5, 90);//Escreve o "Tempo" do relógio
	        
	        
	        } else {
	        	Font textFont = new Font("Arial", Font.BOLD, 40);  
	        	g.setFont(textFont);  
	        	
	        	if(masterBomber.getLives() == 0) {
	        		g2d.setColor(Color.RED);
			        g2d.drawString("GAME OVER !!! =(", 240, 340);//Escreve número da fase
	        	}
	        	else if(stage > 2) {
	        		g2d.setColor(Color.BLUE);
	        		g2d.drawString("YOU WIN !!! =)", 240, 340);//Escreve número da fase
	        	}
		        
	        	
	        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }//Fim da Função paint(Graphics g)
	
	//Aqui que acontece a parte lógica e chama a função de colisões
    public void actionPerformed(ActionEvent e) {
    	
    	pause = masterBomber.isStart();
    	
    	if(pause == false) {//Tudo isso só roda se Pause for FALSO
    		
    		ArrayList<Bomb> bs =  masterBomber.getBombs();
    		
    		action.moveBombs(bs, firesE, fireProjectils);
    		action.<FireExplosion>move(firesE);//Metodo Generics
    		action.moveFires(fireProjectils, fireTails);
    		action.<FireTail>move(fireTails);//Metodo Generics
    		action.<Wall>move(walls);//Metodo Generics
    		action.<Item>move(scene.getItemList());//Metodo Generics
    		action.<Bomber>move(bomberMonsters);//Metodo Generics
    		masterBomber.move();
    		
    		if(clock.getMiliSegTotal() > 0) clock.addMiliSeg(-1);
    		
    		checkCollisions();
    	}
        repaint(); 
    }
    
    //Função de colisões
    private boolean checkCollisions() {
    	
    	collision.<MasterBomber, FireTail>masterBomberXElement(masterBomber, fireTails);//Generics 1
		collision.<Bomber, FireTail>element1XElement2(bomberMonsters, fireTails);//Generics 2
		collision.<MasterBomber, Bomber>masterBomberXElement(masterBomber, bomberMonsters);//Generics 1
		
		if(isEndGame() == true) {
			takeStateBombers();
			ingame = false;
			if (stage < 3 && bomberMonsters.size() == 0) {
				stage++;
				beginStage();
			}
			return ingame;
		}
		
		collision.<MasterBomber, Wall>masterBomberXElement(masterBomber, walls);//Generics 1
		collision.<Bomber, Wall>element1XElement2(bomberMonsters, walls);//Generics 2
		collision.<MasterBomber, Item>masterBomberXElement(masterBomber, scene.getItemList());//Generics 1
		collision.<FireProjectil, Item>element1XElement2(fireProjectils, scene.getItemList());//Generics 2
		collision.<FireProjectil, Wall>element1XElement2(fireProjectils, walls);//Genrics 2
		collision.fireXMobileWall(fireProjectils, walls, scene.getItemList());//Unique
		collision.masterBomberXBomb(masterBomber, masterBomber.getBombs());//Unique
    	collision.<Bomber, Bomb>element1XElement2(bomberMonsters, masterBomber.getBombs());//Generics 2
        collision.<FireProjectil, Bomb>element1XElement2(fireProjectils, masterBomber.getBombs());//Genrics 2
        collision.<Bomb, Wall>element1XElement2(masterBomber.getBombs(), walls);//Genrics 1
        
    	return ingame;
	}
	
	//retorna true, se não houver monstros
	public boolean isEndGame() {
		int livingMonsters;
		livingMonsters = bomberMonsters.size();
		
		if(masterBomber.getLives() > 0 && livingMonsters > 0) return false;//Todos vivos
		return true;//Default
	}
}