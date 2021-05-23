/**
 * 
 */
package geometries;


import primitives.Ray;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Adiel
 *
 *         Composite class for all Intersectable objects
 */
public class Geometries implements Intersectable {
	private List<Intersectable> _intersectableList;

	/**
	 * Default constructor that initializes the field with an empty list
	 */
	public Geometries() {
		_intersectableList = new LinkedList<>();
	}

	/**
	 * Constructor for the geometries
	 * 
	 * @param geometries for the intersections
	 */
	public Geometries(Intersectable... geometries) {
		_intersectableList = new LinkedList<>();
		add(geometries);

	}

	/**
	 * Adding to list
	 * 
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		_intersectableList.addAll(Arrays.asList(geometries));

	}

	

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> intersections = null;
	
		//Go over all the geometries and find intersections with the ray
		for (Intersectable geometry : _intersectableList ) {
			var geoIntersectoions = geometry.findGeoIntersections(ray,maxDistance);
			if (geoIntersectoions != null) {
				if(intersections==null) {
					intersections=new LinkedList<>(); 
				
				}
				intersections.addAll(geoIntersectoions);
			}
		}
		return intersections;
	}


}