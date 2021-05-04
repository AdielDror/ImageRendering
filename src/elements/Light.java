/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author Adiel
 * 
 */
 abstract class Light {
	 
	 private Color intensity;

	/**
	 * Light constructor
	 * @param intensity
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * Getter the intensity
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

	

}
