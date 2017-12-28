package com.engine.pongu;

import com.engine.framework.Game;
import com.engine.framework.Runner;
import com.engine.framework.display.Render;

import java.awt.*;

public class Pongu extends Render implements Game{

    public static void main(String[] args) {
        Pongu pongu = new Pongu();
        Runner runner = new Runner( 1280, 720, "Pongu", Color.BLACK);
        runner.setRender(pongu);
        runner.setGame(pongu);
        runner.start();

    }

    @Override
    public void update() {

    }

    @Override
    public void load() {

    }

    @Override
    public void draw() {
       
    }
}
