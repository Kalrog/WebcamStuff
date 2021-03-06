
public class ColorConverter {
	public static int[] extractRGB(int rgb) {
		int alpha = (rgb >> 24) & 0x000000FF;
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >> 8) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		int[] result = new int[] { alpha, red, green, blue };
		return result;
	}

	public static int makeRGB(int[] colors) {
		return colors[3] | colors[2] << 8 | colors[1] << 16 | colors[0] << 24;
	}
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
