/**
 * Tests for a Point3D class
 */
package unittests.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;

/**
 * @author Adiel
 *
 */
public class Point3DTests {

	Point3D p1 = new Point3D(1, 2, 3);

	/**
	 * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {

		// ============ Equivalence Partitions Tests ==============
       //Test add result is correct
		assertTrue("ERROR: Point + Vector does not work correctly",
				Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))));
	}

	/**
	 * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {

		// ============ Equivalence Partitions Tests ==============
		// Test subtract result is correct
		assertTrue("ERROR: Point - Point does not work correctly",
				new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)));
	}

	/**
	 * Test method for
	 * {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
	 */
	@Test
	public void testDistanceSquared() {

		// ============ Equivalence Partitions Tests ==============
       // Test distance squared result is correct
		assertEquals("ERROR: DistanceSquared of point does not work correctly",
				new Point3D(2, 3, 4).distanceSquared(p1), 3, 0.0001);

	}

	/**
	 * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistance() {

		// ============ Equivalence Partitions Tests ==============
		double sqrt1 = Math.sqrt(3);

		// Test distance result is correct
		assertEquals("ERROR: Distance of point does not work correctly", 
				new Point3D(2, 3, 4).distance(p1), sqrt1,
				0.0001);

	}

}
