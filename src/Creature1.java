
public class Creature1 extends Creature{
	
	/**
	 * No additional information as this simply runs the 
	 * superclass constructor.
	 * @param maxLife
	 * @param fit
	 */
	public Creature1(int maxLife, double fit, int speciesType) {
		super(maxLife, fit, speciesType);
	}
	

	/**
	 * This instantiates a new Creature1 object.
	 * @return A new Creature1 object.
	 */
	protected Creature createChild() {
		Creature1 nextGen = new Creature1(maxLifespan, fitness, speciesType);
		return nextGen;
	}
	
	/**
	 * This method is empty, and therefore no action is taken if
	 * a Creature attempts to check a location not within the array,
	 */
	protected void edgeBehaviour(){		}

}
