/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Adiel
 *
 *         Composite class for all Intersectable objects
 */
public class Geometries extends Intersectable {
	private List<Intersectable> _intersectableList;
	private Intersectable currentGeometry;
	public boolean isImprovement;
	private Box box;

	/**
	 * Default constructor that initializes the field with an empty list
	 */
	public Geometries() {
		_intersectableList = new LinkedList<>();
		maxBoundary = new Point3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		minBoundary = new Point3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	}

	/**
	 * Constructor for the geometries
	 * 
	 * @param geometries for the intersections
	 */
	public Geometries(Intersectable... geometries) {
		_intersectableList = new LinkedList<>();
		add(geometries);

	}

	/**
	 * Setter for isImpruvment
	 * 
	 * @param isBvh for isImpruvment
	 * @return the Geometries itself
	 */
	public Geometries setIsImprovement(boolean isImprovement) {
		this.isImprovement = isImprovement;
		return this;

	}

	/**
	 * Adding to list
	 * 
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		_intersectableList.addAll(Arrays.asList(geometries));

	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {

		List<GeoPoint> intersections = null;

		// If we run the acceleration improvement
		if (isImprovement) {
			// Go over all the geometries and find intersections with the ray
			for (Intersectable geometry : _intersectableList) {

				box = new Box(geometry);
				// If the beam intersects the box of geometry
				if (box.isRayIntersectsBox(ray) == true) {

					var geoIntersectoions = geometry.findGeoIntersections(ray, maxDistance);
					if (geoIntersectoions != null) {
						if (intersections == null) {
							intersections = new LinkedList<>();

						}
						intersections.addAll(geoIntersectoions);
					}
				}
			}

		}
		// If we do not activate the acceleration improvement
		else {
			for (Intersectable geometry : _intersectableList) {

				var geoIntersectoions = geometry.findGeoIntersections(ray, maxDistance);
				if (geoIntersectoions != null) {
					if (intersections == null) {
						intersections = new LinkedList<>();

					}
					intersections.addAll(geoIntersectoions);
				}

			}
		}
		return intersections;
	}

	@Override
	public void setMaxCoordinates() {

		double x, y, z;
		x = currentGeometry.maxBoundary._x.coord;
		y = currentGeometry.maxBoundary._y.coord;
		z = currentGeometry.maxBoundary._z.coord;
		double maxX = maxBoundary._x.coord;
		double maxY = maxBoundary._y.coord;
		double maxZ = maxBoundary._z.coord;
		if (x > maxX)
			maxX = x;
		if (y > maxY)
			maxY = y;
		if (z > maxZ)
			maxZ = z;
		maxBoundary = new Point3D(maxX, maxY, maxZ);

	}

	@Override
	public void setMinCoordinates() {

		double x, y, z;
		x = currentGeometry.minBoundary._x.coord;
		y = currentGeometry.minBoundary._y.coord;
		z = currentGeometry.minBoundary._z.coord;

		double minX = minBoundary._x.coord;
		double minY = minBoundary._y.coord;
		double minZ = minBoundary._z.coord;
		if (x < minX)
			minX = x;
		if (y < minY)
			minY = y;
		if (z < minZ)
			minZ = z;
		minBoundary = new Point3D(minX, minY, minZ);
	}

	
	/**
	 * Internal class for Boundaries of Geometry
	 * 
	 * @author Adiel
	 *
	 */
	public static class Box {
		private double minX, maxX;
		private double minY, maxY;
		private double minZ, maxZ;

		/**
		 * Box constructor for minimum and maximum limits of geometry
		 * 
		 * @param geometry to wrap it in a virtual box
		 */
		public Box(Intersectable geometry) {
			setMaxBox(geometry.maxBoundary);
			setMinBox(geometry.minBoundary);

		}

		/**
		 * Setter for the max box
		 * 
		 * @param max is 3D point for the coordinates
		 */
		private void setMaxBox(Point3D max) {
			maxX = max._x.coord;
			maxY = max._y.coord;
			maxZ = max._z.coord;
		}

		/**
		 * Setter for the min box
		 * 
		 * @param min is 3D point for the coordinates
		 */
		private void setMinBox(Point3D min) {
			minX = min._x.coord;
			minY = min._y.coord;
			minZ = min._z.coord;
		}

