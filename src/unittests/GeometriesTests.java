/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import primitives.*;

import org.junit.Test;

import geometries.*;

/**
 * @author Adiel
 *
 */
public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane plane = new Plane(
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(0, 0, 1));

        Sphere sphere = new Sphere(2, new Point3D(0, 6, 0));
        Triangle triangle = new Triangle(
                new Point3D(1, 0, 0),
                new Point3D(3, 2, 0),
                new Point3D(5, 0, 0));
        Geometries geometries = new Geometries(plane, sphere, triangle);

        // ============ Equivalence Partitions Tests ==============

        //TC01: Some (but not all) shapes are intersected
        Ray ray = new Ray(new Point3D(2, 0.5, 3), new Vector(0, 0, -1));
        List<Point3D> intersections = geometries.findIntersections(ray);
        assertEquals("Some shapes are intersected",2, intersections.size());

        // =============== Boundary Values Tests ==================

        //TC02: No shape is intersected
        assertNull( "No shape is intersected",geometries.findIntersections(new Ray(
                        new Point3D(8, 8, 8),
                        new Vector(0, 0, 1))));

        //TC03: Only one shape is intersected
        intersections = geometries.findIntersections(new Ray(
                new Point3D(0, 6, 0),
                new Vector(0, 1, 0)));
        assertEquals("Only one shape is intersected",1, intersections.size());


        //TC04: All shapes are intersected
        sphere = new Sphere(2, new Point3D(3, 0, -3));
        geometries.add(sphere);
        intersections = geometries.findIntersections(ray);
        assertEquals("Some shapes are intersected",4, intersections.size());

        //TC05: Collection of bodies is empty
        geometries = new Geometries();
        assertNull("No intersection points",geometries.findIntersections(new Ray(
                        new Point3D(0, 0, 0),
                        new Vector(0, 0, 1))));


    }
		
}
