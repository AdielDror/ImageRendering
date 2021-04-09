package geometries;


import primitives.Point3D;
import primitives.Vector;

/**
 * Interface for the geometric shapes
 * 
 * @author Adiel
 *
 */
public interface Geometry extends Intersectable{
	/**
	 * 
	 * @param point3D of the vector
	 * @return normalized vector 
	 */
	Vector getNormal(Point3D point3D);


}
