package renderer;


import geometries.Intersectable.GeoPoint;
import primitives.Color;
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
	 * A private auxiliary function that receives a point and returns color
	 * @param ray 
	 * 
	 * @param point for point
	 * @return color of ambient light of the scene
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(intersection.geometry.getEmission())
				.add(calcLocalEffects(intersection, ray));
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections=scene.geometries.findGeoIntersections(ray);

		// If no intersection points were found
		if (intersections == null) {
			return scene.background;
		}

		GeoPoint closestPoint = ray.getClosestGeoPoint(intersections);
		return calcColor(closestPoint,ray);
	}

	private Color calcLocalEffects(GeoPoint intersection,Ray ray) {
		Vector v=ray.getDir();
		Vector n=intersection.geometry.getNormal(ray.getP0());
		double nv=alignZero(n.dotProduct(v));
		if(nv==0)
			return Color.BLACK;
		int nShininess=intersection.geometry.getMaterial().nShininess;
		double kd=intersection.geometry.getMaterial().kD;
		double ks=intersection.geometry.getMaterial().kS;
		
		Color color=Color.BLACK;
		for(LightSource lightSource:scene.lights) {
			Vector l=lightSource.getL(intersection.point);
			double nl=alignZero(n.dotProduct(l));
			if(nl*nv>0) {//sign(nl)==sign(nv)
                   Color lightIntensity=lightSource.getIntensity(intersection.point);	
                   color=color.add(calcDiffusive(kd,l,n,lightIntensity)
                		   .add(calcSpecular(ks,l,n,v,nShininess,lightIntensity)));
			}
		}
		return color;
	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		
		Vector r=l.subtract(n.scale(2*l.dotProduct(n)));
		double vr=alignZero(-v.dotProduct(r));
		if(vr>0) {
			return lightIntensity.scale(ks* Math.pow(vr,nShininess));
		}
		return lightIntensity.scale(0);
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale(kd*Math.abs(l.dotProduct(n)));
	
	}
}
