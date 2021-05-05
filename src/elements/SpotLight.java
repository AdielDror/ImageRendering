
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class representing spot lighting
 * 
 * @author Adiel
 *
 */
public class SpotLight extends PointLight {

	private Vector direction;

	/**
	 * SpotLight constructor
	 * 
	 * @param intensity for the lighting intensity
	 * @param position for the lighting location
	 * @param direction for the lighting direction
	 * @param kC for the attenuation factor Kc 
	 * @param kL for the attenuation factor kl 
	 * @param kQ for the attenuation factor Kq 
	 */                                         
	public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		this.direction = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		Vector l = getL(p);

		if (l == null) {
			return Color.BLACK;
		}

		double d = direction.dotProduct(l);
		if (d <= 0) {
			return Color.BLACK;
		}

		return super.getIntensity(p).scale(d);
	}

	
}
