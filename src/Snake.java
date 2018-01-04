import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Snake {
	List<Coordonne> listSnake = new ArrayList<Coordonne>();
	Iterator itr;
	static int taille;
	Coordonne tete;
	
	public Snake(int taille) {
		
		this.taille = taille;
		for (int i = 1; i < taille; i++) {
			listSnake.add(new Coordonne(1,i+1));
		}
		
		
	}
	public int getTaille() {
		return this.taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public Coordonne getTete() {
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				//System.out.println(Map.map[i][j].getType());
				if(Map.map[i][j].getType() == TypeCase.TETE) {
					return new Coordonne(i,j); 
				}
			}
		}
		//System.out.println("ca passe");
		return null;
	}
	
	public TypeCase trouverSnake(int i, int j) {
		for (Coordonne coordonne : listSnake) {
			if(coordonne.getX() == i && coordonne.getY() == j) {
				return TypeCase.SNAKE;
			}
		}
		return null;
	}
	public List<Coordonne> nextSnake(Direction direction) {
		int resX = 0;
		int resY = 0;
		//System.out.println("TAILLE : " + taille);
		for (Coordonne coordonne : listSnake) {
			resX = coordonne.getX();
			resY = coordonne.getY();

			//System.out.println("resX = " + resX + "resY = " + resY);
			
		}
		if(listSnake.size() == taille) {
			listSnake.remove(0);
		}else {
			ActionSnake.scoreInt += 100;
		}
		
		Map.map[resX][resY].setType(TypeCase.SNAKE);
		
		
		if(direction == Direction.DROITE) {
			resY++;
		}else if(direction== Direction.GAUCHE) {
			resY--;
		}else if(direction == Direction.BAS) {
			resX++;
		}else {
			resX--;
		}
		if(Map.map[resX][resY].getType() == TypeCase.MUR || Map.map[resX][resY].getType() == TypeCase.SNAKE) {
			Map.perdu = true;
		}else {
			listSnake.add(new Coordonne(resX, resY));
		}
		//System.out.println(resX +" " + resY);
//		if(Map.map[getTete().getX()][getTete().getY()].getType() == TypeCase.SNAKE ) {
//			Map.perdu = true;
//		}
		return listSnake;
	}
	
}
