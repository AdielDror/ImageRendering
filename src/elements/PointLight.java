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
	private double kC;
	private double kL;
	private double kQ;

	/** 
	 * PointLight constructor that initializes all the fields
	 * @param intensity for the intensity
	 * @param position for the position
	 * @param kC for the attenuation factor Kc
	 * @param kL for the attenuation factor kl
	 * @param kQ for the attenuation factor Kq
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		this.position = position;
		this.kC = kC;
		this.kL = kL;
		this.kQ = kQ;
	}

	@Override
	public Color getIntensity(Point3D p) {

		return getIntensity().reduce(kC + kL * p.distance(position) + kQ * p.distanceSquared(position));
	}

	@Override
	public Vector getL(Point3D p) {

		return p.subtract(position).normalize();
	}

	
}
