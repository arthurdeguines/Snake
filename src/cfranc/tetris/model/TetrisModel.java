package cfranc.tetris.model;

public class TetrisModel {
	
	PlayingPiece current;
	PlayingPiece next;
	Grid grid;
	
	public boolean advance() {
		boolean res=true;
		return res;
	}
	
	boolean moveDown(){
		boolean res=true;
		
		current.gridY--;
		
		res = isGridFree(current);
		
		if(res==false){
			// Cancel rotate
			current.gridY++;
		}
		return res;		
	}
	
	boolean putNext(){
		boolean res=true;
		res = isGridFree(next);		
		return res;
	}

	private boolean isGridFree(PlayingPiece pp) {
		boolean res=true;
		// Check playing piece into the grid
		int[][] blockPositions = pp.blockPositions();
		for (int i = 0; i < blockPositions.length; i++) {
			if(!grid.isFree(blockPositions[i][0], blockPositions[i][1])){
				res=false;
				break;
			}
		}
		return res;
	}

}
