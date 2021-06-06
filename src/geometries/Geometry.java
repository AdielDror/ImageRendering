package geometries;


import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * Abstract class for the geometric shapes
 * 
 * @author Adiel
 *
 */
public abstract class Geometry extends Intersectable{
	
	protected Color emission=Color.BLACK;
	private Material material=new Material();
	
	
	/**
	 * Getter for the emission
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}


	/**
	 * Getter for material
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}
	


	/**
	 * Setter for material
	 * @param material the material to set
	 * @return the Geometry itself
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	


	/**
	 * Setter for the emission
	 * @param emmission the emission to set 
	 * @return the Geometry itself
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}


	/**
	 * 
	 * @param point3D of the vector
	 * @return normalized vector 
	 */
	public abstract Vector getNormal(Point3D point3D);


}
