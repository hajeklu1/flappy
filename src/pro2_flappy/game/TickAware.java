package pro2_flappy.game;

/**
 * 
 * @author plasido1
 *
 */
public interface TickAware {
	
	/**
	 * �as od za��tku spu�t�n�
	 * @param ticksSinceStart
	 */
	void tick(long ticksSinceStart);

}
