/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import geometries.Tube;
import primitives.*;


/**
 * @author Adiel
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Tube tb = new Tube(new Ray(new Point3D(0, 0, 1), new Vector(new Point3D(1, 2, 3))), 5);
		// ============ Equivalence Partitions Tests ==============

		double sqrt2 = Math.sqrt(162);
		
		assertEquals("Bad normal to tube", new Vector(3 / sqrt2, 3 / sqrt2, 12 / sqrt2),
				tb.getNormal(new Point3D(0, 3, -2)));

		// =============== Boundary Values Tests ==================
		double sqrt = Math.sqrt(13);
		assertEquals("Bad normal to tube", new Vector(0, 3 / sqrt, -2 / sqrt), tb.getNormal(new Point3D(0, 3, -1)));

	}

}
