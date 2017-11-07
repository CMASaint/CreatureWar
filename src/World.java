
public class World {
	
	private Creature[][] world;
	private int rows;
	private int columns;
	
	/**
	 * Creates and holds an empty Creature 2D array, 
	 * which holds the different Creature objects. 
	 * The world object also stores the number of 
	 * rows and columns.
	 */
	public World(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.createWorld();
	}
	
	/**
	 * Creates an empty Creature 2D array.
	 */
	private synchronized void createWorld(){
		world = new Creature[rows][columns];
	}
	
	/**
	 * Returns the Creature 2D array.
	 * @return Creature[][] world
	 */
	public Creature[][] getWorld(){
		return world;
	}
	
	/**
	 * Returns the number of rows in the Creature 2D array.
	 * @return int rows
	 */
	public int getRows(){
		return rows;
	}
	
	/**
	 * Returns the number of columns in the Creature 2D array.
	 * @return int columns
	 */
	public int getColumns(){
		return columns;
	}
	
	
}