		/**
		 * The function checks whether the ray intersects the box
		 * 
		 * @param ray from the camera
		 * @return false if the ray does not intersect the box, otherwise return true
		 */
		public boolean isRayIntersectsBox(Ray ray) {

			double minTX = 0, minTY = 0, minTZ = 0;
			double maxTX = Double.POSITIVE_INFINITY, maxTY = maxTX, maxTZ = maxTX;
			Vector v = ray.dir;
			Point3D headV = v._head;
			Point3D originRay = ray.p0;
			double dirX = alignZero(headV._x.coord);
			double dirY = alignZero(headV._y.coord);
			double dirZ = alignZero(headV._z.coord);
			double p0X = alignZero(originRay._x.coord);
			double p0Y = alignZero(originRay._y.coord);
			double p0Z = alignZero(originRay._z.coord);

			// Check if the coordinate X of the direction vector is 0, and also the
			// coordinate X of the beginning of the beam is greater than the maximum X
			// of the box or smaller than the minimum X of the box then the beam does not
			// intersect the box
			if (dirX == 0 && (p0X > maxX || p0X < minX))
				return false;

			if (dirX > 0) {
				if (maxX < p0X)// If the coordinate X of the beginning of the beam is greater than that of the
								// max of the box, then the beam does not intersect the box (negative t)
					return false;
				maxTX = (maxX - p0X) / dirX;
				minTX = (minX - p0X) / dirX;
			}
			if (dirX < 0) {
				if (minX > p0X)// If the coordinate X of the beginning of the beam is smaller than that of the
								// max of the box then the beam does not intersect the box (negative t)
					return false;
				maxTX = (minX - p0X) / dirX;
				minTX = (maxX - p0X) / dirX;
			}

			// Check if the coordinate Y of the direction vector is 0, and also the
			// coordinate Y of the beginning of the beam is greater than the maximum Y
			// of the box or smaller than the minimum Y of the box then the beam does not
			// intersect the box
			if (dirY == 0 && (p0Y > maxY || p0Y < minY))
				return false;

			if (dirY > 0) {
				if (maxY < p0Y)// If the coordinate Y of the beginning of the beam is greater than that of the
								// max of the box, then the beam does not intersect the box (negative t)
					return false;
				maxTY = (maxY - p0Y) / dirY;
				minTY = (minY - p0Y) / dirY;
			}
			if (dirY < 0) {
				if (minY > p0Y)// If the coordinate Y of the beginning of the beam is smaller than that of the
								// max of the box then the beam does not intersect the box (negative t)
					return false;
				maxTY = (minY - p0Y) / dirY;
				minTY = (maxY - p0Y) / dirY;
			}

			// Check if the coordinate Z of the direction vector is 0, and also the
			// coordinate Z of the beginning of the beam is greater than the maximum Z
			// of the box or smaller than the minimum Z of the box then the beam does not
			// intersect the box
			if (dirZ == 0 && (p0Z > maxZ || p0Z < minZ))
				return false;

			if (dirZ > 0) {
				if (maxZ < p0Z)// If the coordinate Z of the beginning of the beam is greater than that of the
								// max of the box, then the beam does not intersect the box (negative t)
					return false;
				maxTZ = (maxZ - p0Z) / dirZ;
				minTZ = (minZ - p0Z) / dirZ;
			}
			if (dirZ < 0) {
				if (minZ > p0Z)// If the coordinate Z of the beginning of the beam is smaller than that of the
								// max of the box then the beam does not intersect the box (negative t)
					return false;
				maxTZ = (minZ - p0Z) / dirZ;
				minTZ = (maxZ - p0Z) / dirZ;
			}

			// We will make sure that the maximum T is the minimum t of all the coordinates
			// calculated
			double maxT = Double.min(maxTX, maxTY);
			maxT = Double.min(maxT, maxTZ);

			// We will make sure that the minimum T is the maximum t of all the coordinates
			// calculated
			double minT = Double.max(minTX, minTY);
			minT = Double.max(minT, minTZ);

			// The ray does not intersects the box
			if (maxT < minT)
				return false;

			return true;

		}

		boolean isIntersect(Ray ray) {
			Vector v = ray.dir;
			Point3D head = v._head;
			Point3D point3d = ray.p0;

			double tMaxX = (maxX - point3d._x.coord) / head._x.coord;
			double tMaxY = (maxY - point3d._y.coord) / head._y.coord;
			double tMaxZ = (maxZ - point3d._z.coord) / head._z.coord;

			double tMinX = (minX - point3d._x.coord) / head._x.coord;
			double tMinY = (minY - point3d._y.coord) / head._y.coord;
			double tMinZ = (minZ - point3d._z.coord) / head._z.coord;

			double inversX = 1 / head._x.coord;
			double inversY = 1 / head._y.coord;
			double inversZ = 1 / head._z.coord;

			if (inversX >= 0) {
				tMinX = (minX - point3d._x.coord) * inversX;
				tMaxX = (maxX - point3d._x.coord) * inversX;
			} else {
				tMinX = (maxX - point3d._x.coord) * inversX;
				tMaxX = (minX - point3d._x.coord) * inversX;
			}

			if (inversY >= 0) {
				tMinY = (minY - point3d._y.coord) * inversY;
				tMaxY = (maxY - point3d._y.coord) * inversY;

			} else {
				tMinY = (maxY - point3d._y.coord) * inversY;
				tMaxY = (minY - point3d._y.coord) * inversY;
			}

			if (inversZ >= 0) {
				tMinZ = (minZ - point3d._z.coord) * inversZ;
				tMaxZ = (maxZ - point3d._z.coord) * inversZ;
			} else {
				tMinZ = (maxZ - point3d._z.coord) * inversZ;
				tMaxZ = (minZ - point3d._z.coord) * inversZ;
			}

			// double tMin = tMinX > tMinY ? tMinX : tMinY;
			// double tMax = tMaxX < tMinY ? tMaxX : tMaxY;

			if (tMinX > tMaxY || tMinY > tMaxX)
				return false;

			if (tMinY > tMinX)
				tMinX = tMinY;
			if (tMaxY < tMaxX)
				tMaxX = tMaxY;

			if (tMinX > tMaxZ || tMinZ > tMaxX)
				return false;

			if (tMinZ > tMinX)
				tMinX = tMinZ;

			if (tMaxZ < tMaxX)
				tMaxX = tMaxZ;

			// if(tMax<0 || tMin<0)
			// return false;

			return true;

		}
	}

	




}