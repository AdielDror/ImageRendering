package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
	
	public String name;
	public Color background;
	public AmbientLight ambientLight;
	public Geometries geometries;
	
	/**
	 * @param name
	 */
	public Scene(String name) {
		
		this.name = name;
	}

	

	/**
	 * @param background the background to set
	 * @return 
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}


	/**
	 * @param ambientLight the ambientLight to set
	 * @return 
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	

	/**
	 * @param geometries the geometries to set
	 * @return 
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
	
	
	
	
}
