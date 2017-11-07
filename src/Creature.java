import java.util.Random;

public abstract class Creature extends Thread{
	
	/** The maxLifespan of an individual Creature object, this
	 * will be shared by all members of this creatures species. */
	protected int maxLifespan;
	/** The fitness of an individual Creature object, this will
	 * be shared by all members of this creatures species and no 
	 * other species can have the same value. */ 
	protected double fitness;
	/** The lifespan of an individual Creature. */
	protected int lifespan;
	/** Used to generate random values. */
	protected Random rand = new Random();
	/** The 2D Creature array in which the Creature objects and null 
	 * values which make up the 'world' are held.*/
	protected static Creature[][] creatureWorld;
	/** The row location of a given Creature object. */
	protected int locationR;
	/** The column location of a given Creature object. */
	protected int locationC;
	/** The next row location that the Creature must inspect to determine
	 * if a child can be placed, used with nextC. */
	protected int nextR;
	/** The next column location that the Creature must inspect to determine
	 * if a child can be placed, used with nextR. */	
	protected int nextC;
	/** The next Creature object to be acted upon. */
	protected Creature nextCreature;
	/** If true, a new Creature of the same species (same fitness) will be 
	 * instantiated. If false no new Creature will be instantiated.*/
	protected boolean giveBirth;
	/** The number of rows in the world (creatureWorld[][]). */
	protected static int rows;
	/** The number of columns in the world (creatureWorld[][]). */
	protected static int columns;
	/** The species which this Creature belongs to. */
	protected int speciesType;

	/**
	 * The object determines values for maxLifespan, fitness and lifespan.
	 * @param maxLife The value for maxLifespan for this instance of Creature.
	 * @param fit The value for fitness for this instance of Creature.
	 */
	public Creature(int maxLife, double fit, int speciesType){
		maxLifespan = maxLife;
		fitness = fit;
		lifespan = calcLifespan();
		locationR = 0;
		locationC = 0;
		this.speciesType = speciesType;
	}
	
	/**
	 * Values are assigned to the static variables creatureWorld,
	 * rows, columns and wallsUp. 
	 * @param newWorld The World object from which all the static variables
	 * are assigned values.
	 */
	public static void setHabitat(World newWorld){
		creatureWorld = newWorld.getWorld();
		rows = newWorld.getRows();
		columns = newWorld.getColumns();
	}
	
	/**
	 * For each class extending the Creature class it is necessary 
	 * to define this method in order to create a new instance (child) 
	 * of the new class.
	 * @return An extended Creature object.
	 */
	protected abstract Creature createChild();
	
	/**
	 * Places the Creature object, given as a parameter, into 
	 * the 2D Creature array and causes the thread to start
	 * execution. The object can only be placed in a vacant 
	 * square.
	 * @param nextCreature the Creature object to be placed in
	 * the world.
	 */
	protected void placeCreature(){
		boolean newLocation = false;
		int startR=0;
		int startC=0;
		while(!newLocation){
			startR = rand.nextInt(rows - 1);
			startC = rand.nextInt(columns - 1);
			this.setLocations(startR, startC);
			if(creatureWorld[startR][startC] == null){
				newLocation = true;
			}
		}
		creatureWorld[startR][startC] = this;
		this.start();
	}
	
	/**
	 * Sets the values for the location (which row and which column) 
	 * of the given Creature object to the values provided as parameters.
	 * @param currentRow The current row in the creatureWorld array which
	 * holds this Creature object.
	 * @param currentColumn The current column in the creatureWorld array which
	 * holds this Creature object.
	 */
	protected void setLocations(int currentRow, int currentColumn){
		setRLocation(currentRow);
		setCLocation(currentColumn);
	}
	
	/**
	 * Sets the value for the row location of the given Creature 
	 * object to the value provided as parameter.
	 * @param currentRow The current row.
	 */
	protected void setRLocation(int currentRow){
		locationR = currentRow;
	}
	
