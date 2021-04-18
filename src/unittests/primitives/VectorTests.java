/**
 *Tests for a Vector class 
 */
package unittests.primitives;

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
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(4,5,6);
		Vector v3 = v1.add(v2);
		//Test add result is correct
		assertEquals("add() wrong result add",new Vector(5,7,9),v3);
		
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(4,5,6);
		Vector v3 = v1.subtract(v2);
		//Test subtract result is correct
		assertEquals("subtract() wrong result subtract",new Vector(3,3,3),v3);
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1,2,3);
		//Test scale result is correct
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
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0,-3,2);
		Vector v4 = new Vector(0,3,2);
		
		// ============ Equivalence Partitions Tests ==============
		//Test in case the angle is sharp
		assertTrue("dotProduct() wrong result dotProduct",isZero(v1.dotProduct(v4) -12));
		
		//Test in case the angle is blunt
		assertTrue("dotProduct() wrong result dotProduct",isZero(v2.dotProduct(v4) +24));
		
		// =============== Boundary Values Tests ==================
		//Test in case the angle is zero degree
		assertEquals("dotProduct() wrong result dotProduct",14,v1.dotProduct(v1),0.0001);
		
		//Test in case the angle is 90 degree
		assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
        
		//Test in case the angle is 180 degree
		assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2)+28));
		
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1,2,3);
		//Test length squared result is correct
		assertTrue("lengthSquared() wrong result lengthSquared",isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(0,3,4);
		//Test length result is correct
		assertTrue("length() wrong result length",isZero(v1.length() - 5));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(0,4,0);
		Vector v3=new Vector(v1.getHead());
		Vector v2 = v3.normalize();
		
		//Test that normalize() does not create a new vector
		assertEquals("ERROR: normalize() function creates a new vector",v2, v3);
		
		//Test normalize result is correct
		assertTrue("ERROR: normalize() result is not a unit vector",isZero(v2.length() - 1));
		
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(0,4,0);
		Vector v2 = v1.normalized();
		
		//Test normalized result is correct
		assertTrue("ERROR: normalized() function does not create a new vector",!v1.equals(v2));
		
	}

	

}
