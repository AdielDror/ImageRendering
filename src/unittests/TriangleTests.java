/**
 *Tests for a Triangle class 
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Triangle;
import primitives.*;

/**
 * @author Adiel
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {

		// ============ Equivalence Partitions Tests ==============
		Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		//Standard normal test
		assertEquals("Bad normal to triangle", new Vector(sqrt3, sqrt3, sqrt3), tr.getNormal(new Point3D(0, 0, 1)));
	}

}
