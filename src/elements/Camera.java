package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * This class represents the elements of the scene
 * 
 * @author Adiel
 *
 */
public class Camera {

	final Point3D p0;// Location of the camera
	final Vector vUp;// Vector upward direction
	final Vector vTo;// Vector forward direction
	final Vector vRight;// Vector direction to the right

	private double width;//Width of the View Plane
	private double height;//Height of the View Plane
	private double distance;//Distance of the View Plane from the Camera

	/**
	 * Getter for p0
	 * 
	 * @return the camera position
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * Getter for vector Up
	 * 
	 * @return vector Up
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * Getter for vector Forward
	 * 
	 * @return vector To
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * Getter for vector Right
	 *  
	 * @return vector Right
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * A constructor that initializes the position and direction vectors
	 * 
	 * @param p0  for location values
	 * @param vTo for vector forward
	 * @param vUp for vector up
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		this.p0 = p0;

		if (!isZero(vUp.dotProduct(vTo)))// Check if vUp and vTo vectors are vertical
			throw new IllegalArgumentException("vUp and vTo are not vertical");

		this.vUp = vUp.normalized();
		this.vTo = vTo.normalized();
		this.vRight = (this.vTo.crossProduct(this.vUp)).normalized();
	}

	/**
	 * Borrowing from Builder pattern
	 *
	 * @param width  for the width of the view plane
	 * @param height for the height of the view plane
	 * @return the camera object itself
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * Update method for the View Plane distance from the camera
	 * 
	 * @param distance for the distance
	 * @return the camera object itself
	 */
	public Camera setVpDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * Constructing a ray through a pixel
	 * 
	 * @param nX for quantity of columns (row width)
	 * @param nY for quantity of rows (column height)
	 * @param j  for a column of pixels
	 * @param i  for a line of pixels
	 * @return the position and the direction vector
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

		Point3D pC = p0.add(vTo.scale(distance));

		double rY = height / nY;
		double rX = width / nX;

		double yI = (i - (nY - 1) / 2d) * rY;
		double xJ = (j - (nX - 1) / 2d) * rX;

		Point3D pIJ = pC;

		if (!isZero(xJ)) {
			pIJ = pIJ.add(vRight.scale(xJ));

		}
		if (!isZero(yI)) {
			pIJ = pIJ.add(vUp.scale(-yI));

		}

		return new Ray(p0, pIJ.subtract(p0));
	}

}
