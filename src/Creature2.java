
public class Creature2 extends Creature{
	
	/**
	 * No additional information as this simply runs the 
	 * superclass constructor.
	 * @param maxLife
	 * @param fit
	 */
	public Creature2(int maxLife, double fit, int speciesType) {
		super(maxLife, fit, speciesType);
	}

	/**
	 * This instantiates a new Creature1 object.
	 * @return A new Creature1 object.
	 */
	protected Creature createChild() {
		Creature2 nextGen = new Creature2(maxLifespan, fitness, speciesType);
		return nextGen;
	}
	
	/**
	 * This allows the Creature to check locations on the opposite side of 
	 * the array when in an edge location in the array.
	 */
	protected void edgeBehaviour(){		
		if(nextR<0){
			nextR = nextR + rows;
		}
		if(nextC<0){
			nextC = nextC + columns;
		}
		if(nextC>=columns){
			nextC = nextC - columns;
		}
		if(nextR>=rows){
			nextR = nextR - rows;
		}
		nextCreature = creatureWorld[nextR][nextC];
		giveBirth = this.isBorn(nextCreature);
		this.birth(giveBirth);
	}
	
}