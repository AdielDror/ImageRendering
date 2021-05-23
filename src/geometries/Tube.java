package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

import java.util.List;

/**
 * Tube class represent tube by radius and ray.
 * 
 * @author Adiel
 *
 */
public class Tube extends Geometry {

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
		Point3D p0 = axisRay.getP0();
		Vector v = axisRay.getDir();

		Vector p_p0 = point3d.subtract(p0);

		double t = v.dotProduct(p_p0);
		if (isZero(t)) {
			return p_p0.normalize();
		}

		Point3D O = p0.add(v.scale(t));

		if (O.equals(point3d))
			throw new IllegalArgumentException("point cannot be the reference point of the tube");

		Vector vector = point3d.subtract(O);
		return vector.normalize();
	}

	

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}
}
