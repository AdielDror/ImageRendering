package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * A class representing point lighting
 * 
 * @author Adiel
 *
 */
public class PointLight extends Light implements LightSource {

	private Point3D position;
	private double kC = 1;
	private double kL = 0;
	private double kQ = 0;

	/**
	 * PointLight constructor that initializes all the fields
	 * 
	 * @param intensity for the intensity
	 * @param position  for the position
	 *
	 */
	public PointLight(Color intensity, Point3D position) {
		super(intensity);
		this.position = position;

	}

	/**
	 * @param kC the kC to set
	 * @return the object PointLight itself
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * @param kL the kL to set
	 * @return the object PointLight itself
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	
	 /**
	 * 
	 * @param kQ the kQ to set
	 * @return the object PointLight itself
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point3D p) {

		return getIntensity().reduce(kC + kL * p.distance(position) + kQ * p.distanceSquared(position));
	}

	@Override
	public Vector getL(Point3D p) {

		return p.subtract(position).normalized();
	}

	@Override
	public double getDistance(Point3D point) {
   
		return position.distance(point);
	}

}
