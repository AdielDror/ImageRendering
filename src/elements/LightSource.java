package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Interface for lighting sources
 * 
 * @author Adiel
 *
 */
public interface LightSource {
	
	/**
	 * Method for lighting intensity
	 * 
	 * @param p for the point where the ray struck
	 * @return color of the lighting intensity
	 */
	public Color getIntensity(Point3D p);
	
	/**
	 * Method for the vector from the camera to the point
	 * 
	 * @param p for the point
	 * @return normalized vector
	 */
	public Vector getL(Point3D p);

	/**
	 * 
	 * @param point
	 * @return
	 */
	double getDistance(Point3D point);
}
