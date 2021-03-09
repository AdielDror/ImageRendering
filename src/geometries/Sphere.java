package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry{
	
	final Point3D center;
	final double radius;
	
	

	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	



	public Point3D getCenter() {
		return center;
	}



	public double getRadius() {
		return radius;
	}
	
	
	



	@Override
	public String toString() {
		return center + ", " + radius;
	}





	@Override
	public Vector getNormal(Point3D point3d) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
