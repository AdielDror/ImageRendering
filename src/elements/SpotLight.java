
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {

	private Vector direction;

	/**
	 * @param direction
	 * @param intensity 
	 * @param position 
	 * @param kC 
	 */
	public SpotLight(Color intensity, Point3D position,Vector direction,double kC,double kL,double kQ) {
		super(intensity, position, kC, kL, kQ);
		this.direction = direction.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {
		Vector l=getL(p);
		/*
		 * if(l==null) { return Color.BLACK; }
		 */
		double d=direction.dotProduct(l);
		if(d<=0) {
			return Color.BLACK;
		}
		//d=Math.pow(d, d)

		return super.getIntensity(p).scale(d);
	}
	
	/*
	 * @Override public Vector getL(Point3D p) {
	 * 
	 * // TODO Auto-generated method stub return null; }
	 */
}
