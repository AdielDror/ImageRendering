/**
 * 
 */
package geometries;

import primitives.Point3D;
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
	 * @param geometries for the intersections
	 */
	public Geometries(Intersectable... geometries) {
		_intersectableList = new LinkedList<>();
		add(geometries);

	}

	/**
	 * Adding to list
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		_intersectableList.addAll(Arrays.asList(geometries));

	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		List<Point3D> result = null;

		//A loop that adds to the list all the intersection points that the ray has with 
		//all the geometric bodies
		for (Intersectable item : _intersectableList) {
			List<Point3D> elementList = item.findIntersections(ray);
			if (elementList != null) {
				if (result == null) {
					result = new LinkedList<>();
				}
				result.addAll(elementList);
			}
		}
		return result;
	}
}