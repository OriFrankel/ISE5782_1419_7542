/**
 * 
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.*;

/**
 * @author ori frankel and yair sprecher
 *
 */
class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	void testWriteToImage() {
		// TC 01: empty scene, 16*10 net
		ImageWriter imageWriter = new ImageWriter("testWriteToImage", 800, 500);
		Color blue = new Color(0, 0, 255);
		Color red = new Color(255, 0, 0);
		for (int i = 0; 800 > i; ++i)
			for (int j = 0; 500 > j; ++j)
				imageWriter.writePixel(i, j, (i % 50 == 0 || j % 50 == 0) ? blue : red);
		imageWriter.writeToImage();
	}

}
