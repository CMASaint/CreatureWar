
public class Display {
	
	/** A 2D Creature array, which comprises the 'world'
	 * the Creature objects are within. */
	private Creature[][] creatureWorld;
	/** Number of rows in the 2d Creature array. */
	private int rows;
	/** Number of columns in the 2d Creature array. */
	private int columns;
	/** A counter for how many creatures (of any species) 
	 * there are in the 2D Creature array. */
	private int creatureCount;

	/**
	 * The full contents of the world (2D Creature Array)
	 * is printed to the console. As well as information about
	 * the population. This is updated with a frequency 
	 * determined by the parameter checkDelay.
	 * @param newWorld the World object from which many values 
	 * are obtained.
	 * @param checkDelay the delay between prints of the contents
	 * of the world.
	 */
	public Display(World newWorld, int checkDelay){
		columns = newWorld.getColumns();
		rows = newWorld.getRows();
		creatureWorld = newWorld.getWorld();
		for(;;){
			try {
				Thread.sleep(checkDelay);
			} catch (InterruptedException e) {	
				System.out.println("updateWorld Interrupt Error");
			}	
			printWorld();
			printWorldInfo();
		}
	}
	
	/**
	 * Prints the contents of the world to the console.
	 * Different species of creature are distinguished 
	 * by different numbers. (If attempting to run more 
	 * than 99 different species the display will display 
	 * somewhat squint.)
	 */
	private void printWorld(){
		creatureCount = 0;
		for(int i = 0 ; i<rows ; i++){
			System.out.println();
			for(int j = 0; j<columns ; j++){
				Creature nextCreature = creatureWorld[i][j];
				if(nextCreature == null){
					System.out.print("-- ");
				}
				else{
					int species = nextCreature.getSpeciesType();
					if(species>=10){
						System.out.print(species +" ");
					}
					else{
						System.out.print(species +"  ");
					}
					creatureCount++;
				}			
			}
		}
	}	
	
	/**
	 * Prints the population of the world.
	 */
	private void printWorldInfo(){
		System.out.println("\nPopulation = " + creatureCount);
	}
	
	
}
