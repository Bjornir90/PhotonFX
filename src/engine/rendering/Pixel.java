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

	public void shade(float rShading, float gShading, float bShading){
		Color newShadedColor = new Color(color.r*rShading, color.g*gShading, color.b*bShading);
		shadedColor = newShadedColor;
	}

}
