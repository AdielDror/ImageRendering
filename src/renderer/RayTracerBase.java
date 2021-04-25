package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class for tracing rays
 * 
 * @author Adiel
 *
 */
public abstract class RayTracerBase {

	protected Scene scene;

	/**
	 * RayTracerBase constructor
	 * 
	 * @param scene for scene values
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * An abstract method for a color created from a particular ray to the 3D model of the scene
	 * 
	 * @param ray for the ray
	 * @return The color painted by the ray
	 */
	public abstract Color traceRay(Ray ray);

}
