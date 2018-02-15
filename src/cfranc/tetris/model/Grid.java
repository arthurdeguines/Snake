package cfranc.tetris.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Collections;

public class Grid {
	
	public static final int DEFAULT_NB_ROWS=20;
	public static final int DEFAULT_NB_COLUMNS=10;
	
	private int nbRows=DEFAULT_NB_ROWS;
	private int nbColumns=DEFAULT_NB_COLUMNS;
	
	private boolean[][] isNotFree;
	
	public Grid() {
		this(DEFAULT_NB_ROWS, DEFAULT_NB_COLUMNS);
	}

	public Grid(int nbRows, int nbColumns){
		this.nbRows=nbRows;
		this.nbColumns=nbColumns;
		isNotFree=new boolean[nbRows][nbColumns];
	}
	
	private List<Block> blocks;
	
	public List<Block> getBlocs(){
		return blocks;
	}
	
	public void addBlock(int line, int column){
		isNotFree[line][column]=true;
	}

	public int getNbRows() {
		return nbRows;
	}

	public int getNbColumns() {
		return nbColumns;
	}
	
	public boolean isFree(int gridX, int gridY){
		boolean res=true;		
		// Out of grid bounds
		if(gridX>=nbColumns || gridY < 0 || gridX <0){
			res=false;
		}
		else if(gridY<nbRows){
			res=!isNotFree[gridY][gridX];
		}
		return res;
	}

	public int deleteFullLines() {
		
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
	}
}
