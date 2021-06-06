/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1),
        		new Point3D(1, 0, 0),
        		new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", 
        		new Vector(sqrt3, sqrt3, sqrt3),
        		pl.getNormal(new Point3D(0, 0, 1)));
    }
      
        /**
         * Test method for {@link geometries.Polygon#findIntersections(primitives.Point3D)}.
         */
        @Test
        public void findIntersections() {
             Polygon pl = new Polygon(
                     new Point3D(2,0,0),
                     new Point3D(1, 1, 0),
                     new Point3D(3,2, 0),
                     new Point3D(5, 1, 0));

             // ============ Equivalence Partitions Tests ==============
             //TC01: The point inside the polygon (1 point)
             Point3D p1=new Point3D(2,1,0);
             List<Point3D> result=pl.findIntersections(new Ray(
                     new Point3D(2,1,1),new Vector(0,0,-1)));
             assertEquals( "Wrong number of points",1, result.size());
             assertEquals("Ray crosses polygon",List.of(p1), result);
             //TC02: The point outside the polygon, against edge (0 points)
             assertNull( "Ray not intersects the polygon",pl.findIntersections(new Ray(
                             new Point3D(2, 2, 0),
                             new Vector(0, 0, -1))));

             //TC03: The point outside the polygon, against vertex (0 points)
             assertNull("Ray not intersects the polygon",pl.findIntersections(new Ray(
                             new Point3D(0.5, 1.0, 0),
                             new Vector(0, 0, -1))));

             // =============== Boundary Values Tests ==================
             //TC04: The point on edge (0 points)
             assertNull("Ray not intersects the polygon",pl.findIntersections(new Ray(
                             new Point3D(1.5, 1.25, 1),
                             new Vector(0, 0, -1))));
             //TC05: The point in vertex (0 points)
             assertNull("Ray not intersects the polygon",pl.findIntersections(new Ray(
                             new Point3D(1, 1.0, 0),
                             new Vector(0, 0, -1))));
             //TC06: The point on edge's continuation (0 points)
             assertNull("Ray not intersects the polygon",pl.findIntersections(new Ray(
                             new Point3D(4, 3.0, 1),
                             new Vector(0, 0, -1))));


         
    }
        

}
