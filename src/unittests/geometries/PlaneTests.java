/**
 *Tests for a Plane class 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Plane;
import primitives.*;

/**
 * @author Adiel
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPlane() {

		// ============ Equivalence Partitions Tests ==============
		Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		Vector vPlus = new Vector(sqrt3, sqrt3, sqrt3);
		Vector vMinus = new Vector(-sqrt3, -sqrt3, -sqrt3);
		Vector normal = pl.getNormal(new Point3D(0, 0, 1));
		// Simple test for normal
		assertTrue("Bad normal to plane", vPlus.equals(normal) || vMinus.equals(normal));
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {

		// =============== Boundary Values Tests ==================
		// The first and second point become united
		try {
			new Plane(new Point3D(0, 0, 1), new Point3D(0, 0, 1), new Point3D(0, 1, 0));
			fail("The first and second point the same");
		} catch (IllegalArgumentException e) {
		}

		// The points on the same line
		try {
			new Plane(new Point3D(-1, 0, 2), new Point3D(1, 1, 4), new Point3D(3, 2, 6));
			fail("The dots are on the same line");
		} catch (IllegalArgumentException e) {
		}

	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane plane = new Plane(new Point3D(1, 0, 0),
				new Point3D(0, 1, 0),
				new Point3D(0, 0, 1));
		
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray intersects the plane (1 point)
		Point3D p1 = new Point3D(1, 0, 0);
		List<Point3D> result = plane.findIntersections(new Ray(
				new Point3D(-1, 0, 0),
				new Vector(1, 0, 0)));
		assertEquals("Result is wrong", 1, result.size());
		assertEquals("Ray intersects the plane", List.of(p1), result);

		// TC02: Ray does not intersect the plane (0 point)
		assertNull("Ray not intersects the plane",
				plane.findIntersections(new Ray(
						new Point3D(-1, 0, 0),
						new Vector(-1, -1, 0))));

		// =============== Boundary Values Tests ==================

		// **** Group: Ray is parallel to the plane
		// TC03: The ray included in the plane
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(-1, 0, 0),
				new Vector(1, -1, 0))));

		// TC04: The ray not included in the plane
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(-1, 0, 0),
				new Vector(-2, 0, 0))));

		// **** Group: Ray is orthogonal to the plane
		// TC05: The point before the plane
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(-1, 0, 0), 
				new Vector(0, -2, 0))));
		// TC06: The point in the plane
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(0, 0, 1),
				new Vector(1, 1, 1))));
		// TC07: The point after the plane
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(3, 2, 2),
				new Vector(1, 1, 1))));

		// **** Group: Ray is neither orthogonal nor parallel to the plane
		// TC08: The ray begins at the plane (p0 is in the plane, but not the ray)
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(1.5, 1.5, 0), 
				new Vector(2, 2, -3))));
		// TC09: The ray begins in the same point which appears as reference point in
		// the plane (Q)
		assertNull("Ray not intersects the plane",plane.findIntersections(new Ray(
				new Point3D(1, 0, 0),
				new Vector(1, 1, -2))));
	}
}
