package primitives;

import java.util.List;

/**
 * Class Ray is the class representing a set of points on a line that are on one
 * side relative to a given point on a line called, the beginning of the ray.
 * 
 * @author Adiel
 *
 */
public class Ray {

	final Point3D p0;
	final Vector dir;

	/**
	 * Ray constructor receiving Point and direction
	 * 
	 * @param p0  value for p0 Point3D
	 * @param dir value for direction Vector
	 */
	public Ray(Point3D p0, Vector dir) {

		this.p0 = p0;
		this.dir = dir.normalized();
	}

	/**
	 * 
	 * @return head of the ray
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * 
	 * @return the direction
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return p0.equals(other.p0) && dir.equals(other.dir);

	}

	@Override
	public String toString() {
		return p0 + ", " + dir;
	}

	/**
	 * Calculate a point on the ray
	 * 
	 * @param t for the point
	 * @return the point
	 */
	public Point3D getPoint(double t) {

		return p0.add(dir.scale(t));
	}

	/**
	 * Calculate the point closest to the beginning of the ray
	 * 
	 * @param list for collection of points
	 * @return the point
	 */
	public Point3D findClosestPoint(List<Point3D> points) {

		Point3D result;
		
		//Check that the list is not empty
		if (points.size() > 0) {
			result = points.get(0);
			//A loop that goes through on the list 
			//and checks what is the closest point to the beginning of the ray
			for (Point3D other : points) {
				if ((p0.distance(other)) < p0.distance(result)) {
					result = other;

				}
			}
			return result;
		}

		return null;
	}

}
