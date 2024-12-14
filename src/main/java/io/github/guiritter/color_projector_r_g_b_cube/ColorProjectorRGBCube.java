package io.github.guiritter.color_projector_r_g_b_cube;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class ColorProjectorRGBCube {

	public static final int ARGB_R = 0;
	public static final int ARGB_G = 1;
	public static final int ARGB_B = 2;
	public static final int ARGB_A = 3;

	public static final int MAX_ALPHA = 255;

	public static final int MAX_COLOR_VALUE = 255;
	public static final int MIN_COLOR_VALUE = 0;

	public static final int STEP = 17;
	// public static final int STEP = 1; // hd

	public static void main(String args[]) throws IOException {
		// Color firstStartingPoint = new Color(0, 0, 0);
		// Color secondStartingPoint = null;

		// System.out.println(new Color(0, 300, 0));

		var setList = new LinkedList<Set<Color>>();

		setList.add(new TreeSet<Color>());

		setList.getLast().add(new Color(0, 0, 0));

		int outPutHeight = 0;

		while (true) {
			var currentSet = setList.getLast();

			var nextSet = new TreeSet<Color>();

			currentSet.forEach((color) -> nextSet.addAll(color.getNextColors()));

			setList.add(nextSet);

			outPutHeight = Math.max(outPutHeight, nextSet.size());

			if (nextSet.size() == 1) {
				break;
			}
		}
		
		int outPutWidth = setList.size();

		var outPutImage = new BufferedImage(outPutWidth, outPutHeight, TYPE_INT_ARGB);
		
		var transparent = new int[] {0, 0, 0, 0};

		var outPutRaster = outPutImage.getRaster();

		var emptyColorArray = new Color[0];

		for (int y = 0; y < outPutHeight; y++) {
			for (int x = 0; x < outPutWidth; x++) {
				outPutRaster.setPixel(x, y, transparent);
			}
		}

		var pixel = new int[] {0, 0, 0, MAX_ALPHA};
	
		for (int x = 0; x < outPutWidth; x++) {
			var setArray = setList.get(x).toArray(emptyColorArray);

			var setHeight = setArray.length;

			var heightOffset = (outPutHeight - setHeight) / 2;

			for (int y = 0; y < setHeight; y++) {
				var color = setArray[y];

				color.setPixel(pixel);

				outPutRaster.setPixel(x, y + heightOffset, pixel);
			}
		}

		ImageIO.write(outPutImage, "png", new File("C:\\design\\color\\ColorProjectorRGBCube\\src\\main\\resources\\output.png"));
		// ImageIO.write(outPutImage, "png", new File("C:\\design\\color\\ColorProjectorRGBCube\\src\\main\\resources\\output_hd.png"));
	}
}
