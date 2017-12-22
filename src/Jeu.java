
public class Jeu {
	Map map ;
	boolean finished;
	public Jeu() {
		map = new Map();
		finished = false;
	    while (! map.getPerdu())
	    {
	        map.nextMap();
	 			ActionSnake.dessine();
				try {
					Thread.sleep (200 - Snake.taille*(1/(map.largeur-2) * (map.longueur-2)));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	      
	    }
	    System.out.println("PERDU!!");
	}
}
