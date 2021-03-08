package primitives;

public class Ray {
    
	final Point3D p0;
    final Vector dir;
	
    public Ray(Point3D p0, Vector dir) {
		
		this.p0 = p0;
		this.dir = dir;
	}
	
	public Point3D getP0() {
		return p0;
	}
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
	
}
