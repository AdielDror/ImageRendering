package unittests.renderer;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {
	private Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVpDistance(100) //
			.setViewPlaneSize(500, 500);

	/**
	 * Produce a scene with basic 3D model and render it into a jpeg image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {

		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(50, new Point3D(0, 0, -100)),
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)), // up
																													// left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up
																													// right
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)), // down
																														// left
		new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down
																											// right

		ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

	/**
	 * 
	 * Test for XML based scene - for bonus
	 * 
	 *
	 */

	
	/*
	 * @Test public void basicRenderXml() { Scene scene = new
	 * Scene("XML Test scene"); // enter XML file name and parse from XML file into
	 * scene object // DocumentBuilderFactory
	 * factory=DocumentBuilderFactory.newInstance(); try { DocumentBuilder builder=
	 * factory.newDocumentBuilder(); Document doc=builder.parse("scene.xml");
	 * NodeList geometriesList=doc.getElementsByTagName("geometries"); for(int i=0;
	 * i<geometriesList.getLength(); i++) { Node p=geometriesList.item(i);
	 * if(p.getNodeType()==Node.ELEMENT_NODE) { Element geometry=(Element) p; String
	 * radius=geometry.getAttribute("radius"); NodeList
	 * nameList=geometry.getChildNodes(); for(int j=0;j<nameList.getLength();j++) {
	 * Node n= nameList.item(j); if(n.getNodeType()==Node.ELEMENT_NODE) { Element
	 * name=(Element) n; System.out.println("geometries "+radius+":"+
	 * name.getTagName()+"="+name.getAttribute("radius")); } }
	 * 
	 * }
	 * 
	 * } } catch (ParserConfigurationException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } catch (SAXException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * ImageWriter imageWriter = new ImageWriter("xml render test", 1000, 1000);
	 * Render render = new Render() .setImageWriter(imageWriter) .setScene(scene)
	 * .setCamera(camera) .setRayTracer(new RayTracerBasic(scene));
	 * 
	 * render.renderImage(); render.printGrid(100, new
	 * Color(java.awt.Color.YELLOW)); render.writeToImage(); }
	 */


	    @Test
		public void basicRenderMultiColorTest() {
			Scene scene = new Scene("Test scene")//
					.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2)); //

			scene.geometries.add(new Sphere(50, new Point3D(0, 0, -100)), //
					new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)) // up left
							.setEmission(new Color(java.awt.Color.GREEN)),
					new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up right
					new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)) // down left
							.setEmission(new Color(java.awt.Color.RED)),
					new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100)) // down right
							.setEmission(new Color(java.awt.Color.BLUE)));

			ImageWriter imageWriter = new ImageWriter("color render test", 1000, 1000);
			Render render = new Render() //
					.setImageWriter(imageWriter) //
					.setScene(scene) //
					.setCamera(camera) //
					.setRayTracer(new RayTracerBasic(scene));

			render.renderImage();
			render.printGrid(100, new Color(java.awt.Color.WHITE));
			render.writeToImage();
		}
}
