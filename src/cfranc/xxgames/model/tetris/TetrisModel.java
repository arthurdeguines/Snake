package cfranc.xxgames.model.tetris;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cfranc.tetris.model.Grid;
import cfranc.tetris.model.PlayingPiece;
import cfranc.tetris.model.Shape;
import cfranc.tetris.model.Shape.Shapes;
import cfranc.xxgames.model.Block;
import cfranc.xxgames.model.DefaultGameModel;
import cfranc.xxgames.model.OneDirection;

public class TetrisModel extends DefaultGameModel{
	
	PlayingPiece current;
	PlayingPiece next;
	TetrisBlock[][] grid;
	Grid myGrid;
	int score=0;
	
	boolean moveDown(){
		boolean res=true;
		
		setChanged();			
		notifyObservers(triominoBlocker(current));

		current.gridY--;

		res = isGridFree(current);
		
		if(res==false){
			// Cancel moveDown
			current.gridY++;
			// Add current to grid
			addCurrent();
			//deleteFullLines();
			res=false;
		}
		setChanged();			
		notifyObservers(triominoBlocker(current,null));
		return res;		
	}
	
	/*private void deleteFullLines() {
		int nbFullLines=_deleteFullLines();
		score=score+(nbFullLines*myGrid.getNbColumns());
		notifyObservers();
	}*/

	/**
	 * Add current piece to the grid
	 * 
	 * @return false is cannot add new piece !
	 */
	private boolean addCurrent() {
		boolean res=true;
		int[][] pos=current.blockPositions();
		for (int i = 0; i < pos.length; i++) {
			int line=pos[i][1];
			int column=pos[i][0];
			if( (line>=0 && line<myGrid.getNbRows())
				&&
				(column>=0 && column<myGrid.getNbColumns()) ){
				myGrid.addBlock(line, column);
			}
			else{
				res=false;
				break;
			}
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
			if(!myGrid.isFree(blockPositions[i][0], blockPositions[i][1])){
				res=false;
				break;
			}
		}
		return res;
	}

