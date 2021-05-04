package primitives;

public class Material {

	public double kD=0.0;
	public double kS=0.0;
	public int nShininess=0;
	
	/**
	 * @param kD the kD to set
	 * @return 
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}
	/**
	 * @param kS the kS to set
	 * @return 
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}
	/**
	 * @param nShininess the nShininess to set
	 * @return 
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	
	
}
