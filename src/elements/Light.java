/**
 * 
 */
package elements;

import primitives.Color;

/**
 * An abstract class representing lighting
 * 
 * @author Adiel
 * 
 */
 abstract class Light {
	 
	 private Color intensity;

	/**
	 * Light constructor
	 * @param intensity for the light intensity
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
