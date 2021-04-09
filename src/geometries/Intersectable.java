/**
 * 
 */
package geometries;

import primitives.*;

import java.util.List;

/**
 * @author Adiel
 *
 * Interface for intersection points
 */
public interface Intersectable {
    /**
     * Find all intersection points from the ray
     *
     * @param ray the famous Ray pointing to
     * @return intersection points
     */
    List<Point3D> findIntersections(Ray ray);
}
