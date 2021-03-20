/**
 * Tests for a Sphere class
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Sphere;
import primitives.*;

/**
 * @author Adiel
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {

		// ============ Equivalence Partitions Tests ==============
		Sphere sp = new Sphere(new Point3D(0, 0, 1), 5);
		double sqrt2 = Math.sqrt(2);
		// Standard normal test
		assertEquals("Bad normal to sphere", new Vector(1 / sqrt2, 0, -1 / sqrt2), sp.getNormal(new Point3D(1, 0, 0)));
	}

}
