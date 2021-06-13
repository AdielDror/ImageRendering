package primitives;

import static primitives.Point3D.ZERO;

/**
 * Vector class represent vector with size and direction by the end point
 * 
 * @author Adiel
 *
 */
public class Vector {
	public Point3D _head;

	/**
	 * Vector constructor receive 3 coordinate values
	 * 
	 * @param x for x coordinate
	 * @param y for y coordinate
	 * @param z for z coordinate
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {

		_head = new Point3D(x, y, z);

		if (_head.equals(ZERO))
			throw new IllegalArgumentException("head of vector cannot be Point(0,0,0)");

	}

	/**
	 * Vector constructor receive point of type Point3D
	 * 
	 * @param head the point
	 */
	public Vector(Point3D head) {
		if (head.equals(ZERO)) {
			throw new IllegalArgumentException("head of vector cannot be Point(0,0,0)");

		}

		_head = new Point3D(head._x, head._y, head._z);
	}

	/**
	 * Vector constructor receive 3 double values
	 * 
	 * @param x for first point
	 * @param y for second point
	 * @param z for third point
	 */
	public Vector(double x, double y, double z) {

		_head = new Point3D(x, y, z);

		if (_head.equals(ZERO)) {
			throw new IllegalArgumentException("head of vector cannot be Point(0,0,0)");

		}

	}

	/**
	 * 
	 * @return the end point
	 */
	public Point3D getHead() {
		return _head;
	}

	/**
	 * Adding between two vectors
	 * 
	 * @param vector to adding
	 * @return new vector is the result
	 */
	public Vector add(Vector vector) {
		return new Vector(vector._head._x.coord + _head._x.coord, vector._head._y.coord + _head._y.coord,
				vector._head._z.coord + _head._z.coord);

	}

	/**
	 * Subtract between two vectors
	 * 
	 * @param vector to subtract
	 * @return new vector is the result
	 */
	public Vector subtract(Vector vector) {
		return new Vector(vector._head._x.coord - _head._x.coord, vector._head._y.coord - _head._y.coord,
				vector._head._z.coord - _head._z.coord);
	}

	/**
	 * Multiplication the current vector in the scalar
	 * 
	 * @param s is scalar
	 * @return new vector is the result
	 */
	public Vector scale(double s) {
		return new Vector(s * _head._x.coord, s * _head._y.coord, s * _head._z.coord);

	}

	/**
	 * Vector multiplication between the current vector and that receive as a
	 * parameter
	 * 
	 * @param vector for multiplication
	 * @return new vector is the result
	 */
	public Vector crossProduct(Vector vector) {
		return new Vector((_head._y.coord * vector._head._z.coord) - (_head._z.coord * vector._head._y.coord),
				(_head._z.coord * vector._head._x.coord) - (_head._x.coord * vector._head._z.coord),
				(_head._x.coord * vector._head._y.coord - _head._y.coord * vector._head._x.coord));

	}

	/**
	 * Scalar product between two vectors
	 * 
	 * @param vector for multiplication
	 * @return the result
	 */
	public double dotProduct(Vector vector) {
		return vector._head._x.coord * _head._x.coord + vector._head._y.coord * _head._y.coord
				+ vector._head._z.coord * _head._z.coord;

	}

	/**
	 * Squared length of the vector
	 * 
	 * @return squared length
	 */
	public double lengthSquared() {
		return _head._x.coord * _head._x.coord + _head._y.coord * _head._y.coord + _head._z.coord * _head._z.coord;
	}

	/**
	 * Length of the vector in using the lengthSquared function
	 * 
	 * @return the length of the vector
	 */
	public double length() {
		return Math.sqrt(lengthSquared());

	}

	/**
	 * Normalizing the current Vector
	 * 
	 * @return this Vector normalized
	 */
	public Vector normalize() {
		double length = this.length();

		// cannot divide by 0
		if (length == 0) {
			throw new ArithmeticException("divide by zero");
		}
		double x = this._head._x.coord;
		double y = this._head._y.coord;
		double z = this._head._z.coord;
		this._head = new Point3D(x / length, y / length, z / length);
		return this;
	}

	/**
	 * Normalizing the new Vector
	 * 
	 * @return the new Vector normalized
	 */
	public Vector normalized() {
		Vector newVector = new Vector(_head._x.coord, _head._y.coord, _head._z.coord);
		return newVector.normalize();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return _head.equals(other._head);
	}

	@Override
	public String toString() {
		return "" + _head;
	}

	/**
	 * A function creates a vertical vector from a given vector
	 * 
	 * @return normalized vector
	 */
	public Vector craeteNormal() {
		
		double x = _head._x.coord, z = _head._z.coord;
			return new Vector(-z, 0, x).normalize();
	}
}
