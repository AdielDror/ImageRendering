package primitives;

/**
 * 
 * @author idovi
 *
 */
public class Point3D {
	final Coordinate _x;
	final Coordinate _y;
	final Coordinate _z;
	
	public static final Point3D ZERO = new Point3D(0d,0d,0d);
	
	public Point3D(Coordinate x, Coordinate y,Coordinate z){
		this._x = x;
		this._y = y;
		this._z = z;
	}
	
	public Point3D(double x, double y, double z) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}
	
	

	public Coordinate get_x() {
		return _x;
	}

	public Coordinate get_y() {
		return _y;
	}

	public Coordinate get_z() {
		return _z;
	}

	public Point3D add(Vector vector) {
		return new Point3D(_x.coord + vector._head._x.coord , _y.coord + vector._head._y.coord ,_z.coord + vector._head._z.coord);
	}
	
	public Vector subtract(Point3D point3D) {
		
		return new Vector(_x.coord - point3D._x.coord ,_y.coord  - point3D._y.coord , _z.coord - point3D._z.coord);
	}
	
	public double distanceSquared(Point3D point3D) {
	     double x1 = _x.coord;
		 double y1 = _y.coord;
		 double z1 = _z.coord; 
		 
		 double x2 = point3D._x.coord ;
		 double y2 = point3D._y.coord ;
		 double z2 = point3D._z.coord ;
		 
		 return((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
	}
	
	public double distance(Point3D point3D) {
		return Math.sqrt(distanceSquared(point3D));
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D))
            return false;
        Point3D other = (Point3D)obj;
		return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
	}

	@Override
	public String toString() {
		return "(" + _x + "," + _y + "," + _z + ")";
	}
	
	
}
