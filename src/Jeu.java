
public class Jeu {
	Map map ;
	boolean finished;
	public Jeu(boolean b) {
		map = new Map();
		finished = false;
		System.out.println("TEST : " + map.getPerdu() + " " + map.fermer);
	    while (! map.getPerdu() && !Map.fermer)
	    {
	    
	    	System.out.println(Map.fermer);
	        map.nextMap();
	 			ActionSnake.dessine();
				try {
					System.out.println("TPS:::: " + (200 - Snake.taille));
					Thread.sleep (200 - 200*Snake.taille*(1/(map.largeur-2)) * (map.longueur-2));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	      
	    }
	    System.out.println("PERDU!!");
	}
}
