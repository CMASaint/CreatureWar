
public class TestWorld {
	
	/** Determines how many different species will be generated
	 * (ONLY in SpeciesGenerator2!) */
	private final static int speciesCount = 2;
	/** Number of rows in the world. */
	private final static int rows = 15;
	/** Number of columns in the world. */
	private final static int columns = 10;
	/** The delay (in milliseconds) between prints of the contents
	 * of the 2D Creature array (creatureWorld). */
	private final static int checkDelay = 500;
	
	/**
	 * The main, in which the world is created, the 
	 * speciesGenerator creates the different species
	 * of creature and places them in the 2D Creature array
	 * (creatureWorld) and the Display class prints the contents to 
	 * the console.
	 * @param args
	 */
	public static void main(String[] args){
		World world = new World(rows, columns); 
		new SpeciesGenerator1(world);
//		new SpeciesGenerator1Jump(world);
//		new SpeciesGenerator2(world, speciesCount);
		new Display(world, checkDelay);
	}
	
	/* DIFFERENT SPECIES GENERATORS
	 * SpeciesGenerator1 creates species of Creature with predefined values for 
	 * fitness and maxLifespan (within the class) that cannot breach the edge of the array. 
	 * 
	 * SpeciesGenerator1Jump creates species of Creature with predefined values for fitness and maxLifespan 
	 * (within the class) that can breach the edge of arrays and spawn new Creatures on the opposite edge.
	 * 
	 * Species Generator2 creates species of Creature with random values for fitness and maxLifespan 
	 * (within the class) that cannot breach the edge of the array, multiple species can be generated if 
	 * required by changing the new parameter in the constructor, speciesCount.
	 */
}
