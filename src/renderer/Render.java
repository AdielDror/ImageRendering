package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import primitives.Ray;

/**
 * This class creates from the scene the color matrix of the image
 * 
 * @author Adiel
 *
 */
public class Render {

	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * Setter for imageWriter
	 * 
	 * @param imageWriter
	 * @return the object of render itself
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * Setter for the scene
	 * 
	 * @param scene
	 * @return the object of render itself
	 *//*
		 * public Render setScene(Scene scene) { this.scene = scene; return this; }
		 */

	/**
	 * Setter for camera
	 * 
	 * @param camera
	 * @return the object of render itself
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * Setter for rayTracer
	 * 
	 * @param rayTracer for ray scanner
	 * @return the object of render itself
	 */
	public Render setRayTracer(RayTracerBasic rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	/**
	 * The function goes through all the pixels in the view plane and builds a ray
	 * for each pixel, and for each ray receives a color from the ray scanner and
	 * places it in the appropriate pixel of the image maker
	 * 
	 */
	public void renderImage() {
		
		if (camera == null)
			throw new MissingResourceException("Empty value", "Render", "camera");
		if (imageWriter == null)
			throw new MissingResourceException("Empty value", "Render", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("Empty value", "Render", "rayTracer");

		Ray ray;
		Color color;
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();

		// A loop that goes through all the pixels in a View Plane
		for (int i = 0; i < nX; ++i)
			for (int j = 0; j < nY; ++j) {
				ray = camera.constructRayThroughPixel(nX, nY, j, i);
				color = rayTracer.traceRay(ray);
				imageWriter.writePixel(j, i, color);

			}
	}

	/**
	 * The function creates a grid of lines for an image
	 * 
	 * @param interval for the width and height of the pixel
	 * @param color    for the grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("Empty value", "Render", "imageWriter");

		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();

		// A loop that passes over the view plane and creates a grid
		// in the color receive as a parameter
		for (int i = 0; i < nY; i++) {
			for (int j = 0; j < nX; j++) {
				if (i % interval == 0) {// for rows
					imageWriter.writePixel(i, j, color);
				} else if (j % interval == 0) {// for columns
					imageWriter.writePixel(i, j, color);
				}
			}
		}

	}

	/**
	 * A function activates by delegating the appropriate function of an image maker
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Empty value", "Render", "imageWriter");
		imageWriter.writeToImage();
	}

}
