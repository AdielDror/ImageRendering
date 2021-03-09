package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
	
	protected Ray axisRay;
	protected double radius;
	
	
	
	
	
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}





	public Ray getAxisRay() {
		return axisRay;
	}





	public double getRadius() {
		return radius;
	}

	




	@Override
	public String toString() {
		return axisRay + ", " + radius;
	}





	@Override
	public Vector getNormal(Point3D point3d) {
		// TODO Auto-generated method stub
		return null;
	}
}
