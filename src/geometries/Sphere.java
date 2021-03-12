package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Sphere class represent sphere by center point radius.
 *
 * @author Adiel
 *
 */
public class Sphere implements Geometry {

	final Point3D center;
	final double radius;

	/**
	 * Sphere constructor receiving 2 values
	 * 
	 * @param center of the Sphere
	 * @param radius of the Sphere
	 */
	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	/**
	 * 
	 * @return the center of the Sphere
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * 
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return center + ", " + radius;
	}

	@Override
	public Vector getNormal(Point3D point3d) {

		return null;
	}

}
