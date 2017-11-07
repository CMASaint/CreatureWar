
public class SpeciesGenerator1 extends SpeciesGenerator{
	
	/** An array of MaxLife values. */
	private int[] speciesLifeArray;
	/** An array of Fitness values. */
	private double[] speciesFitnessArray;

	/**
	 * Stores the values for int speciesCount, double[] 
	 * fitnessChart, the World object and the Creature[][] 
	 * creatureWorld. It then calls the generator method. 
	 * In this case the generator method generates Creature 
	 * objects with predefined values for fitness and 
	 * MaxLifespan. These species cannot create new Creature
	 * objects off the edge of the array.
	 * @param speciesCount the number of species required.
	 * @param world the world the creatures will inhabit
	 */
	public SpeciesGenerator1(World world) {
		super(world);
		this.generator();
	}
	
	/**
	 * Generates a number of Creature objects (equal to speciesCount) 
	 * with MaxLifespan and fitness values from the speciesLifeArray 
	 * and speciesFitnessArray. These values must be determined in the
	 * createLifeArray and createFitnessArray methods.
	 */
	protected synchronized void generator(){
		speciesLifeArray = createLifeArray();
		speciesFitnessArray = createFitnessArray();
		checkUniqueFitness();
		if(speciesLifeArray.length == speciesFitnessArray.length){
			try{
				for(int speciesCount = 0 ; speciesCount<speciesLifeArray.length; speciesType++, speciesCount++){
					speciesMaxLifespan = speciesLifeArray[speciesCount];
					speciesFitness = speciesFitnessArray[speciesCount];
					this.instantiateSpecies();
				}
			}
			catch(ArrayIndexOutOfBoundsException e){	}
			catch(NullPointerException e){	}
		}
		else{
			System.err.println("There are not enough values assigned for \n"
					+ "speciesLifeArray or speciesFitnessArray, \n"
					+ "please look at the SpeciesGenerator1 class \n"
					+ "and add more values to the relevant arrays \n"
					+ "or use the SpeciesGenerator2 class instead.");
			System.exit(0);
		}
	}
	
	protected void instantiateSpecies(){
		Creature1 nextSpecies = new Creature1(speciesMaxLifespan, speciesFitness, speciesType);
		nextSpecies.placeCreature();
	}
	

	/**
	 * Sets the values for maxLifespan in an int array. 
	 * @return The int array containing predetermined values 
	 * for maxLifespan.
	 */
	private int[] createLifeArray(){
		int [] LifeArray = {10, 5};
		return LifeArray;
	}
	
	/**
	 * Sets the values for fitness in a double array.
	 * @return The double array containing predetermined values
	 * for fitness.
	 */
	private double[] createFitnessArray(){
		double [] FitnessArray = {0.8, 0.4};
		return FitnessArray;
	}
	
	/**
	 * Checks the fitness values in the speciesFitnessArray are 
	 * all unique as species are currently distinguished by fitness.
	 */
	private void checkUniqueFitness(){
		for(int i = 0; i<speciesLifeArray.length; i++){
			double nextValue = speciesFitnessArray[i];
			for(int j = 0; j<speciesLifeArray.length; j++){
				if(j!=i){
					if(nextValue==speciesFitnessArray[j]){
						System.err.println("Fitness values must be unique, "
								+ "please change \nthe values in the SpeciesGenerator "
								+ "class.");
						System.exit(0);
					}
				}
			}
		}
	}
	
	
}
