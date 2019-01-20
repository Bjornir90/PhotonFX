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

}
