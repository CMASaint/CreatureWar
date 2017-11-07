
public abstract class SpeciesGenerator {
	
	/** The current species' max Lifespan. */
	protected int speciesMaxLifespan;
	/** The current species' fitness. */
	protected double speciesFitness;
	/** The Creature[][] that contains all the Creature objects. */
	protected Creature[][] creatureWorld;
	/** The species which this Creature belongs to, this is static so
	 * that if more than one speciesGenerator is run, different species
	 * will not be represented by the same speciesType. */
	protected static int speciesType;
	
	/**
	 * Stores the values for int speciesCount, the World object 
	 * and the Creature[][] creatureWorld. The Creature class also
	 * sets the values contained in the World object so they 
	 * are available to all Creature objects.
	 * @param world the world the creatures will inhabit
	 */
	public SpeciesGenerator(World world){
		creatureWorld = world.getWorld();
		Creature.setHabitat(world);
	}
	
	/**
	 * I suggest calling the generator within the constructor.
	 * Each new species (Creature with unique fitness) will be 
	 * required to be generated. I would also suggest making this 
	 * synchronised if you wish to run multiple generators.
	 */
	protected abstract void generator();
	
	/**
	 * Instantiate whatever you wish to inhabit the Creature array.
	 * This is restricted as anything that extends from the abstract 
	 * Creature class.
	 */
	protected abstract void instantiateSpecies();


}
