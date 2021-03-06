package elements;

import primitives.Color;

/**
 * This class for ambient light
 * 
 * @author Adiel
 *
 */
public class AmbientLight extends Light {

	/**
	 * 
	 * AmbientLight constructor calculates the final power of fill lighting stored
	 * in a field _intensity in superClass
	 * 
	 * @param iA for original fill light (light intensity according to RGB
	 *           components)
	 * @param kA for coefficient of attenuation of filler light
	 */
	public AmbientLight(Color iA, double kA) {
		super(iA.scale(kA));

	}
	
	/**
	 * Default constructor
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}

}
