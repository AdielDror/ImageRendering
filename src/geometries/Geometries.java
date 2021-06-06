/**
 * 
 */
package geometries;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import primitives.Point3D;
import primitives.Ray;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Adiel
 *
 *         Composite class for all Intersectable objects
 */
public class Geometries extends Intersectable {
	private List<Intersectable> _intersectableList;
	private Intersectable _lastAdded;
	
	/**
	 * Default constructor that initializes the field with an empty list
	 */
	public Geometries() {
		_intersectableList = new LinkedList<>();
		maxBoundary=new Point3D(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		minBoundary=new Point3D(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
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
	 * Adding to list
	 * 
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		_intersectableList.addAll(Arrays.asList(geometries));
		/*
		 * for (Intersectable intersectable : geometries) {
		 * _intersectableList.add(intersectable); _lastAdded = intersectable;
		 * setMinCoordinates(); setMaxCoordinates(); }
		 */
	}

	

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		List<GeoPoint> intersections = null;
	
		//Go over all the geometries and find intersections with the ray
		for (Intersectable geometry : _intersectableList ) {
			var geoIntersectoions = geometry.findGeoIntersections(ray,maxDistance);
			if (geoIntersectoions != null) {
				if(intersections==null) {
					intersections=new LinkedList<>(); 
				
				}
				intersections.addAll(geoIntersectoions);
			}
		}
		return intersections;
	}

	@Override
	public void setMaxCoordinates() {
		// TODO Auto-generated method stub
		double x, y, z;
		x = _lastAdded.maxBoundary.getX();
		y = _lastAdded.maxBoundary.getY();
		z = _lastAdded.maxBoundary.getZ();
		double maxX = maxBoundary.getX();
		double maxY = maxBoundary.getY();
		double maxZ = maxBoundary.getZ();
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
		// TODO Auto-generated method stub
		double x,y,z;
		x=_lastAdded.minBoundary.getX();
		y=_lastAdded.minBoundary.getY();
		z=_lastAdded.minBoundary.getZ();
		
		double minX=minBoundary.getX();
		double minY=minBoundary.getY();
		double minZ=minBoundary.getZ();
		if(x<minX)
			minX=x;
		if(y<minY)
			minY=y;
		if(z<minZ)
			minZ=z;
		minBoundary=new Point3D(minX, minY, minZ);
	}

	

	public void BVH() {
		TreeMap<Intersectable,List<Point3D>> map=new TreeMap<>();
		
		
		for(Intersectable f:_intersectableList) {
			Point3D max=f.maxBoundary;
			Point3D min=f.minBoundary;
			
			
		}
	}

	public Iterator<Intersectable> iterator() {
		// TODO Auto-generated method stub
		return _intersectableList.iterator();
	}
	

}