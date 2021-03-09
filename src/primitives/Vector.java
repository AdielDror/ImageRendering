package primitives;


import static primitives.Point3D.ZERO;

public class Vector {
    Point3D _head;

    
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if ((x.equals(0)) && (y.equals(0)) && (z.equals(0)))
            throw new IllegalArgumentException("head of vector cannot be Point(0,0,0)");
        _head = new Point3D(x, y, z);

    }

    public Vector(Point3D head) {
       
        _head = new Point3D(head._x, head._y, head._z);
    }

    public Vector(double x, double y, double z) {
    	_head = new Point3D(x, y, z);
    	  if (_head.equals(ZERO)) {
              throw new IllegalArgumentException("head of vector cannot be Point(0,0,0)");

          }
        
    }


    public Point3D getHead() {
        return _head;
    }

    public Vector add(Vector vector) {
        return new Vector(vector._head._x.coord + _head._x.coord,
                vector._head._y.coord + _head._y.coord,
                vector._head._z.coord + _head._z.coord);

    }

    public Vector subtract(Vector vector) {
        return new Vector(vector._head._x.coord - _head._x.coord,
                vector._head._y.coord - _head._y.coord,
                vector._head._z.coord - _head._z.coord);
    }

    public Vector scale(double s) {
        return new Vector(s * _head._x.coord, s * _head._y.coord, s * _head._z.coord);

    }

    public Vector crossProduct(Vector vector) {
         Vector v3 = new Vector((_head._y.coord * vector._head._z.coord) - (_head._z.coord * vector._head._y.coord),
                (_head._z.coord * vector._head._x.coord) - (_head._x.coord * vector._head._z.coord),
                (_head._x.coord * vector._head._y.coord - _head._y.coord * vector._head._x.coord));
         if(v3.equals(ZERO)) {
        	 throw new IllegalArgumentException("cross product of the vectors can't be Point(0,0,0)");
         }
         return v3;
    }

    public double dotProduct(Vector vector) {
        return vector._head._x.coord * _head._x.coord +
                vector._head._y.coord * _head._y.coord +
                vector._head._z.coord * _head._z.coord;


    }

    public double lengthSquared() {
        return _head._x.coord * _head._x.coord +
                _head._y.coord * _head._y.coord +
                _head._z.coord * _head._z.coord;
    }

    public double length() {
        return Math.sqrt(lengthSquared());

    }

    public Vector normalize() {
        double length = this.length();
        if(length == 0) {
        	throw new ArithmeticException("divide by zero");
        }
        double x = this._head._x.coord;
        double y = this._head._y.coord;
        double z = this._head._z.coord;
        this._head = new Point3D(x / length, y / length, z / length);
        return this;
    }

    public Vector normalized() {
        normalize();
        return new Vector(_head._x.coord, _head._y.coord, _head._z.coord);

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector))
            return false;
        Vector other = (Vector) obj;
        return _head.equals(other._head);
    }

    @Override
    public String toString() {
        return "" + _head;
    }
}
