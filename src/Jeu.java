
public class Jeu {
	Map map ;
	int vitesse;
	boolean finished;
	public Jeu(boolean modeNormal, Option option) {
		vitesse = 200;
		map = new Map(modeNormal,option);
		finished = false;
		//System.out.println("TEST : " + map.getPerdu() + " " + map.fermer);
	    while (! map.getPerdu() && !Map.fermer)
	    {
	    	//System.out.println(Map.fermer);
	        map.nextMap();
	 			ActionSnake.dessine();
				try {
					//System.out.println("TPS:::: " + (200 - Snake.taille));
					if(modeNormal && option.accelerationNormal) {
						Thread.sleep (200 - Snake.taille);
					}else if(modeNormal){
						Thread.sleep (200);
					}else if(option.isAccelerationFun()){
						long sleep = (long) (((double)1/(((float)option.getVitesseFun())/1000))+50)-Snake.taille;
						//System.out.println(((double)1/(((float)option.getVitesseFun())/1000))+50);
						Thread.sleep(sleep);
					}else {
						long sleep = (long) (((double)1/(((float)option.getVitesseFun())/1000))+50);
						//System.out.println(((double)1/(((float)option.getVitesseFun())/1000))+50);
						Thread.sleep(sleep);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	      
	    }
	   // System.out.println("PERDU!!");
	}
}
