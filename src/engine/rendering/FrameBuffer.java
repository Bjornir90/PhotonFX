package engine.rendering;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FrameBuffer {
	private Pixel[][] img;
	private int width, height;

	public FrameBuffer(int width, int height) {
		img = new Pixel[width][height];
		this.width = width;
		this.height = height;
	}

	public void renderImage(Graphics g){
		for(int y = 0; y<img[0].length; y++){
			for(int x = 0; x<img.length; x++){
				Pixel pixel = img[x][y];
				if(pixel == null){
					/*g.setColor(Color.black);
					g.fillRect(x, y, 1, 1);*/
					continue;
				}
				pixel.render(g);
			}
		}
	}

	private void drawPixel(int x, int y, Color color){
		if(x>=width || y>=height || x<0 || y<0){
			System.err.println("Error : pixel out of frame boundaries : "+x+";"+y);
			return;
		}
		img[x][y] = new Pixel(color, x, y);
	}

	private void drawVerticalLine(int begin, int end, Color color, int x){
		for(int y=begin; y<end; y++){
			drawPixel(x, y, color);
		}
	}

	private void drawHorizontalLine(int begin, int end, Color color, int y){
		for(int x=begin; x<end; x++){
			drawPixel(x, y, color);
		}
	}

	public void drawRectangle(int x, int y, int width, int height, Color color){
		for(int i=y; i<y+height; i++) {
			drawHorizontalLine(x, x + width, color, i);
		}
		for(int i=x; i<x+width; i++) {
			drawVerticalLine(y, y + height, color, x);
		}
	}

	public void resetBuffer(){
		img = new Pixel[width][height];
	}
}
