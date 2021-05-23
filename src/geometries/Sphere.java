package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Sphere class represent sphere by center point radius.
 *
 * @author Adiel
 *
 */
public class Sphere extends Geometry {

	final Point3D center;
	final double radius;

	/**
	 * Sphere constructor receiving 2 values
	 * 
	 * @param radius of the Sphere
	 * @param center of the Sphere
	 */
	public Sphere(double radius, Point3D center) {
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
		if (point3d.equals(center))
			throw new IllegalArgumentException("point cannot be the center of the sphere");
		Vector vector = point3d.subtract(center);
		return vector.normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> result = null;
		Point3D P0 = ray.getP0();
		Vector v = ray.getDir();
		// P0==center
		if (center.equals(P0)) {

			return List.of(new GeoPoint(this, ray.getPoint(radius)));

		}

		Vector u = center.subtract(P0);

		double tm = alignZero(u.dotProduct(v));
		double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

		if (d >= radius) {
			return null;
		}
		double th = alignZero(Math.sqrt(radius * radius - d * d));

		double t1 = alignZero(tm - th);
		double t2 = alignZero(tm + th);

		if (t1 < 0 && t2 < 0) {
			return null;
		}

		// if both t1 and t2 grater than 0 that mean we have 2 intersection points
		//and check if the points is farther from the front of the beam than the maximum distance
		if (t1 > 0 && t2 > 0 && alignZero(maxDistance - t1) >= 0 && alignZero(maxDistance - t2) >= 0)
		{

			return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));

		}

		else if (t1 > 0 && alignZero(maxDistance - t1) > 0) {
			return List.of(new GeoPoint(this, ray.getPoint(t1)));

		} else if (t2 > 0 && alignZero(maxDistance - t2) > 0) {
			return List.of(new GeoPoint(this, ray.getPoint(t2)));
		}
		return result;
	}

}
