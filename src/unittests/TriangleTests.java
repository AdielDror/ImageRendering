/**
 *Tests for a Triangle class 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

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
		assertEquals("Bad normal to triangle",
				new Vector(sqrt3, sqrt3, sqrt3), 
				tr.getNormal(new Point3D(0, 0, 1)));
	}
	
	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	 @Test
	   public void findIntersections() {
	        Triangle tr=new Triangle(
	                new Point3D(1,0,0),
	                new Point3D(3,2,0),
	                new Point3D(5,0, 0));

	        // ============ Equivalence Partitions Tests ==============
	        //TC01: The point inside the triangle (1 point)
	        Point3D p1=new Point3D(2,0.5,0);
	        List<Point3D> result=tr.findIntersections(new Ray(
	                new Point3D(2,0.5,1),new Vector(0,0,-1)));
	        assertEquals("Wrong number of points",1, result.size());
	        assertEquals( "Ray crosses triangle",List.of(p1), result);
	        //TC02: The point outside the triangle, against edge
	        assertNull("Ray not intersects the triangle",tr.findIntersections(new Ray(
	                        new Point3D(1.5, 1.0, 0),
	                        new Vector(0, 0, -1))));
	        //TC03: The point outside the triangle, against vertex (0 point)
	        assertNull("Ray not intersects the triangle",tr.findIntersections(new Ray(
	                        new Point3D(0.5, 0, 0),
	                        new Vector(0, 0, -1))));

	        // =============== Boundary Values Tests ==================
	        //TC04: The point on edge (0 points)
	        assertNull("Ray not intersects the triangle",tr.findIntersections(new Ray(
	                        new Point3D(2, 0, 0),
	                        new Vector(0, 0, -1))));

	        //TC05: The point in vertex (0 points)
	        assertNull( "Ray not intersects the triangle",tr.findIntersections(new Ray(
	                        new Point3D(3, -2, 0),
	                        new Vector(0, 0, -1))));

	        //TC06: The point on edge's continuation (0 points)
	        assertNull("Ray not intersects the triangle",tr.findIntersections(new Ray(
	                        new Point3D(6, 0, 0),
	                        new Vector(0, 0, -1))));
	    }

}
