package primitives;

/**
 * A class representing a material of the body
 * 
 * @author Adiel
 *
 */
public class Material {

	/**
	 * Transparency coefficient
	 */
	public double kT = 0.0;
	/**
	 * Reflection coefficient
	 */
	public double kR = 0.0;

	/**
	 * Diffuse coefficient
	 */
	public double kD = 0.0;
	
	/**
	 * specular coefficient
	 */
	public double kS = 0.0;
	/**
	 * The exponent of the material
	 */
	public int nShininess = 0;

	/**
	 * Setter for kD
	 * 
	 * @param kD the kD to set
	 * @return the object Material itself
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter for kS
	 * 
	 * @param kS the kS to set
	 * @return the object Material itself
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Setter for nShininess
	 * 
	 * @param nShininess the nShininess to set
	 * @return the object itself
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

	/**
	 * Setter for kT
	 * 
	 * @param kT the kT to set for the transparency coefficient
	 * @return the object itself
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * Setter for kR
	 * 
	 * @param kR the kR to set for the reflection coefficient
	 * @return the object itself
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}
}
