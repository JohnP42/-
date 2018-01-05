package com.engine.galaga;

import com.engine.framework.display.Render;
import com.engine.framework.display.Texture;
import com.engine.framework.math.Rectangle;
import com.engine.framework.math.Vector2;

import java.util.ArrayList;

public class Ship {

    Vector2 velocity;
    Rectangle body;
    float speed;
    int hp;

    ArrayList<Lazer> firedLazers;
    static Texture texture;
    Rectangle[] src;
    int imageIndex = 0;
    int imageSpeed = 2;

    public Ship(Rectangle b, float s, int hp) {
        velocity = new Vector2();
        body = b;
        speed = s;
        this.hp = hp;
        firedLazers = new ArrayList<>();
        srcRects();
    }

    public static void load() {
        texture = new Texture("assets/ship.png");
    }

    public void update() {

    }

    public void draw(Render r) {
        r.drawImage(body, src[imageIndex], texture);
    }

    private void move() {

    }

    private void srcRects() {
        src = new Rectangle[2];
        src[0] = new Rectangle(0, 0, 64, 96);
        src[1] = new Rectangle(64, 0, 64, 96);
    }

}
