package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represent tube by radius and ray.
 * 
 * @author Adiel
 *
 */
public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;

	/**
	 * Tube constructor receiving 2 values
	 * 
	 * @param axisRay of the Tube
	 * @param radius  of the Tube
	 */
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	/**
	 * 
	 * @return the ray
	 */
	public Ray getAxisRay() {
		return axisRay;
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
		return axisRay + ", " + radius;
	}

	@Override
	public Vector getNormal(Point3D point3d) {
		return null;
	}
}
