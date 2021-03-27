package engine;

import engine.framework.Input;
import engine.framework.Game;
import engine.framework.Runner;
import engine.framework.display.Render;
import engine.framework.display.Texture;
import engine.framework.math.Rectangle;
import engine.framework.math.Vector2;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class Main extends Render implements Game {

	Rectangle player = new Rectangle(0, 0, 64, 96);
	Rectangle[] src = new Rectangle[2];
	int imageIndex = 0;
	int imageCount = 1;
	float angle = 90;
	Texture tex;

	Rectangle ball = new Rectangle(0, 0, 20, 20);
	Vector2 speed = new Vector2(5, 5);
	
	public static void main(String[] args) {
		
		Main main = new Main();
		Runner runner = new Runner(1280, 720, "Game", Color.BLACK);
		runner.setRender(main);
		runner.setGame(main);
		runner.start();
		
	}
	
	@Override
	public void load() {
		tex = new Texture("assets/ship.png");
		src[0] = new Rectangle(0, 0, 64, 96);
		src[1] = new Rectangle(64, 0, 64, 96);
	}
	
	@Override
	public void update() {
		if(Input.keys[GLFW_KEY_LEFT]) player.add(-7, 0);
		if(Input.keys[GLFW_KEY_RIGHT])  player.add(7, 0);
		if(Input.keys[GLFW_KEY_UP]) player.add(0, -7);
		if(Input.keys[GLFW_KEY_DOWN])  player.add(0, 7);

		angle+=1;
		ball.x += speed.x;
		ball.y += speed.y;

		if (ball.x <=0 || ball.x >= 1280 - ball.width || ball.collidesWith(player)) {
			speed.x *= -1;
		}

		if (ball.y <=0 || ball.y >= 720 - ball.height || ball.collidesWith(player)) {
			speed.y *= -1;
		}
		
		if(imageCount > 0) {
			imageCount --;
		}
		else {
			if(imageIndex == 0)
				imageIndex = 1;
			else
				imageIndex = 0;
			imageCount = 1;
		}

		player.clamp(0, 0, 1280, 720);
	}
	
	@Override
	public void draw() {
		this.drawImage(player, src[imageIndex], angle, tex);
		this.drawFullCircle(ball, ball.width/2, Color.red);
	}
	
}
