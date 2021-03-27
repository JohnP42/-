package engine.framework.display;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import engine.utils.BufferUtils;

public class Texture {

	int width, height, texture;
	Color transparencyColor;

	public Texture(String path){
		this.transparencyColor = null;
		texture = load(path);
	}

	public Texture(String path, Color transparencyColor){
		this.transparencyColor = transparencyColor;
		texture = load(path);
	}
	
	private int load(String path) {
		int[] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(ClassLoader.getSystemResource(path).getPath()));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] data = new int[width * height];
		for(int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff) >> 0;
			
			data[i] = data_with_transparency(a, r, g, b);
		}
		
		int tex = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		return tex;
	}

	public int data_with_transparency(int a, int r, int g, int b) {
		if (transparencyColor != null &&
				transparencyColor.getRed() == r &&
				transparencyColor.getGreen() == g &&
				transparencyColor.getBlue() == b) {
			a = 1;
		}
		return a << 24 | b << 16 | g << 8 | r;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getTexture() {
		return texture;
	}
	
}
