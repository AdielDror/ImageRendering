package elements;

import primitives.Color;

/**
 * This class for ambient light
 * 
 * @author Adiel
 *
 */
public class AmbientLight {

	final private Color _intensity;

	/**
	 * 
	 * AmbientLight constructor calculates the final power of fill lighting stored in
	 *  a field _intensity
	 *  
	 * @param iA for original fill light (light intensity according to RGB components)
	 * @param kA for coefficient of attenuation of filler light
	 */
	public AmbientLight(Color iA, double kA) {

		this._intensity = iA.scale(kA);
	}

	/**
	 * @return the _intensity
	 */
	public Color getIntensity() {
		return _intensity;
	}

}
