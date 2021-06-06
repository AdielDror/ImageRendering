package unittests.lights;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;

import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class MiniProjectTests {

	private Scene scene = new Scene("Test scene");
	
	/**
	 * Produces an image for enhancing soft shadows with some objects and lighting sources
	 */
	@Test
	public void test() {
		
		Camera camera = new Camera(new Point3D(0, 0, 2000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setVpDistance(1000);
		
		
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		
		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
				.setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setkD(0.7).setkS(0.8).setnShininess(60).setkR(0)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setEmission(new Color(java.awt.Color.BLACK)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Sphere(30, new Point3D(50, 118, -80)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.6).setkS(1)
								.setnShininess(30).setkR(0.2)),
						new Sphere(30, new Point3D(-40, 118, -80)) //
						.setEmission(new Color(java.awt.Color.MAGENTA)) //
						.setMaterial(new Material().setkD(0.6).setkS(1)
								.setnShininess(30).setkR(0.2)),
						new Sphere(20, new Point3D(60, -50, 50)) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(20, new Point3D(10, -50, 50)) //
						.setEmission(new Color(153,255,153)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(20, new Point3D(10, -10, 50)) //
						.setEmission(new Color(204,102,0)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(10, -110, 50)) //
						.setEmission(new Color(204,102,0)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(0, -110, 50)) //
						.setEmission(new Color(152,85,0)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(-10, -110, 50)) //
						.setEmission(new Color(152,72,0)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(-30, -110, 50)) //
						.setEmission(new Color(1,10,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(-40, -110, 50)) //
						.setEmission(new Color(113,0,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(-50, -110, 50)) //
						.setEmission(new Color(83,0,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(-60, -110, 50)) //
						.setEmission(new Color(56,0,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(60, -110, 50)) //
						.setEmission(new Color(15,10,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(50, -110, 50)) //
						.setEmission(new Color(133,0,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(40, -110, 50)) //
						.setEmission(new Color(103,0,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Sphere(10, new Point3D(30, -110, 50)) //
						.setEmission(new Color(76,0,152)) //
						.setMaterial(new Material().setkD(0.6).setkS(1).setnShininess(30).setkR(0.2)),
						new Plane(new Point3D(-500,-500,0),new Point3D(500,-300,0),new Point3D(0,-400,10) ),
		new Polygon(new Point3D(80,-80,80),new Point3D(80,80,80),new Point3D(-80,80,80),new Point3D(-80,-80,80))
		.setEmission(new Color(0,27,96)).setMaterial(new Material().setkD(0.2).setkS(0.2)
			.setnShininess(100).setkT(0.3)),
		new Polygon(new Point3D(-80,-80,-80),new Point3D(-80,80,-80),new Point3D(80,80,-80),new Point3D(80,-80,-80))
		.setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setkD(0.2).setkS(0.2)
				.setnShininess(30).setkT(0.6)),
		new Polygon(new Point3D(80,-80,-80),new Point3D(80,80,-80),new Point3D(80,80,80),new Point3D(80,-80,80))
		.setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setkD(0.2).setkS(0.2)
				.setnShininess(30).setkT(0.6)),
		new Polygon(new Point3D(-80,-80,80),new Point3D(-80,80,80),new Point3D(-80,80,-80),new Point3D(-80,-80,-80))
		.setEmission(new Color(153,204,255)).setMaterial(new Material().setkD(0.2).setkS(0.2)
				.setnShininess(30).setkT(0.2)),
		new Polygon(new Point3D(80,80,80),new Point3D(80,80,-80),new Point3D(-80,80,-80),new Point3D(-80,80,80))
		.setEmission(new Color(0,255,102)).setMaterial(new Material().setkD(0.2).setkS(0.2)
				.setnShininess(30).setkT(0.6)),
		new Polygon(new Point3D(-80,-80,80),new Point3D(-80,-80,-80),new Point3D(80,-80,-80),new Point3D(80,-80,80))
		.setEmission(new Color(16,27,96)).setMaterial(new Material().setkD(0.2).setkS(0.2)
				.setnShininess(30).setkT(0.6)));
		
		
		
		scene.lights.add(new SpotLight(new Color(230, 212, 188), new Point3D(10, -110, 100), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7).setRadius(3).setNumOfRays(10));
		scene.lights.add(new SpotLight(new Color(155,102,0), new Point3D(50, 118, 0), new Vector(0, 0, -1)) //
				.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(255,102,0), new Point3D(-40, 150, 0), new Vector(0, 0, -1)) //
				.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(400, 240, 0), new Point3D(60,50,0), new Vector(1, 1, -3)) //
		.setkL(1E-5).setkQ(1.5E-7));
		//scene.lights.add(new DirectionalLight(new Color(150, 150, 50), new Vector(0, 0, -1)));
		scene.lights.add(new PointLight(new Color(0, 0, 250), new Point3D(50, 118, 0))
				.setkL(0.0005).setkQ(0.0005));
		
		ImageWriter imageWriter = new ImageWriter("miniproject1", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene)).setDebugPrint().setMultithreading(3);

		render.renderImage();
		render.writeToImage();
	}
	
	
	
	

}
