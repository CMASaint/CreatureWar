
public class SpeciesGenerator1Jump extends SpeciesGenerator1{

	/**
	 * Stores the values for int speciesCount, double[] 
	 * fitnessChart, the World object and the Creature[][] 
	 * creatureWorld. It then calls the generator method. 
	 * In this case the generator method generates Creature 
	 * objects with predefined values for fitness and 
	 * MaxLifespan. These species can all create new Creature
	 * objects on the opposite side of the array when on
	 * an edge of the array.
	 * @param speciesCount the number of species required.
	 * @param world the world the creatures will inhabit
	 */
	public SpeciesGenerator1Jump(World world) {
		super(world);
	}
	
	protected void instantiateSpecies(){
		Creature2 nextSpecies = new Creature2(speciesMaxLifespan, speciesFitness, speciesType);
		nextSpecies.placeCreature();
	}

}
