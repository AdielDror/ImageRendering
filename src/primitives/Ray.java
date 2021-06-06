package primitives;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * Class Ray is the class representing a set of points on a line that are on one
 * side relative to a given point on a line called, the beginning of the ray.
 * 
 * @author Adiel
 *
 */
public class Ray {

	/**
	 * Fixed for first moving magnitude rays for shading rays
	 */
	private static final double DELTA = 0.1;

	private Point3D p0;
	private Vector dir;
	
	/**
	 * Ray constructor receiving Point and direction and normal
	 * 
	 * @param head for the moving the ray head point by DELTA in direction of the secondary ray
	 * @param direction value for direction Vector
	 * @param normal for the normal vector
	 */
	public Ray(Point3D head, Vector direction, Vector normal) {
		double dn = alignZero(direction.dotProduct(normal));

		this.p0 = head.add(normal.scale(dn > 0 ? DELTA : -DELTA));

		this.dir = direction.normalized();

	}

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

		try {
			if (isZero(t))
				return p0;
			return p0.add(dir.scale(t));
		} catch (IllegalArgumentException e) {
			return p0;
		}

	}

	/**
	 * Calculate the point closest to the beginning of the ray
	 * 
	 * @param list for collection of points
	 * @return the point
	 */
	public Point3D findClosestPoint(List<Point3D> points) {

		Point3D result;

		// Check that the list is not empty
		if (points.size() > 0) {
			result = points.get(0);
			// A loop that goes through on the list
			// and checks what is the closest point to the beginning of the ray
			for (Point3D other : points) {
				if ((p0.distance(other)) < p0.distance(result)) {
					result = other;

				}
			}
			return result;
		}

		return null;
	}

	/**
	 * 
	 * Finding the closest point and geometry to the p0 of the camera
	 * 
	 * @param intersections list of points and geometries, the function should find
	 *                      from this list the closest point and geometry to p0 of
	 *                      the camera in the scene
	 * @return the closest point and geometry to the camera
	 */
	public GeoPoint getClosestGeoPoint(List<GeoPoint> intersections) {
		GeoPoint result;

		// Check that the list is not empty
		if (intersections.size() > 0) {
			result = intersections.get(0);

			// A loop that goes through on the list
			// and checks what is the closest point to the beginning of the ray
			for (GeoPoint other : intersections) {
				if ((other.point.distance(p0)) < result.point.distance(p0)) {
					result = other;

				}
			}
			return result;
		}

		return null;

	}

	
	/**
	 * The function creates a beam of rays
	 * 
	 * @param n for the normal
	 * @param radius of the circuit
	 * @param distance of the point on the body from the center of the circle
	 * @param numOfRays the amount of rays we produce
	 * @return list of rays
	 */
	public List<Ray> generateBeam(Vector n, double radius, double distance, int numOfRays) {
		List<Ray> rays=new LinkedList<Ray>();
		rays.add(this);
		if(numOfRays==1 || isZero(radius))
			return rays;
		Vector nX=dir.craeteNormal();
		Vector nY=dir.crossProduct(nX);
		
		Point3D centerCircle=this.getPoint(distance);
		Point3D randomPoint;
		double x,y,d;
		double nv=alignZero(n.dotProduct(getDir()));
		//A loop that produces rays using random dots
		for(int i=0; i<numOfRays; i++) {
			x = random(-1,1);
			y=Math.sqrt(1-x*x);
			d=random(-radius, radius);
			x=alignZero(x*d);
			y=alignZero(y*d);
			randomPoint=centerCircle;
			if(x!=0)
				randomPoint=randomPoint.add(nX.scale(x));
			if(y!=0)
				randomPoint=randomPoint.add(nY.scale(y));
			
			Vector tPoint=randomPoint.subtract(p0);
			double nt=alignZero(n.dotProduct(tPoint));
			if(nv*nt>0) {
				rays.add(new Ray(p0,tPoint));
			}
		}
		
		return rays;
	}
	
	
	
}
