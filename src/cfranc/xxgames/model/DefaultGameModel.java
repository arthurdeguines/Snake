package cfranc.xxgames.model;

import java.awt.Color;
import java.util.Observable;

public class DefaultGameModel extends Observable implements GameModelInterface, GameCtrlInterface {
	private int rowIndex;
	private int columnIndex;
	public static final int GRID_ROW=20;
	public static final int GRID_COL=10;
	
	public Block[][] grid=new Block[GRID_ROW][GRID_COL];
	
	public Block getBlock(int rowIndex, int columnIndex){
		return grid[rowIndex][rowIndex];
	}
	
	@Override
	public int getNbGridColumns() {
		// TODO Auto-generated method stub
		return grid[0].length;
	}

	@Override
	public int getNbGridRows() {
		// TODO Auto-generated method stub
		return grid.length;
	}
	
	@Override
	public int cellSize(){
		return 5;
	}

	public DefaultGameModel() {
		super();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j]=new Block(i,j,Block.DEFAULT_COLOR);
			}
		}
	}

	private synchronized void changeColor(int rowIndex, int columnIndex){
		changeColor(rowIndex, columnIndex, Block.DEFAULT_COLOR);
	}
	
	private synchronized void changeColor(int rowIndex, int columnIndex, Color color){
		grid[rowIndex][columnIndex].color=color;
		setChanged();
		notifyObservers(new int[]{rowIndex, columnIndex});
	}

	/* (non-Javadoc)
	 * @see cfranc.xxgames.model.GameCtrlInterface#advance()
	 */
	@Override
	public boolean advance() {
		int oldRowIndex=rowIndex;
		int oldColIndex=columnIndex;
		grid[rowIndex][columnIndex]=new Block(rowIndex,columnIndex,Block.DEFAULT_COLOR);
		setChanged();
		notifyObservers(new int[]{oldRowIndex, oldColIndex});
		if( (columnIndex<this.getNbGridColumns()-1)
				&&
				(rowIndex<this.getNbGridRows()) ){
				columnIndex++;
		}
		else if( (columnIndex==this.getNbGridColumns()-1)
				&&
				(rowIndex<this.getNbGridRows()-1) ){
				rowIndex++;
				columnIndex=0;
		}
		else{
			rowIndex=0;
			columnIndex=0;
		}
		grid[rowIndex][columnIndex]=new Block(rowIndex,columnIndex,Color.RED);
		setChanged();
		int newRowIndex=rowIndex;
		int newColIndex=columnIndex;
		notifyObservers(new int[]{newRowIndex, newColIndex});
		return true;
	}

	public void changeDirection(OneDirection newDirection) {
	}

	public String getScore() {
		// TODO Auto-generated method stub
		return "";
	}	
}
