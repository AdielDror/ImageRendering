package scene;

import static primitives.Util.*;
import geometries.Geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Box {
private static double minX, minY, minZ;
private static double maxX,maxY,maxZ;


  public Box(int l,Geometries geometries) {
	  setMaxBox(geometries.getMaxBoundary());
	  setMinBox(geometries.getMinBoundary());
 
  
  }

private void setMaxBox(Point3D max) {
	maxX=max.getX();
	maxY=max.getY();
	maxZ=max.getZ();
}

private void setMinBox(Point3D min) {
	minX=min.getX();
	minY=min.getY();
	minZ=min.getZ();
}



private boolean checkIntersect(Ray ray) {
	
	double minTX = 0, minTY = 0, minTZ = 0;
	double maxTX = Double.POSITIVE_INFINITY, maxTY = maxTX, maxTZ = maxTX;
	Vector v = ray.getDir();
	Point3D headV = v.getHead();
	Point3D originRay = ray.getP0();
	double rayX = alignZero(headV.getX());
	double rayY = alignZero(headV.getY());
	double rayZ = alignZero(headV.getZ());
	double rayPX = alignZero(originRay.getX());
	double rayPY = alignZero(originRay.getY());
	double rayPZ = alignZero(originRay.getZ());

	if (rayX == 0 && (rayPX > maxX || rayPX < minX))
		return false;
	if (rayX > 0) {
		if (maxX < rayPX)
			return false;
		maxTX = (maxX - rayPX) / rayX;
		minTX = Double.max(0, (minX - rayPX) / rayX);
	}
	if (rayX < 0) {
		if (minX > rayPX)
			return false;
		maxTX = (minX - rayPX) / rayX;
		minTX = Double.max(0, (maxX - rayPX) / rayX);
	}

	if (rayY == 0 && (rayPY > maxY || rayPY < minY))
		return false;
	if (rayY > 0) {
		if (maxY < rayPY)
			return false;
		maxTY = (maxY - rayPY) / rayY;
		minTY = Double.max(0, (minY - rayPY) / rayY);
	}
	if (rayY < 0) {
		if (minY > rayPY)
			return false;
		maxTY = (minY - rayPY) / rayY;
		minTY = Double.max(0, (maxY - rayPY) / rayY);
	}

	if (rayZ == 0 && (rayPZ > maxZ || rayPZ < minZ))
		return false;
	if (rayZ > 0) {
		if (maxZ < rayPZ)
			return false;
		maxTZ = (maxZ - rayPZ) / rayZ;
		minTZ = Double.max(0, (minZ - rayPZ) / rayZ);
	}
	if (rayZ < 0) {
		if (minZ > rayPZ)
			return false;
		maxTZ = (minZ - rayPZ) / rayZ;
		minTZ = Double.max(0, (maxZ - rayPZ) / rayZ);
	}
	double minT = Double.min(maxTX, maxTY);
	minT = Double.min(minT, maxTZ);
	double maxT = Double.max(minTX, minTY);
	maxT = Double.max(maxT, minTZ);
	if (minT < maxT)
		return false;
	Point3D p = ray.getPoint(maxT);
	return true;
	
}


  
}
