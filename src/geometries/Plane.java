package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Plane class represents a plane using a point in space and a vertical vector
 * space.
 * 
 * @author Adiel
 *
 */
public class Plane implements Geometry {

	final Point3D q0;
	final Vector normal;

	/**
	 * Plane constructor receive the point and vertical
	 * 
	 * @param q0 the point
	 * @param normal the vertical
	 */
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
	}

	/**
	 * Plane constructor receive 3 3D point values
	 * 
	 * @param p1 for the first point
	 * @param p2 for the second point
	 * @param p3 for the third point
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this.q0=p1;
        Vector v1=p2.subtract(p1);
        Vector v2=p3.subtract(p1);
        Vector n =v1.crossProduct(v2);
        this.normal=n.normalize();

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

}
