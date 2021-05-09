package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import static primitives.Util.*;

import elements.LightSource;

/**
 * The class inherits from the abstract class RayTracerBase
 * 
 * @author Adiel
 *
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * RayTracerBasic constructor who gets a scene and runs the constructor of the
	 * master class
	 * 
	 * @param scene
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);

	}

	/**
	 * The function calculates the effect of the light sources on the point for
	 * which the color is calculated according to the simple pong model
	 * 
	 * @param ray          for the point that strikes the point
	 * @param intersection for the point and geometry
	 * @return color of ambient light of the scene
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission())
				.add(calcLocalEffects(intersection, ray));
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);

		// If no intersection points were found
		if (intersections == null) {
			return scene.background;
		}

		GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}

	/**
	 * The function calculates the color of point with all of kindes of lights. We
	 * use Phong's model of lightTogather to got the precise color on the
	 * intersection point. We combine The Diffuse light and Specular light and
	 * emission light
	 * 
	 * @param intersection for the point and geometry
	 * @param ray
	 * @return color with the diffuse and speculative effects
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();// Vector from camera to the body
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		Material material = intersection.geometry.getMaterial();
		int nShininess = material.nShininess;
		double kd = material.kD;
		double ks = material.kS;

		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);// Vector from the light source to the point
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) {// sign(nl)==sign(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);// The intensity of the light
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),          // source at a certain point
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	}

	/**
	 * Auxiliary function that calculates the specular component
	 * 
	 * @param ks             for the attenuation factor
	 * @param l              for the vector from light source to the point
	 * @param n              for the vector n
	 * @param v              for the vector from the camera to the point
	 * @param nShininess     for the exponent
	 * @param lightIntensity for the light intesity
	 * @return the specular color of the point
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

		Vector r = n.scale(2 * (l.dotProduct(n))).subtract(l).normalize();
		Vector vMinus = v.scale(-1);
		double vr = alignZero(vMinus.dotProduct(r));

		if (vr > 0) {
			return lightIntensity.scale(ks * Math.pow(vr, nShininess));
		} else
			return lightIntensity.scale(0);
	}

	/**
	 * Auxiliary function that calculates the diffuse component
	 * 
	 * @param kd             for the attenuation factor
	 * @param l              for vector L
	 * @param n              for vector n
	 * @param lightIntensity the Light intensity
	 * @return the diffusive color of the point
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale(kd * Math.abs(l.dotProduct(n)));

	}
}
