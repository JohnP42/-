package com.engine.pongu;

import com.engine.framework.Game;
import com.engine.framework.Runner;
import com.engine.framework.display.Render;
import com.engine.framework.math.Rectangle;
import com.engine.framework.math.Vector2;

import java.awt.*;

public class Pongu extends Render implements Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    Ball ball;
    Paddle player1;
    Paddle player2;

    public static void main(String[] args) {
        Pongu pongu = new Pongu();
        Runner runner = new Runner(WIDTH, HEIGHT, "Pongu", Color.BLACK);
        runner.setRender(pongu);
        runner.setGame(pongu);
        runner.start();
    }

    @Override
    public void update() {
        ball.update();
        player1.update(ball);
        player2.update(ball);
    }

    @Override
    public void load() {
        ball = new Ball(new Vector2(WIDTH/2 - 8, HEIGHT/2 - 8), new Vector2(5, 5), 8, Color.white);
        player1 = new Paddle(new Rectangle(10, HEIGHT / 2, WIDTH / 50, HEIGHT / 10), 1, HEIGHT / 50, Color.white);
        player2 = new Paddle(new Rectangle(WIDTH - WIDTH / 50 - 10, HEIGHT / 2, WIDTH / 50, HEIGHT / 10), 2, HEIGHT / 50, Color.white);
    }

    @Override
    public void draw() {
        drawFullCircle(ball.position, ball.radius, ball.color);
        drawRectangle(player1.rectangle, player1.color);
        drawRectangle(player2.rectangle, player2.color);
    }
}