	/**
	 * Sets the value for the column location of the given Creature 
	 * object to the value provided as parameter.
	 * @param currentColumn The current column.
	 */
	protected void setCLocation(int currentColumn){
		locationC = currentColumn;
	}
	
	/**
	 * Calculates a random lifespan in milliseconds,
	 * with a maximum value set by maxLifespan.
	 * @return An int value to be used for lifespan.
	 */
	private int calcLifespan(){
		lifespan = rand.nextInt((maxLifespan*1000));
		return lifespan;
	}

	/**
	 * Returns the value for fitness.
	 * @return A double value for fitness.
	 */
	protected double getFitness(){
		return fitness;
	}
	
	/**
	 * Returns the value for maxLifespan.
	 * @return An int value for fitness.
	 */
	protected int getMaxLifespan(){
		return maxLifespan;
	}
	
	/**
	 * Returns the speciesType
	 * @return an int that represents the species type.
	 */
	protected int getSpeciesType(){
		return speciesType;
	}
	
	/**
	 * Checks the surrounding 8 locations in the immediate vicinity of the 
	 * current Creature object as well as checking the current Creatures own location, 
	 * all within the creatureWorld array. In each of these locations it is calculated 
	 * whether a new Creature object with the same fitness and maxLifespan values
	 * will be generated and new Creature objects are created and placed in the array
	 * where necessary.
	 */
	protected synchronized void checkNest(){
		int newR = locationR - 1 ;
		int newC = locationC - 1 ;
		for(int r = 0 ; r<3	; r++){
			for(int c = 0 ; c<3 ; c++){
				try{
					nextR = newR + r;
					nextC = newC + c;
					nextCreature = creatureWorld[nextR][nextC];
					if(r==1 && c==1){
						creatureWorld[nextR][nextC] = null;
						giveBirth = this.isBorn(null);
					}
					else{
						giveBirth = this.isBorn(nextCreature);
					}
					this.birth(giveBirth);
				}
				catch(ArrayIndexOutOfBoundsException e){	
					this.edgeBehaviour();
				}
			}
		}
	}

	/**
	 * Determines whether a new Creature object should replace the Creature object given
	 * as a parameter.
	 * @param nearbyCreature The Creature object that is in a 'neighbour' position
	 * within the creatureWorld array.
	 * @return A boolean value, true if a new Creature should be instantiated,
	 * false if not.
	 */
	protected boolean isBorn(Creature nearbyCreature){
		boolean giveBirth = false;
		if(nearbyCreature != null){
			double otherFit = nearbyCreature.getFitness();
			if(Math.random() <= fitness - otherFit) {
				giveBirth = true;
			}
		}
		if(nearbyCreature == null){
			if(Math.random() <= fitness) {
				giveBirth = true;
			}
		}
		return giveBirth;
	}
	
	/**
	 * Instantiates a new Creature object, places it in the creatureWorld array, 
	 * and starts the Thread, as long as the parameter is true. If it is false, 
	 * no Creature object is instantiated.
	 * @param giveBirth A boolean value, true if a new Creature should be instantiated,
	 * false if not.
	 */
	protected void birth(boolean giveBirth){
		if(giveBirth){
			if(nextCreature != null){
				nextCreature.interrupt(); 
			}
			Creature child = this.createChild();
			child.setLocations(nextR, nextC);
			creatureWorld[nextR][nextC] = child;
			child.start();
		}
	}
	
	/**
	 * If this method is left blank, the Creature cannot spawn across the edge
	 * of the creatureWorld array. You can define behaviour for this if required
	 * which can, for instance, allow Creatures to spwawn new Creature objects on
	 * the opposite edge of the array.
	 */
	protected abstract void edgeBehaviour();
	
	/**
	 * Has the thread sleep for a previously determined period of 
	 * time and then checks its surroundings to determine whether
	 * new objects of the same species will be spawned.
	 */
	public void run() {
		try {
			Thread.sleep(lifespan);
			checkNest();
		}
		catch (InterruptedException e) {	}
	}
	
	
}
