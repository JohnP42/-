package com.engine.bow;

import com.engine.framework.Game;
import com.engine.framework.Runner;
import com.engine.framework.display.Render;
import com.engine.framework.math.Rectangle;

import java.awt.*;

public class Shootaman extends Render implements Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    Bow bow;

    public static void main(String[] args) {
        Shootaman shootaman = new Shootaman();
        Runner runner = new Runner(WIDTH, HEIGHT, "Shootaman", new Color(0, 128, 128));
        runner.setRender(shootaman);
        runner.setGame(shootaman);
        runner.start();
    }

    @Override
    public void load() {
        Bow.load();
        bow = new Bow(new Rectangle(0, 0, 48, 48));
    }

    @Override
    public void update() {
        bow.update();
    }

    @Override
    public void draw() {
        bow.draw(this);
    }
}
