package com.engine.framework.display;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.engine.framework.math.Rectangle;

public class Render {
	
	public void init(int width, int height, Color c) {
		GL.createCapabilities();
		glClearColor(c.getRed()/255.0f, c.getGreen()/255.0f, c.getBlue()/255.0f, c.getAlpha()/255.0f);
		glEnable (GL_BLEND); 
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDepthFunc(GL_NEVER);
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public void draw() {
		
	}
	
	public void drawRectangle(float x, float y, int width, int height, Color c){
		// set the color of the quad (R,G,B,A)
		glColor4f(c.getRed()/255.0f, c.getGreen()/255.0f, c.getBlue()/255.0f, c.getAlpha()/255.0f);
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		glEnd();
	}
	
	public void drawRectangle(Rectangle r, Color c){
		// set the color of the quad (R,G,B,A)
		glColor4f(c.getRed()/255.0f, c.getGreen()/255.0f, c.getBlue()/255.0f, c.getAlpha()/255.0f);
		glBegin(GL_QUADS);
		glVertex2f(r.left(),r.top());
		glVertex2f(r.right(),r.top());
		glVertex2f(r.right(),r.bottom());
		glVertex2f(r.left(),r.bottom());
		glEnd();
	}
	
	public void drawImage(Rectangle r, Texture tex) {
		glBindTexture(GL_TEXTURE_2D, tex.texture);
		glEnable(GL_TEXTURE_2D);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex2f(r.left(), r.top());
		glTexCoord2f(1, 0); glVertex2f(r.right(),r.top());
		glTexCoord2f(1, 1); glVertex2f(r.right(),r.bottom());
		glTexCoord2f(0, 1); glVertex2f(r.left(),r.bottom());
		glEnd();
		glDisable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, 0);
		glFlush();
	}
	
	public void drawImage(Rectangle r, Rectangle src, Texture tex) {
		glBindTexture(GL_TEXTURE_2D, tex.texture);
		glEnable(GL_TEXTURE_2D);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);
		glTexCoord2f(src.x/tex.width, src.y/tex.height); glVertex2f(r.x, r.y);
		glTexCoord2f((src.x+src.width)/tex.width, src.y/tex.height); glVertex2f(r.x+r.width,r.y);
		glTexCoord2f((src.x+src.width)/tex.width, (src.y+src.height)/tex.height); glVertex2f(r.x+r.width,r.y+r.height);
		glTexCoord2f(src.x/tex.width, (src.y+src.height)/tex.height); glVertex2f(r.x,r.y+r.height);
		glEnd();
		glDisable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, 0);
		glFlush();
	}
	
}
