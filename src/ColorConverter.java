
public class ColorConverter {
	public int[] convertToSingleColors(int rgb) {
		int alpha = (rgb >> 24) & 0x000000FF;
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >> 8) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		int[] result = new int[] { alpha, red, green, blue };
		return result;
	}

	public int convertToOneColor(int[] colors) {
		return colors[3] | colors[2] << 8 | colors[1] << 16 | colors[0] << 24;
	}
}
