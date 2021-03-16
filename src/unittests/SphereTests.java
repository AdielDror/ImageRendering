/**
 * 
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
        Sphere sp=new Sphere(new Point3D(0,0,1),5);
        double sqrt3 = Math.sqrt(2);
        assertEquals("Bad normal to sphere",new Vector(1/sqrt3,0,-1/sqrt3),sp.getNormal(new Point3D(1,0,0)));
	}

}
