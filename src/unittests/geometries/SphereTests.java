/**
 * Tests for a Sphere class
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Sphere;
import primitives.*;
import static primitives.Point3D.ZERO;

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
		Sphere sp = new Sphere(1d, new Point3D(0, 0, 1));
		double sqrt2 = Math.sqrt(2);
		// Standard normal test
		assertEquals("Bad normal to sphere", new Vector(1 / sqrt2, 0, -1 / sqrt2),
				sp.getNormal(new Point3D(1, 0, 0)));
	}

	/**
	 * Test method for
	 * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray's line is outside the sphere (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), 
						new Vector(1, 1, 0))));

		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
		List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
				new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);

		// TC03: Ray starts inside the sphere (1 point)
		Point3D p3 = new Point3D(1.6851646544245034, 0.7283882181415011, 0);
		List<Point3D> result1 = sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0),
				new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 1, result1.size());
		assertEquals("Ray coming out of a sphere", List.of(p3), result1);

		// TC04: Ray starts after the sphere (0 points)
		assertNull("Ray's line after the sphere",
				sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
						new Vector(-2, -1, 0))));

		// =============== Boundary Values Tests ==================

		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC11: Ray starts at sphere and goes inside (1 points)
		Point3D p4 = new Point3D(1.6851646544245034, 0.7283882181415011, 0);
		List<Point3D> result2 = sphere.findIntersections(new Ray(
				new Point3D(0.4, 0.5, 0), new Vector(3, 1, 0)));
		assertEquals("Wrong number of points", 1, result2.size());
		assertEquals("Ray coming out of a sphere", List.of(p4), result1);

		// TC12: Ray starts at sphere and goes outside (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(new Point3D(0, 0, 1),
						new Vector(-1, 0, 0))));

		// **** Group: Ray's line goes through the center
		// TC13: Ray starts before the sphere (2 points)
		Point3D p6=new Point3D(2, 0, 0);
		
		List<Point3D> result3 = sphere.findIntersections(new Ray(new Point3D(2.5, 0, 0), 
				new Vector(-1, 0, 0)));
		assertEquals("Wrong number of points", 2, result3.size());
		if (result3.get(0).getX() > result3.get(1).getX())
			result3 = List.of(result3.get(1), result3.get(0));
		assertEquals("Ray crosses sphere", List.of(ZERO, p6), result3);

		// TC14: Ray starts at sphere and goes outside (1 points)
		List<Point3D> result4 = sphere.findIntersections(new Ray(new Point3D(0.4, 0, 0),
				new Vector(-1, 0, 0)));
		assertEquals("Wrong number of points", 1, result4.size());
		assertEquals("Ray coming out of a sphere", List.of(ZERO), result4);

		// TC15: Ray starts inside (1 points)
		List<Point3D> result5 = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
				new Vector(-1, 0, 0)));
		assertEquals("Wrong number of points", 1, result5.size());
		assertEquals("Ray coming out of a sphere", List.of(ZERO), result5);

		// TC16: Ray starts at the center (1 points)
		List<Point3D> result6 = sphere.findIntersections(new Ray(new Point3D(1, 0, 0),
				new Vector(-1, 0, 0)));
		assertEquals("Wrong number of points", 1, result6.size());
		assertEquals("Ray coming out of a sphere", List.of(ZERO), result6);

		// TC17: Ray starts at sphere and goes outside (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(
						new Point3D(0, 0, 1), new Vector(-1, 0, 1))));

		// TC18: Ray starts after sphere (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(
						new Point3D(-1, 0, 0), new Vector(-2, 0, 0))));

		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC19: Ray starts before the tangent point (0 points)
		assertNull("Ray's line out of sphere", sphere.findIntersections(new Ray( new
		  Point3D(0, 0, -1), new Vector(0, 1, 0))));
		 
		  
		 
		// TC20: Ray starts at the tangent point (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(
						new Point3D(1, 1, 0), new Vector(3, 2, 1))));

		// TC21: Ray starts after the tangent point (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(new Ray(
						new Point3D(1, 1, 0), new Vector(1, 0, 0))));

		// **** Group: Special cases
		// TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line (0 points)
		assertNull("Ray's line out of sphere",
				sphere.findIntersections(
						new Ray(new Point3D(-1, 0, 0), new Vector(-1, 1, 0))));
	}

}
