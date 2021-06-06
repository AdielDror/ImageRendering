package scene;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

/**
 * A class representing a scene
 * 
 * @author Adiel
 *
 */
public class Scene {
	
	public String name;
	public Color background=Color.BLACK;
	public AmbientLight ambientLight=new AmbientLight(Color.BLACK, 0.0);
	public Geometries geometries=null;
	public List<LightSource> lights=new LinkedList<LightSource>();
	
	/**
	 * Scene constructor who gets the name of the scene and 
	 * also builds an empty collection of bodies for model D3
	 * 
	 * @param name for the name of the scene
	 */
	public Scene(String name) {
		
		this.name = name;
		this.geometries=new Geometries();
	}

	

	/**
	 * Setter for background
	 * @param background the background to set
	 * @return the object of the scene itself
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}


	/**
	 * Setter for ambientLight
	 * @param ambientLight the ambientLight to set
	 * @return the object of the scene itself
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	

	/**
	 * Setter for geometries
	 * @param geometries the geometries to set
	 * @return the object of the scene itself
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}



	
	/**
	 * Setter for lights
	 * @param lights for the list of light sources
	 * @return the object of the scene itself
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
	
	
	/*
	 * public Iterator<Geometry>getGeometriesIterator() { return
	 * geometries.iterator(); }
	 */
	
}
