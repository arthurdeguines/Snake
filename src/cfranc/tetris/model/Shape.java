package cfranc.tetris.model;

import java.awt.Color;
import java.util.Arrays;

public class Shape {
	
//	private int x,y;
	private Color color;
	private int[][] coords;
	private Shapes shape;
	
	public enum Shapes{SHAPE_Z, SHAPE_S, SHAPE_LINE, SHAPE_T, SHAPE_SQUARE, SHAPE_L, SHAPE_BACKL}
	
	/*
	 * shapesCoords = {coords_SHAPE_Z, coords_SHAPE_S, coords_SHAPE_LINE, coords_SHAPE_T, 
	 *                 coords_SHAPE_SQUARE, coords_SHAPE_L, coords_SHAPE_BACKLZ}
	 * coords = {bloc1, bloc2, bloc3, bloc4}
	 *  blocK = {Xrel,Yrel}
	 */
	static final int[][][] shapesCoords=new int[][][]{
		{{-1, 1}, { 0, 1}, { 0, 0}, { 1, 0}},// SHAPE_Z
		{{-1, 0}, { 0, 0}, { 0, 1}, { 1, 1}},// SHAPE_S
		{{ 0,-2}, { 0,-1}, { 0, 0}, { 0, 1}},// SHAPE_LINE
		{{-1, 0}, { 0, 0}, { 1, 0}, { 0, 1}},// SHAPE_T
		{{-1, 0}, { 0, 0}, { 0, 1}, {-1, 1}},// SHAPE_SQUARE
		{{ 1,-1}, { 0,-1}, { 0, 0}, { 0, 1}},// SHAPE_L
		{{-1,-1}, { 0,-1}, { 0, 0}, { 0, 1}},// SHAPE_BACKL
	};

	static final Color[] shapesColor = new Color[]{Color.RED, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.ORANGE, Color.BLUE};
	
	/**
	 * Triomino with position from the bottom left corner, color and shape.
	 * Each Triomino is build with 4 blocs with relative position from the 
	 * horizontal (x) and vertical (y) position.
	 * 
	 * @param x      horizontal position
	 * @param y      vertical position
	 * @param color
	 * @param coords {bloc1, bloc2, bloc3, bloc4} where blocK = {Xrel,Yrel} 
	 * @param shape
	 */
	private Shape(Color color, int[][] coords, Shapes shape) {
		super();
//		this.x = x;
//		this.y = y;
		this.color = color;
		this.coords = new int[][]{
			{coords[0][0],coords[0][1]},
			{coords[1][0],coords[1][1]},
			{coords[2][0],coords[2][1]},
			{coords[3][0],coords[3][1]}
		};
		this.shape = shape;
	};
	
	public Shape(Shapes shape){
		this(shapesColor[shape.ordinal()], shapesCoords[shape.ordinal()], shape);
	}
	
	/**
	 * Right turn blocs coordinates
	 * coords = (bloc1, bloc2, bloc3, bloc4)
	 * blocK = (Xrel,Yrel)
	 * Xrel->Yrel AND Yrel->-Xrel
	 */
	public void turnRight(){
		if(shape==Shapes.SHAPE_SQUARE) return;
		int x;
		
		// Loop on each 4 blocs
		for (int i = 0; i < coords.length; i++) {
			// Xrel->-Yrel AND Yrel->Xrel
			x=coords[i][0];
			coords[i][0]=coords[i][1];
			coords[i][1]=-x;
		}
	}

	/**
	 * Left turn blocs coordinates
	 * coords = (bloc1, bloc2, bloc3, bloc4)
	 * blocK = (Xrel,Yrel)
	 * Xrel->-Yrel AND Yrel->Xrel
	 */
	public void turnLeft(){
		if(shape==Shapes.SHAPE_SQUARE) return;
		int x;
		
		// Loop on each 4 blocs
		for (int i = 0; i < coords.length; i++) {
			
			// Xrel->Yrel AND Yrel->-Xrel
			x=coords[i][0];
			coords[i][0]=-coords[i][1];
			coords[i][1]=x;
		}
	}

	/**
	 * Add the Triomino absolute position at each bloc's relative positions  
	 * 
	 * @return absolute position for each blocs from the the bottom left corner
	 */
	public int[][] position(int x, int y){
		int[][] res=new int[4][2];
		
		// Loop on each 4 blocs
		for (int i = 0; i < coords.length; i++) {
			
			// Add relative position
			res[i][0]=coords[i][0]+x;
			res[i][1]=coords[i][1]+y;
		}
		return res;
	}
	
	@Override
	public String toString() {
		String res=null;
		StringBuilder sb = new StringBuilder(shapeToString());
		
		// Build the coords values
		String coordsString="(";
		for (int i = 0; i < coords.length; i++) {
			coordsString=coordsString+"("+coords[i][0]+", "+coords[i][1]+") ";
		}
		coordsString=coordsString+")";
		
		// Append attribute values to the grid 
//		sb.append("Triomino [x=" + x + ", y=" + y + ", color=" + color + ", coords="+ coordsString + ", shape="
		sb.append("Triomino [color=" + color + ", coords="+ coordsString + ", shape="
				+ shape + "]");
		res=sb.toString();
		return res;
	}

	protected String shapeToString() {
		String[][] triominoStringTab=new String[][]{
			{" ", " ", " ", " ", " "},// | | | | | |4 [0]
			{" ", " ", " ", " ", " "},// | | | | | |3 [1]
			{" ", " ", " ", " ", " "},// | | |x| | |2 [2]
			{" ", " ", " ", " ", " "},// | | | | | |1 [3]
			{" ", " ", " ", " ", " "} // | | | | | |0 [4]
		};//////////////////////////////  0 1 2 3 4

		// Position for (x,y)=(2,2)
		int [][] absPositionBlocs=new int[4][2];
		// Loop on each 4 blocs
		for (int i = 0; i < coords.length; i++) {			
			// Add relative position
			absPositionBlocs[i][0]=coords[i][0]+2;
			absPositionBlocs[i][1]=coords[i][1]+2;
		}

		// Fill triominoStringTab
		for (int i = 0; i < absPositionBlocs.length; i++) {
			int x=absPositionBlocs[i][0];
			int y=absPositionBlocs[i][1];
			// y : bottom to top
			triominoStringTab[triominoStringTab.length-1-y][x]="X";
		}

		// Build the grid to display
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < triominoStringTab.length; i++) {
			sb.append('|').append(triominoStringTab[i][0]).append('|');
			sb.append(triominoStringTab[i][1]).append('|');
			sb.append(triominoStringTab[i][2]).append('|');
			sb.append(triominoStringTab[i][3]).append('|');
			sb.append(triominoStringTab[i][4]).append('|');
			sb.append("\n+-+-+-+-+-+\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Shape triomino=new Shape(Color.BLACK, shapesCoords[1], Shapes.SHAPE_S);
		System.out.println(triomino);
	}

	public Color getColor() {
		return color;
	}
}
