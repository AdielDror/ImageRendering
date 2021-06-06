package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class representing directional lighting
 * 
 * @author Adiel
 *
 */
public class DirectionalLight extends Light implements LightSource {
	
	
	private Vector direction;

	/**
	 * DirectionalLight constructor initializes directional light with it's intensity and direction
	 *  
	 * @param intensity for the light
	 * @param direction for direction vector
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		
		return super.getIntensity();
	}

	@Override
	public Vector getL(Point3D p) {
		
		return direction;
	}

	@Override
	public double getDistance(Point3D point) {
		
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public double getRadius() {
		
		return 0;
	}

	@Override
	public int getNumOfRays() {
		
		return 0;
	}
	

}
