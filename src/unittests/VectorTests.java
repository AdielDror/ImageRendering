/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

import static primitives.Util.isZero;;

/**
 * @author idovi
 *
 */
public class VectorTests {



	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(4,5,6);
		Vector v3 = v1.add(v2);
		assertEquals("add() wrong result add",new Vector(5,7,9),v3);
		
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(4,5,6);
		Vector v3 = v1.subtract(v2);
		assertEquals("subtract() wrong result subtract",new Vector(3,3,3),v3);
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(1,2,3);
		assertEquals("scale() wrong result scale",new Vector(5,10,15),v1.scale(5));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		    Vector v1 = new Vector(1, 2, 3);
	        Vector v2 = new Vector(-2, -4, -6);

	        // ============ Equivalence Partitions Tests ==============
	        Vector v3 = new Vector(0, 3, -2);
	        Vector vr = v1.crossProduct(v3);

	        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
	        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

	        // Test cross-product result orthogonality to its operands
	        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
	        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

	        // =============== Boundary Values Tests ==================
	        // test zero vector from cross-productof co-lined vectors
	        try {
	            v1.crossProduct(v2);
	            fail("crossProduct() for parallel vectors does not throw an exception");
	        } catch (Exception e) {}

	}

	
	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(4,5,6);
		Vector v3 = new Vector(0,-3,2);
		assertTrue("dotProduct() wrong result dotProduct",isZero(v1.dotProduct(v3)));
		assertTrue("dotProduct() wrong result dotProduct",isZero(v1.dotProduct(v2) - 32));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		Vector v1 = new Vector(1,2,3);
		assertTrue("lengthSquared() wrong result lengthSquared",isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		Vector v1 = new Vector(0,3,4);
		assertTrue("length() wrong result length",isZero(v1.length() - 5));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v1 = new Vector(0,4,0);
		Vector v3=new Vector(v1.getHead());
		Vector v2 = v3.normalize();
		assertEquals("ERROR: normalize() function creates a new vector",v2, v3);
		assertTrue("ERROR: normalize() result is not a unit vector",isZero(v2.length() - 1));
		
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v1 = new Vector(0,4,0);
		Vector v2 = v1.normalized();
		assertTrue("ERROR: normalized() function does not create a new vector",!v1.equals(v2));
		
	}

	

}
