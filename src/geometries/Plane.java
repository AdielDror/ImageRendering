package geometries;

import java.util.List;
import static primitives.Util.*;

import primitives.*;

/**
 * Plane class represents a plane using a point in space and a vertical vector
 * space.
 * 
 * @author Adiel
 *
 */
public class Plane extends Geometry {

	final Point3D q0;
	final Vector normal;

	/**
	 * Plane constructor receive the point and vertical
	 * 
	 * @param q0     the point
	 * @param normal the vertical
	 */
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal.normalized();
	}

	/**
	 * Plane constructor receive 3 3D point values
	 * 
	 * @param p1 for the first point
	 * @param p2 for the second point
	 * @param p3 for the third point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this.q0 = p1;
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		Vector n = v1.crossProduct(v2);
		this.normal = n.normalized();
		setMaxCoordinates();
		setMinCoordinates();

	}

	/**
	 * 
	 * @return the point in space
	 */
	public Point3D getQ0() {
		return q0;
	}

	/**
	 * 
	 * @return the normal, a vertical to the point
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public String toString() {
		return q0 + ", " + normal;
	}

	@Override
	public Vector getNormal(Point3D point3d) {

		return normal;
	}

	
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> result = null;

		// Check if q0=p0
		if (q0.equals(ray.getP0())) {
			return null;
		}

		double numerator = alignZero(normal.dotProduct(q0.subtract(ray.getP0())));
		double denominator = alignZero(normal.dotProduct(ray.getDir()));

		// Check if numerator or denominator equal zero
		if (isZero(numerator) || isZero(denominator)) {
			return null;
		}

		double t = numerator / denominator;
		if (t <= 0) {
			return result;// result=null
		}
		
		//Check if the point is farther from the head of the ray than the maximum distance
		if (alignZero(t-maxDistance)<=0) {
			
			return List.of(new GeoPoint(this, ray.getPoint(t)));
		}

		return null;

	}

	@Override
	protected void setMaxCoordinates() {
		// TODO Auto-generated method stub
		double x = Double.POSITIVE_INFINITY, y = x, z = y;
		maxBoundary = new Point3D(x, y, z);
		
	}

	@Override
	protected void setMinCoordinates() {
		// TODO Auto-generated method stub
		double x = Double.NEGATIVE_INFINITY, y = x, z = y;
		minBoundary = new Point3D(x, y, z);
		
	}

}
