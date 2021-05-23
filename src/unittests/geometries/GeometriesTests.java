/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import primitives.*;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;

/**
 * @author Adiel
 *
 */
public class GeometriesTests {

	/**
	 * Test method for
	 * {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane plane = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));

		Sphere sphere = new Sphere(2, new Point3D(0, 6, 0));
		Triangle triangle = new Triangle(new Point3D(1, 0, 0), new Point3D(3, 2, 0), new Point3D(5, 0, 0));
		Geometries geometries = new Geometries(plane, sphere, triangle);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Some (but not all) shapes are intersected
		Ray ray = new Ray(new Point3D(2, 0.5, 3), new Vector(0, 0, -1));
		List<Point3D> intersections = geometries.findIntersections(ray);
		assertEquals("Some shapes are intersected", 2, intersections.size());

		// =============== Boundary Values Tests ==================

		// TC02: No shape is intersected
		assertNull("No shape is intersected",
				geometries.findIntersections(new Ray(new Point3D(8, 8, 8), new Vector(0, 0, 1))));

		// TC03: Only one shape is intersected
		intersections = geometries.findIntersections(new Ray(new Point3D(0, 6, 0), new Vector(0, 1, 0)));
		assertEquals("Only one shape is intersected", 1, intersections.size());

		// TC04: All shapes are intersected
		sphere = new Sphere(2, new Point3D(3, 0, -3));
		geometries.add(sphere);
		intersections = geometries.findIntersections(ray);
		assertEquals("Some shapes are intersected", 4, intersections.size());

		// TC05: Collection of bodies is empty
		geometries = new Geometries();
		assertNull("No intersection points",
				geometries.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1))));

	}

	
	/**
	 * Test method for
	 * {@link geometries.Geometries#findGeoIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindGeoIntersections() {
		Plane plane = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0),
				new Point3D(0, 0, 1));

		Sphere sphere = new Sphere(2, new Point3D(3, 0, -3));
		Triangle triangle = new Triangle(new Point3D(1, 0, 0), new Point3D(3, 2, 0), 
				new Point3D(5, 0, 0));

		Geometries geometries = new Geometries(plane, sphere, triangle);
		Ray ray = new Ray(new Point3D(2, 0.5, 3), new Vector(0, 0, -1));

		// TC01: All shapes are intersected and all within the maximum distance range
		List<GeoPoint> intersections = geometries.findGeoIntersections(ray, 10);
		assertEquals("Some shapes are intersected", 4, intersections.size());

		// TC02: All shapes are intersected but only one point in the maximum distance
		// range
		List<GeoPoint> intersections1 = geometries.findGeoIntersections(ray, 3);
		assertEquals("Some shapes are intersected", 1, intersections1.size());

		// TC03: All shapes are intersected but only three points in the maximum
		// distance range
		List<GeoPoint> intersections2 = geometries.findGeoIntersections(ray, 5);
		assertEquals("Some shapes are intersected", 3, intersections2.size());

		// TC04: All shapes are intersected but no point in the maximum
		// distance range
		assertNull("No intersection points", geometries.findGeoIntersections(ray, 2));

	}

}
