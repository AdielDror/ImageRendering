/**
 * 
 */
package unittests.renderer;

import org.junit.Test;

import renderer.ImageWriter;
import primitives.*;

/**
 * Test for a preliminary image
 * 
 * @author Adiel
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	public void writeImageTest() {

		ImageWriter picture = new ImageWriter("test blue", 800, 500);

		// A loop that passes over the view plane and creates an image in one color
		// and a grid of lines in a second color
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0) {// for rows
					// Writing pixel for grid
					picture.writePixel(i, j, new Color(255, 0, 255));
				}
				
				  else if (j % 50== 0) {//for columns
					  //Writing pixel for grid
				  picture.writePixel(i, j, new Color(255, 0, 255)); }
				 
				else {
					// Writing blue pixel
					picture.writePixel(i, j, new Color(0, 0, 255));
				}
			}
		}
		picture.writeToImage();
	}

}
