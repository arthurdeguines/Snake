package Snake;
import cfranc.xxgames.model.Block;
import cfranc.xxgames.model.GameModelInterface;

public class Jeu implements GameModelInterface{
	Map map ;
	boolean finished;
	public Jeu(boolean modeNormal, Option option) {
		map = new Map(modeNormal,option);
		finished = false;
		//System.out.println("TEST : " + map.getPerdu() + " " + map.fermer);
	    while (! map.getPerdu() && !Map.fermer)
	    {
	    	//System.out.println(Map.fermer);
	        map.nextMap();
	 			InterfaceSnake.dessine();
				try {
					//System.out.println("TPS:::: " + (200 - Snake.taille));
					if(modeNormal && option.accelerationNormal) {
						Thread.sleep (140 - Snake.taille);
					}else if(modeNormal){
						Thread.sleep (140);
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
	@Override
	public int getNbGridColumns() {
		
		return 25;
	}
	@Override
	public int getNbGridRows() {
		// TODO Auto-generated method stub
		return 25;
	}
	@Override
	public Block getBlock(int rowIndex, int columnIndex) {
		
		return new Block(rowIndex, columnIndex);
	}
	@Override
	public int cellSize() {
		return 20;
	}
}
