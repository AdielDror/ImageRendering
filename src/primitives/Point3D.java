package primitives;

/**
 * Class Point3D is the class representing a point with 3 coordinates.
 * 
 * @author idovi
 *
 */
public class Point3D {
	final Coordinate _x;
	final Coordinate _y;
	final Coordinate _z;

	/**
	 * Static constant containing a point(0,0,0)
	 */
	public static final Point3D ZERO = new Point3D(0d, 0d, 0d);

	/**
	 * Point3D constructor receiving 3 coordinate values
	 * 
	 * @param x value for x Coordinate
	 * @param y value for y Coordinate
	 * @param z value for z Coordinate
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this._x = x;
		this._y = y;
		this._z = z;
	}

	/**
	 * Point3D constructor receiving 3 double values
	 * 
	 * @param x value for x Coordinate
	 * @param y value for y Coordinate
	 * @param z value for z Coordinate
	 */
	public Point3D(double x, double y, double z) {
		_x = new Coordinate(x);
		_y = new Coordinate(y);
		_z = new Coordinate(z);
	}

	
	/**
	 * Adding between 2 points
	 * 
	 * @param vector the other vector
	 * @return a new point that is the result of the adding of two vectors
	 */
	public Point3D add(Vector vector) {
		return new Point3D(_x.coord + vector._head._x.coord, _y.coord + vector._head._y.coord,
				_z.coord + vector._head._z.coord);
	}

	/**
	 * Subtraction between two vectors
	 * 
	 * @param point3D the other point3D
	 * @return a new vector that is the result of subtracting two vectors
	 */
	public Vector subtract(Point3D point3D) {

		return new Vector(
				_x.coord - point3D._x.coord,
				_y.coord - point3D._y.coord,
				_z.coord - point3D._z.coord);
	}

	/**
	 * Squared distance between 2 3D points
	 *
	 * @param point3D the other points
	 * @return the squared distance
	 */
	public double distanceSquared(Point3D point3D) {
		final double x1 = _x.coord;
		final double y1 = _y.coord;
		final double z1 = _z.coord;

		final double x2 = point3D._x.coord;
		final double y2 = point3D._y.coord;
		final double z2 = point3D._z.coord;

		return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
	}

	/**
	 * Distance between 2 3D points in use function
	 * 
	 * @param point3D the other point
	 * @return the distance
	 */
	public double distance(Point3D point3D) {
		return Math.sqrt(distanceSquared(point3D));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
	}

	@Override
	public String toString() {
		return "(" + _x + ", " + _y + ", " + _z + ")";
	}

	/**
	 * Getter for coordinate x
	 * @return coordinate x
	 */
	public double getX() {
		
		return _x.coord;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return _y.coord;
	}

	public double getZ() {
		// TODO Auto-generated method stub
		return _z.coord;
	}

}
