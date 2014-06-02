package colormaps.impl;

import java.awt.Color;

import colormaps.AbstractColormap2D;
import colormaps.ColorSpace;

public class RGBRobertsonAndOCallaghan1 extends AbstractColormap2D {

	RGBFourAnchorColorMapDynamic[][] colorMaps = null;

	@Override
	public Color getColor(float x, float y) {

		if (colorMaps == null)
			initializeColorMap();

		checkRanges(x, y);

		float x_ = x;
		int indexX = 0;
		while (x_ > 1 / 3.0f) {
			indexX++;
			x_ -= 1 / 3.0f;
		}

		float y_ = y;
		int indexY = 0;
		while (y_ > 1 / 3.0f) {
			indexY++;
			y_ -= 1 / 3.0f;
		}

		if (indexX == 3 || indexY == 3)
			System.out.println("argh");

		return colorMaps[indexX][indexY].getColor(x_ * 3, y_ * 3);
	}

	@Override
	public String getName() {
		return "RGBRobertsonAndOCallaghan1";
	}

	@Override
	public String getDescription() {
		return "RGB colormap with 4x4 discrete color anchors. The colors in the corners are: Light-Orange, Brown, Dark-Brown, Blue";
	}

	@Override
	public ColorSpace getColorSpace() {
		return ColorSpace.RGB;
	}

	private void initializeColorMap() {

		Color nullnull = new Color(198, 119, 95);
		Color nulleins = new Color(212, 137, 100);
		Color nullzwo = new Color(225, 174, 113);
		Color nulldrei = new Color(247, 225, 168);

		Color einsnull = new Color(126, 92, 78);
		Color einseins = new Color(156, 118, 100);
		Color einszwo = new Color(164, 145, 111);
		Color einsdrei = new Color(172, 196, 158);

		Color zwonull = new Color(84, 73, 72);
		Color zwoeins = new Color(80, 74, 70);
		Color zwozwo = new Color(106, 119, 106);
		Color zwodrei = new Color(87, 130, 131);

		Color dreinull = new Color(58, 60, 59);
		Color dreieins = new Color(56, 65, 65);
		Color dreizwo = new Color(65, 80, 84);
		Color dreidrei = new Color(77, 106, 125);

		colorMaps = new RGBFourAnchorColorMapDynamic[3][3];

		colorMaps[0][0] = new RGBFourAnchorColorMapDynamic(nullnull, nulleins,
				einsnull, einseins);
		colorMaps[1][0] = new RGBFourAnchorColorMapDynamic(nulleins, nullzwo,
				einseins, einszwo);
		colorMaps[2][0] = new RGBFourAnchorColorMapDynamic(nullzwo, nulldrei,
				einszwo, einsdrei);
		colorMaps[0][1] = new RGBFourAnchorColorMapDynamic(einsnull, einseins,
				zwonull, zwoeins);
		colorMaps[1][1] = new RGBFourAnchorColorMapDynamic(einseins, einszwo,
				zwoeins, zwozwo);
		colorMaps[2][1] = new RGBFourAnchorColorMapDynamic(einszwo, einsdrei,
				zwozwo, zwodrei);
		colorMaps[0][2] = new RGBFourAnchorColorMapDynamic(zwonull, zwoeins,
				dreinull, dreieins);
		colorMaps[1][2] = new RGBFourAnchorColorMapDynamic(zwoeins, zwozwo,
				dreieins, dreizwo);
		colorMaps[2][2] = new RGBFourAnchorColorMapDynamic(zwozwo, zwodrei,
				dreizwo, dreidrei);
	}

	private double interpolate(double start, double end, double position) {
		return start + (end - start) * position;
	}

	private double interpolate(double lo, double ro, double lu, double ru,
			double positionX, double positionY) {
		// TODO: needs testing!
		double o = interpolate(lo, ro, positionX);
		double u = interpolate(lu, ru, positionX);
		return interpolate(o, u, positionY);
	}
}