	public TetrisModel() {
		super();
		grid=new TetrisBlock[19][30];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j]=new TetrisBlock(i,j,null);
			}
		}
		myGrid=new Grid(this.getNbGridRows(), this.getNbGridColumns());
	}	
	
	@Override
	public int cellSize() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	private final TriominoFactory factory=new TriominoFactory(this.getNbGridColumns()/2, this.getNbGridRows()-1);
	
	@Override
	public boolean advance() {
		boolean res=false;
		if(current==null){
			if(next!=null){
				current=next;
				next=factory.createTriomino();
			}
			else{
				current=factory.createTriomino();
				next=factory.createTriomino();
			}
		}
		if(moveDown()){
			res=true;
		}
		else if(putNext()){
			current=null;
			res=true;
		}
		// Game Over
		else{
			System.out.println(next);
			res=false;
		}
		return res;
	}
	
	@Override
	public void changeDirection(OneDirection newDirection) {
		if(_canChangeDirection(newDirection)){
			setChanged();			
			notifyObservers(triominoBlocker(current, null));
		}
	}
	
	public boolean _canChangeDirection(OneDirection newDirection) {
		boolean res=true;
		if( newDirection.equals(OneDirection.UP) ) {
			if(canTurnLeft(current)){
				setChanged();			
				notifyObservers(triominoBlocker(current, null));
			}
		}
		else if ( newDirection.equals(OneDirection.DOWN) ){
			if(canTurnRight(current)){
				setChanged();			
				notifyObservers(triominoBlocker(current, null));
			}
		}
		else if ( newDirection.equals(OneDirection.LEFT) ){
			if(canGoLeft(current)){
				setChanged();			
				notifyObservers(triominoBlocker(current, null));
			}
		}
		else if ( newDirection.equals(OneDirection.RIGHT) ){
			if(canGoRight(current)){
				setChanged();			
				notifyObservers(triominoBlocker(current, null));
			}
		}
		return res;
	}
	
	private boolean canGoRight(PlayingPiece t){
		setChanged();			
		notifyObservers(triominoBlocker(t));

		t.gridX++;
		boolean res=_canGoRight(t);
		
		if(res==false){
			t.gridX--;
		}

		setChanged();			
		notifyObservers(triominoBlocker(t, null));

		return res;
	}

	private boolean _canGoRight(PlayingPiece t) {
		boolean res=false;
		if(t==null) return false;
		int[][] pos=t.position();
		if( (pos[0][0]<getNbGridColumns())
			&&
			(pos[1][0]<getNbGridColumns())
			&&
			(pos[2][0]<getNbGridColumns())
			&&
			(pos[3][0]<getNbGridColumns()) ){
			res=true;
		}
		return res;
	}
	
	private boolean canGoLeft(PlayingPiece t){
		boolean res=false;
		if(t==null) return false;
		
		setChanged();			
		notifyObservers(triominoBlocker(t));

		t.gridX--;
		
		res=_canGoLeft(t);

		if(res==false){
			t.gridX++;
		}
		
		setChanged();			
		notifyObservers(triominoBlocker(t, null));
		
		return res;
	}
	
	private boolean _canGoLeft(PlayingPiece t) {
		boolean res=false;
		if(t==null) return false;
		int[][] pos=t.position();
		if( (pos[0][0]>=0)
				&&
				(pos[1][0]>=0)
				&&
				(pos[2][0]>=0)
				&&
				(pos[3][0]>=0) ){
			res=isGridFree(t);
		}
		return res;
	}

	private boolean canGoDown(PlayingPiece t){
		boolean res=false;
		if(t==null) return false;
		int[][] pos=t.position();
		if( (pos[0][1]<getNbGridRows()-1)
				&&
				(pos[1][1]<getNbGridRows()-1)
				&&
				(pos[2][1]<getNbGridRows()-1)
				&&
				(pos[3][1]<getNbGridRows()-1) ){
			res=true;
		}
		return res;
	}
	
	private boolean canTurnRight(PlayingPiece t){
		boolean res;
		setChanged();			
		notifyObservers(triominoBlocker(t));

		t.turnRight();
		res=isGridFree(t);
		if(res==false){
			t.turnLeft();
		}
		
		setChanged();			
		notifyObservers(triominoBlocker(t, null));
		
		return res;
	}

	private boolean canTurnLeft(PlayingPiece t){
		boolean res=false;
		setChanged();			
		notifyObservers(triominoBlocker(t));

		t.turnLeft();
		res=isGridFree(t);
		if(res==false){
			t.turnRight();
		}
		
		setChanged();			
		notifyObservers(triominoBlocker(t, null));
		
		return res;
	}

	private boolean _canTurnLeft(PlayingPiece t) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<Block> triominoBlocker(PlayingPiece t){
		return triominoBlocker(t, Block.DEFAULT_COLOR);
	}

	/*private int _deleteFullLines() {
		
		// Search full lines
		List<Integer> iFullLines=new ArrayList<Integer>();
		List<Integer> iNotFullLines=new ArrayList<Integer>();
		for(int iLine=0; iLine<this.nbRows;iLine++){
			boolean fullLine=true;
			for (int k = 0; k < isNotFree[iLine].length; k++) {
				
				// Cancel iLine when one of thoses columns is free !
				if(!isNotFree[iLine][k]){
					fullLine=false;
					break;
				}
			}
			
			// The iLine is full
			if(fullLine){
				iFullLines.add(iLine);
			}
			else{
				iNotFullLines.add(iLine);
			}
		}
		
		// Fill new grid
		int iNewGridLine=0;
		for(int iLine=0; iLine<this.nbRows;iLine++){
			
			// Check if this line shall not be deleted 
			// i.e. shall be copied
			if(!iFullLines.contains(iLine)){
				
				// Copy line
				for (int k = 0; k < isNotFree[iNewGridLine].length; k++) {
					isNotFree[iNewGridLine][k]=isNotFree[iLine][k];
				}
				
				// Next new line
				iNewGridLine++;
			}
		}
		
		// Fill empty lines at the top of the grid
		for(int i=iNewGridLine;i<isNotFree.length;i++){
			for (int k = 0; k < isNotFree[iNewGridLine].length; k++) {
				isNotFree[i][k]=false;
			}
		}
		
		// Return number of full lines
		return iFullLines.size();
	}*/

	
	private List<Block> clearGridFull(){
		List<Block> res=new ArrayList<Block>();
		for (int i = 0; i < myGrid.getNbRows(); i++) {
			for (int j = 0; j < myGrid.getNbColumns(); j++) {
				int iPanelLine=this.getNbGridRows()-1-j;
				int iPanelColumn=i;
				if(myGrid.isFree(j, i)){
					res.add(new TetrisBlock(iPanelLine,iPanelColumn,Block.DEFAULT_COLOR));
				}
			}
		}
		return res;
	}
	
	private List<Block> displayGridFull(){
		List<Block> res=new ArrayList<Block>();
		for (int i = 0; i < myGrid.getNbRows(); i++) {
			for (int j = 0; j < myGrid.getNbColumns(); j++) {
				int iPanelLine=this.getNbGridRows()-1-j;
				int iPanelColumn=i;
				if(myGrid.isFree(j, i)){
					res.add(new TetrisBlock(iPanelLine,iPanelColumn,Color.blue));
				}
			}
		}
		return res;
	}
	
	private List<Block> triominoBlocker(PlayingPiece t, Color color){
		List<Block> res=new ArrayList<Block>();
		if(color==null){
			color=t.getColor();
		}
		int[][] pos=t.position();
		for (int i = 0; i < pos.length; i++) {
			int iPanelLine=this.getNbGridRows()-1-pos[i][1];
			int iPanelColumn=pos[i][0];
			if( (iPanelLine>=0 && iPanelLine<this.getNbGridRows())
				&&
				(iPanelColumn>=0 && iPanelColumn<this.getNbGridColumns()) ){
				res.add(new TetrisBlock(iPanelLine,iPanelColumn,color));
			}
		}
		return res;
	}
}

class TriominoFactory{
	
	int startCol,startLine;

	Random random=new Random(System.currentTimeMillis());
	
	PlayingPiece[] cache;
	
	public TriominoFactory(int startCol, int startLine) {
		this.startCol=startCol;
		this.startLine=startLine;
		cache=new PlayingPiece[7];
	}

	public PlayingPiece createTriomino(){
		PlayingPiece res=null;
		int i=random.nextInt(cache.length);
		switch(i){
			case 0:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_Z));
				break;
			case 1:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_S));
				break;
			case 2:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_LINE));
				break;
			case 3:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_T));
				break;
			case 4:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_SQUARE));
				break;
			case 5:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_L));
				break;
			default:
				res=new PlayingPiece(startCol, startLine, new Shape(Shapes.SHAPE_BACKL));
		}
		return res;
	}
}