package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
/**
 * The class inherits from the abstract class RayTracerBase 
 * 
 * @author Adiel
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * RayTracerBasic constructor
	 * 
	 * @param scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);

	}

	/**
	 * A private auxiliary function that receives a point and returns color
	 * 
	 * @param point for point
	 * @return color of ambient light of the scene
	 */
	private Color calcColor(Point3D point) {
		return scene.ambientLight.getIntensity();
	}

	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersections = scene.geometries.findIntersections(ray);
		
		//If no intersection points were found
		if(intersections==null) {
			return scene.background;
		}
		
		Point3D closestPoint = ray.findClosestPoint(intersections);
		return calcColor(closestPoint);
	}

}
