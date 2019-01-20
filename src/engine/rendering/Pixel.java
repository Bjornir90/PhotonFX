package engine.rendering;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Pixel {
	private Color color, shadedColor;
	private float x, y;

	public Pixel(Color color, float x, float y) {
		this.color = color;
		shadedColor = color;
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g){
		Color oldColor = g.getColor();
		g.setColor(shadedColor);
		g.fillRect(x, y, 1, 1);
		g.setColor(oldColor);
	}

	public void shade(Color incidentLightColor){
		System.out.println("incidentLightColor = " + incidentLightColor);
		Color newShadedColor = new Color(color);
		newShadedColor = newShadedColor.multiply(incidentLightColor);
		System.out.println("newShadedColor = " + newShadedColor);
		shadedColor = newShadedColor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pixel pixel = (Pixel) o;

		if (Float.compare(pixel.x, x) != 0) return false;
		if (Float.compare(pixel.y, y) != 0) return false;
		if (!color.equals(pixel.color)) return false;
		return shadedColor.equals(pixel.shadedColor);
	}

	@Override
	public int hashCode() {
		int result = color.hashCode();
		result = 31 * result + shadedColor.hashCode();
		result = 31 * result + (x != +0.0f ? Float.floatToIntBits(x) : 0);
		result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
		return result;
	}
}
