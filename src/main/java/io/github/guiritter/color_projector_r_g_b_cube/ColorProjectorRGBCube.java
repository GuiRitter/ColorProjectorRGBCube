package io.github.guiritter.color_projector_r_g_b_cube;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class ColorProjectorRGBCube {

	public static final int MAX_COLOR_VALUE = 255;
	public static final int MIN_COLOR_VALUE = 0;

	// public static final int STEP = 17;
	public static final int STEP = 255;

	public static void main(String args[]) {
		// Color firstStartingPoint = new Color(0, 0, 0);
		// Color secondStartingPoint = null;

		// System.out.println(new Color(0, 300, 0));

		var setList = new LinkedList<Set<Color>>();

		setList.add(new TreeSet<Color>());

		setList.getLast().add(new Color(0, 0, 0));

		while (true) {
			var currentSet = setList.getLast();

			var nextSet = new TreeSet<Color>();

			currentSet.forEach((color) -> nextSet.addAll(color.getNextColors()));

			setList.add(nextSet);

			if (nextSet.size() == 1) {
				break;
			}
		}

		System.out.println(setList);
	}
}
