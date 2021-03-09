package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
	
	Point3D q0;
	Vector normal;
	
	
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
	}

	
	public Point3D getQ0() {
		return q0;
	}


	public Vector getNormal() {
		return normal;
	}


	

	@Override
	public String toString() {
		return q0 + ", " + normal;
	}




	@Override
	public Vector getNormal(Point3D point3d) {
		// TODO Auto-generated method stub
		return null;
	}

}
