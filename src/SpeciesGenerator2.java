import java.util.Random;

public class SpeciesGenerator2 extends SpeciesGenerator{
	
	/** The maximum Lifespan any species can be assigned. */
	private final int maxSpeciesLifespan = 20;
	/** The Random object for use where necessary. */
	private Random rand = new Random();
	/** The number of different species to be generated. */
	private int speciesCount;
	
	/**
	 * Stores the values for int speciesCount, double[] 
	 * fitnessChart, the World object and the Creature[][] 
	 * creatureWorld. It then calls the generator method. 
	 * In this case the generator method generates Creature 
	 * objects with random values for fitness and 
	 * MaxLifespan. These species cannot create new Creature
	 * objects off the edge of the array.
	 * @param speciesCount the number of species required.
	 * @param world the world the creatures will inhabit
	 */
	public SpeciesGenerator2(World world, int speciesCount) {
		super(world);
		this.speciesCount = speciesCount;
		this.generator();
	}

	/**
	 * Generates a number of Creature objects (equal to speciesCount) 
	 * with random MaxLifespan and fitness values.
	 */
	protected synchronized void generator(){
		for(int i = 0 ; i<speciesCount; speciesType++, i++){
			this.calcSpeciesMaxLife();
			this.calcFitness();
			instantiateSpecies();
		}
	}
	
	protected void instantiateSpecies() {
		Creature1 nextSpecies = new Creature1(speciesMaxLifespan, speciesFitness, speciesType);
		nextSpecies.placeCreature();
	}
	
	/**
	 * Calculates a random maximum lifespan value (int).
	 * @return An int value between 1 and 
	 * the current value of maxLifespan.
	 */
	private void calcSpeciesMaxLife(){
		this.speciesMaxLifespan = rand.nextInt(maxSpeciesLifespan);
		while(speciesMaxLifespan <= 0){
			this.speciesMaxLifespan = rand.nextInt(maxSpeciesLifespan);
		}
	}

	/**
	 * Calculates a random fitness value (double) 
	 * @return A double value of 0.0 up to (and not 
	 * including) 1.0.
	 */
	private void calcFitness(){
		this.speciesFitness = rand.nextDouble();
	}

	
}
