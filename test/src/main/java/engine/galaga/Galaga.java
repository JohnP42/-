package engine.galaga;

import engine.framework.Game;
import engine.framework.Runner;
import engine.framework.display.Render;
import engine.framework.math.Rectangle;

import java.awt.*;

public class Galaga extends Render implements Game {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    Ship ship;

    public static void main(String[] args) {
        Galaga galaga = new Galaga();
        Runner runner = new Runner(WIDTH, HEIGHT, "Galaga", Color.BLACK);
        runner.setRender(galaga);
        runner.setGame(galaga);
        runner.start();
    }

    @Override
    public void update() {
        ship.update();
    }

    @Override
    public void load() {
        Ship.load();
        ship = new Ship(new Rectangle(WIDTH / 2 - 32, HEIGHT - 128, 64, 96), 8, 3);
    }

    @Override
    public void draw() {
        ship.draw(this);
    }
}
