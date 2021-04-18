/**
 * Tests for a Tube class
 */
package unittests.geometries;

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
		Tube tb = new Tube(new Ray(new Point3D(1,0,0), new Vector(0,0,1)), 1);
		
		// ============ Equivalence Partitions Tests ==============
		//Standard normal test
		assertEquals("Bad normal to tube", new Vector(1,0,0),tb.getNormal(new Point3D(2,0,60)));

		
		  // =============== Boundary Values Tests ================== 
		//Test in case that the vector p-p0 is normal to direction vector 
		assertEquals("Bad normal to tube", new Vector(1,0,0), tb.getNormal(new Point3D(2,0,0)));
		 
	}

}
