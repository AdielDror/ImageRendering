package geometries;

import primitives.Point3D;

/**
 * Triangle class represent a polygon with three vertices.
 * 
 * @author Adiel
 *
 */
public class Triangle extends Polygon{

	/**
	 * Triangle constructor receiving a list
	 * 
	 * @param vertices a list of vertices
	 */
	public Triangle(Point3D... vertices) {
		super(vertices);
		
	}

	@Override
	public String toString() {
		return vertices + ", " + plane ;
	}

	
}
