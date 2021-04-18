package unittests;

import geometries.*;
import elements.Camera;
import primitives.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


/**
 * This class is used for integration tests between the formation of rays from a camera 
 * and the calculation of cuts of a ray with geometric bodies
 * @author Adiel
 *
 */
public class IntegrationTests {

	/**
	 * 
	 * Auxiliary function for cutting a ray with the body and counting the amount of cuts
	 * 
     * @param cam camera for the test
     * @param geo 3D body to test the integration of the camera with the ray
     * @param expected amount of intersections
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;
        cam.setViewPlaneSize(3, 3).setVpDistance(1);
        
        //Loop of creating rays and count of cuts
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                List<Point3D> intersections = geo.findIntersections(
                        cam.constructRayThroughPixel(3, 3, j, i));
                if(intersections!=null) {
                	count += intersections.size();
                }
               

            }
        //Check that the amount of cuts is correct 
        assertEquals("Wrong amount of intersections", expected, count);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void cameraRaySphereIntegretion() {

        Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1),
                new Vector(0, -1, 0));
        Camera camera2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1),
                new Vector(0, -1, 0));

        // TC01: Small Sphere (2 point)
        assertCountIntersections(camera1, new Sphere(
                1, new Point3D(0, 0, -3)), 2);

        // TC02: Big Sphere (18 points)
        assertCountIntersections(camera2, new Sphere(
                2.5, new Point3D(0, 0, -2.5)), 18);

        // TC03: Medium Sphere (10 points)
        assertCountIntersections(camera2, new Sphere(
                2, new Point3D(0, 0, -2)), 10);

        // TC04: Inside Sphere (9 points)
        assertCountIntersections(camera2, new Sphere(
                4, new Point3D(0, 0, -1)), 9);

        // TC05: Beyond Sphere (0 points)
        assertCountIntersections(camera1, new Sphere(
                0.5, new Point3D(0, 0, 1)), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1),
                new Vector(0, -1, 0));

        // TC01: Plane against camera (9 points)
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5),
                new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle (9 points)
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5),
                new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays (6 points)
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5),
                new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane (0 points)
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, 1),
                new Vector(0, 2, 0)), 0);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera camera = new Camera(Point3D.ZERO,
                new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle (1 point)
        assertCountIntersections(camera, new Triangle(new Point3D(1, -1, -2),
                new Point3D(-1, -1, -2), new Point3D(0, 1, -2)), 1);

        //TC02: Medium triangle (2 points)
        assertCountIntersections(camera, new Triangle(new Point3D(1, -1, -2),
                new Point3D(-1, -1, -2), new Point3D(0, 20, -2)), 2);
    }

	
}
