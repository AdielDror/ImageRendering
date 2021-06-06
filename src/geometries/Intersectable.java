/**
 * 
 */
package geometries;

import primitives.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adiel
 *
 *         Interface for intersection points
 */
public abstract class Intersectable {
	protected Point3D minBoundary;
	protected Point3D maxBoundary;

	/**
	 * Static Internal Auxiliary Class (PDS) that is a tuple of references to a
	 * specific geometry and its intersection point
	 * 
	 *
	 */
	public static class GeoPoint {

		public Geometry geometry;
		public Point3D point;

		/**
		 * GeoPoint constructor for geometry and point
		 * 
		 * @param geometry reference to current geometry
		 * @param point    reference to current point on geometry
		 */
		public GeoPoint(Geometry geometry, Point3D point) {

			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return geometry.equals(other.geometry) && point.equals(other.point);

		}

	}

	/**
	 * The function finds intersection points with the beam within 
	 * the range of the maximum distance
	 * 
	 * @param ray the famous Ray pointing to
	 * @param maxDistance for upper boundary of distance from the ray head 
	 * to the intersection point
	 * @return list of intersection points
	 */
	abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

	/**
	 * Find all intersection points from the ray
	 *
	 * @param ray the famous Ray pointing to
	 * @return intersection points
	 */
	 public List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}
	
	
	/**
	 * A default function for finding GeoPoint's intersection points with a beam
	 * 
	 * @param ray the ray intersects
	 * @return calling the function of finding intersection points with a default parameter
	 */
	  public List<GeoPoint> findGeoIntersections(Ray ray) {
    	return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
}
	 
	 protected abstract void setMaxCoordinates();
	 protected abstract void setMinCoordinates();

	/**
	 * @return the minBoundary
	 */
	public Point3D getMinBoundary() {
		return minBoundary;
	}

	/**
	 * @return the maxBoundary
	 */
	public Point3D getMaxBoundary() {
		return maxBoundary;
	}
	 
	 
	 

}
