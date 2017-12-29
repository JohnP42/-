package com.engine.jumpu;

import com.engine.framework.Game;
import com.engine.framework.Runner;
import com.engine.framework.display.Render;
import com.engine.framework.math.Rectangle;
import com.engine.framework.math.Vector2;

import java.awt.*;

public class Jumpu extends Render implements Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    Player player;

    public static void main(String[] args) {
        Jumpu jumpu = new Jumpu();
        Runner runner = new Runner(WIDTH, HEIGHT, "Jumpu", new Color(0, 128, 128));
        runner.setGame(jumpu);
        runner.setRender(jumpu);
        runner.start();
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void load() {
        Rectangle r = new Rectangle(100, 100, 50, 50);
        Vector2 v = new Vector2();
        player = new Player(r, Color.red, 8, v, 15, 0.5f);
    }

    @Override
    public void draw() {
        drawRectangle(player.rectangle, player.color);
    }
}
