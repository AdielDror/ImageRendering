package primitives;

/**
 * Class Ray is the class representing a set of points on a line that are on one side relative 
 * to a given point on a line called, the beginning of the ray.
 * 
 * @author Adiel
 *
 */
public class Ray {
    
	final Point3D p0;
    final Vector dir;
	 
    /**
	  * Ray constructor receiving Point and direction
	  * 
	  * @param p0 value for p0 Point3D 
	  * @param dir value for direction Vector
	  */
    public Ray(Point3D p0, Vector dir) {
		
		this.p0 = p0;
		this.dir = dir.normalized();
	}

   /**
    * 
    * @return head of the ray
    */
	public Point3D getP0() {
		return p0;
	}
	
	/**
	 * 
	 * @return the direction
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector))
            return false;
        Ray other = (Ray)obj;
		return p0.equals(other.p0) && dir.equals(other.dir);
		
	}

	@Override
	public String toString() {
		return   p0 + ", " + dir;
	}

	/**
	 * Calculate a point on the ray
	 * 
	 * @param t for the point
	 * @return the point
	 */
	public Point3D getPoint(double t){
        return p0.add(dir.scale(t));
    }
}
