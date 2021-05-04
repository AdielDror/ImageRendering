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
 * Interface for intersection points
 */
public interface Intersectable {
	/**
	 * Static Internal Auxiliary Class (PDS) that is a tuple of references to a specific geometry 
	 * and its intersection point
	 * 
	 *
	 */
	public static class GeoPoint{
		public Geometry geometry;
		public Point3D point;
		
		/**
		 * GeoPoint constructor for geometry and point
		 * 
		 * @param geometry reference to current geometry
		 * @param point reference to current point on geometry
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
	 * Find all intersection points from the ray with the geometry
	 * 
	 * @param ray the famous Ray pointing to
	 * @return list of intersection points
	 */
	List<GeoPoint> findGeoIntersections(Ray ray); 
    
	/**
     * Find all intersection points from the ray
     *
     * @param ray the famous Ray pointing to
     * @return intersection points
     */
   default List<Point3D> findIntersections(Ray ray){
	   var geoList=findGeoIntersections(ray);
	   return geoList==null ? null : geoList.stream().map(gp->gp.point).collect(Collectors.toList());
   }
}
