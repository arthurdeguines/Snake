import java.awt.Desktop.Action;
import java.util.List;

public class Map {
	static boolean aJouer = false;
	int largeur;
	int longueur;
	static Case[][] map;
	Snake snake;
	ActionSnake action;
	static boolean perdu;
	public static boolean fermer = false;
	public Map() {
		this.longueur = 20;
		this.largeur = 20;
		map = initMap(longueur,largeur);
		action = new ActionSnake();
		perdu = false;
	
	}
	private Case[][] initMap(int longueur, int largeur) {
		map = new Case[longueur][largeur];
		snake = new Snake(2);
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				if(i==0 || i == longueur-1 || j ==0 || j ==largeur-1) {
					map[i][j] = new Case(TypeCase.MUR);
				}else if(snake.trouverSnake(i,j) == TypeCase.TETE) {
					map[i][j] = new Case(TypeCase.TETE);
				}else if(snake.trouverSnake(i,j) == TypeCase.SNAKE) {
					map[i][j] = new Case(TypeCase.SNAKE);
				}else {
					map[i][j] = new Case(TypeCase.VIDE);
				}
			}
		}
		placerNouriture();
		return map;
	}
	public Map(int width,int height) {
		this.longueur = width;
		this.largeur = height;
		map = initMap(longueur, largeur);
	}
	public Case[][] getMap() {
		return map;
	}
	public String toString() {
		String res = "";
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				if(map[i][j].getType()==TypeCase.MUR) {
					res += "%";
				}else if(map[i][j].getType()==TypeCase.TETE){
					res += ">";
				}else if(map[i][j].getType()==TypeCase.SNAKE){
					res += "O";
				}else {
					res +="X";
				}
			}
			res +="\r\n";
		}
		return res;
	}
	public boolean getPerdu() {
		return this.perdu;
	}
	
	public void placerNouriture() {
		int nombreAleatoireX;
		int nombreAleatoireY;
		if(!(trouverNouriture() == 3)) {
			snake.setTaille(snake.getTaille() + 1);
		}
		while(!(trouverNouriture() == 3)) {
			nombreAleatoireX = (int)(Math.random() * map.length );
			nombreAleatoireY = (int)(Math.random() * map.length );
			
			if(map[nombreAleatoireX][nombreAleatoireY].getType() == TypeCase.VIDE) {
				
				map[nombreAleatoireX][nombreAleatoireY].setType(TypeCase.NOURITURE);
			}
		}
	}
	
	private int trouverNouriture() {
		int res = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				
				if(map[i][j].getType() == TypeCase.NOURITURE) {
					res++;
				}
			}
		}
		return res;
	}
	public void nextMap() {
		aJouer = false;
		if(!(ActionSnake.scoreInt <=0)) {
			action.addScore(-1);
		}
		
		snake.nextSnake(action.getDirection());
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				if(snake.trouverSnake(i, j) == TypeCase.SNAKE) {
					map[i][j].setType(TypeCase.SNAKE);
				}else if(map[i][j].getType() == TypeCase.MUR ) {
					
				}else if( map[i][j].getType() == TypeCase.NOURITURE) {
					
				}else {
					map[i][j].setType(TypeCase.VIDE);
				}
			}
		}
		
		placerNouriture();

	}

}
