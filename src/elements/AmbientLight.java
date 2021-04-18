package elements;

import primitives.Color;


public class AmbientLight {
final Color _intensity;

/**
 * @param _intensity
 */
public AmbientLight(Color iA,double kA) {
	
	this._intensity = iA.scale(kA);
}

/**
 * @return the _intensity
 */
public Color getIntensity() {
	return _intensity;
}


}
