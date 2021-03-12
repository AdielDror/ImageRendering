package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represent cylinder by height, radius and ray.
 * 
 * @author Adiel
 *
 */
public class Cylinder extends Tube {

	final double height;

	/**
	 * Cylinder constructor receiving 3 values
	 * 
	 * @param axisRay of the cylinder
	 * @param radius  of the cylinder
	 * @param height  of the cylinder
	 */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	/**
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return axisRay + ", " + radius + ", " + height;
	}

	@Override
	public Vector getNormal(Point3D point3d) {

		return null;
	}

}
