/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Test for the function that returns the point closest to the ray
 * 
 * @author Adiel
 *
 */
public class RayTests {

	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testFindClosestPoint() {
		
		Ray ray=new Ray(new Point3D(1,0,0), new Vector(0,2,0));
		
		// ============ Equivalence Partitions Tests ==============
		
		// TC01: A point in the middle of the list is closest to the beginning of the ray
		List<Point3D> list = List.of(
				new Point3D(4,0,0),
				new Point3D(2,0,0),
				new Point3D(3,0,0));
        
		assertEquals("The point is incorrect",new Point3D(2,0,0), 
				ray.findClosestPoint(list));
		
		
		// =============== Boundary Values Tests ==================
		
		// TC11: Empty list 
		List<Point3D> list1 =List.of();
		  assertNull("List is empty",ray.findClosestPoint(list1));
		 
		
		// TC12: The first point is closest to the beginning of the ray
		List<Point3D> list2  = List.of(
				new Point3D(2,0,0),
				new Point3D(4,0,0),
				new Point3D(3,0,0));
       
		assertEquals("The point is incorrect",new Point3D(2,0,0), 
				ray.findClosestPoint(list2));
		
		// TC13: The last point is closest to the beginning of the ray
		List<Point3D> list3 =List.of(
				new Point3D(3,0,0),
				new Point3D(4,0,0),
				new Point3D(2,0,0)) ;
	
		assertEquals("The point is incorrect",new Point3D(2,0,0), 
				ray.findClosestPoint(list3));
		
	}

	
	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
	 */
	@Test
	public void testGetClosestGeoPoint() {}
}
