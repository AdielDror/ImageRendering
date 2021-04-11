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
		if (point3d.equals(center))
			throw new IllegalArgumentException("point cannot be the center of the sphere");
		Vector vector = point3d.subtract(center);
		return vector.normalize();
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> result = null;
		Point3D P0 = ray.getP0();
		Vector v = ray.getDir();
        //P0==center
		if (center.equals(P0)) {
			
			return List.of(ray.getPoint(radius));

		}

		Vector u = center.subtract(P0);

		double tm = alignZero(u.dotProduct(v));
		double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

		if (d > radius) {
			return null;
		}
		double th = alignZero(Math.sqrt(radius * radius - d * d));

		double t1 = alignZero(tm - th);
		double t2 = alignZero(tm + th);

		if (t1 > 0 && t2 > 0) {
			return List.of(ray.getPoint(t1), ray.getPoint(t2));
		
		} else if (t1 > 0) {
			return List.of(ray.getPoint(t1));
		
		} else if (t2 > 0) {
			return List.of(ray.getPoint(t2));
		}
		
		return result;
	}

}
