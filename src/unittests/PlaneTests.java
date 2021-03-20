/**
 *Tests for a Plane class 
 */
package unittests;

import static org.junit.Assert.*;

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

}
