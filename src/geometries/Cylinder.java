package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
	
	final double height;
	
	

	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}
	
	



	@Override
	public String toString() {
		return axisRay + ", " + radius + ", " + height ;
	}





	public double getHeight() {
		return height;
	}
	
	
	public Vector getNormal(Point3D point3d) {
		// TODO Auto-generated method stub
		return null;
	}


}
