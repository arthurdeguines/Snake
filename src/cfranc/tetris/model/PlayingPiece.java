package cfranc.tetris.model;

import java.awt.Color;

public class PlayingPiece {

	public int gridX;
	public int gridY;

	Shape shape;
	
	public PlayingPiece(int gridX, int gridY, Shape shape) {
		super();
		this.gridX = gridX;
		this.gridY = gridY;
		this.shape = shape;
	}

	public void turnLeft(){
		shape.turnLeft();
	}
	
	public void turnRight(){
		shape.turnRight();
	}
	
	public int[][] blockPositions(){
		int[][] res=shape.position(gridX, gridY);
		return res;
	}
	
	
	// INTERFACE
	
	public Color getColor(){
		return shape.getColor();
	}
	
	public int[][] position(){
		int[][] res=this.blockPositions();
		return res;
	}

	@Override
	public String toString() {
		return "PlayingPiece [gridX=" + gridX + ", gridY=" + gridY + ", shape=" + shape + "]";
	}
}
