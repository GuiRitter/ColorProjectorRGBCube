package io.github.guiritter.color_projector_r_g_b_cube;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static io.github.guiritter.color_projector_r_g_b_cube.ColorProjectorRGBCube.STEP;
import static io.github.guiritter.color_projector_r_g_b_cube.ColorProjectorRGBCube.MAX_COLOR_VALUE;

public class Color implements Comparable<Color> {

	public final int r;

	public final int g;

	public final int b;

	public Color(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public String toString() {
		return String.format("[% 3d, % 3d, % 3d]", r, g, b);
	}

	@Override
	public int compareTo(Color o) {
		if (g < o.g) {
			return 1;
		}
		if (g > o.g) {
			return -1;
		}
		if (b < o.b) {
			return 1;
		}
		if (b > o.b) {
			return -1;
		}
		if (r < o.r) {
			return 1;
		}
		if (r > o.r) {
			return -1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Color)) return false;
		var other = (Color) obj;
		return (r == other.r) && (g == other.g) && (b == other.b);
	}

	@Override
	public int hashCode() {
		return Objects.hash(r, g, b);
	}

	public List<Color> getNextColors() {
		var list = new LinkedList<Color>();

		var nextG = g + STEP;

		if (nextG <= MAX_COLOR_VALUE) {
			list.add(new Color(r, nextG, b));
		}

		var nextB = b + STEP;
		
		if (nextB <= MAX_COLOR_VALUE) {
			list.add(new Color(r, g, nextB));
		}

		var nextR = r + STEP;

		if (nextR <= MAX_COLOR_VALUE) {
			list.add(new Color(nextR, g, b));
		}

		return list;
	}
}
