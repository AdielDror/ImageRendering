package primitives;

/**
 * A class representing a material of the body
 * 
 * @author Adiel
 *
 */
public class Material {

	public double kD=0.0;
	public double kS=0.0;
	public int nShininess=0;
	
	/** Setter for kD
	 * @param kD the kD to set
	 * @return the object Material itself
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}
	
	/**
	 * Setter for kS
	 * @param kS the kS to set
	 * @return the object Material itself 
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}
	
	/**
	 * Setter for nShininess
	 * @param nShininess the nShininess to set
	 * @return the object itself
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	
	
}
