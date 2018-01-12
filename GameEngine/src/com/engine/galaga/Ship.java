package com.engine.galaga;

import com.engine.framework.Input;
import com.engine.framework.display.Render;
import com.engine.framework.display.Texture;
import com.engine.framework.math.Rectangle;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

public class Ship {

    Rectangle body;
    float speed;
    int hp;

    ArrayList<Lazer> firedLazers;

    //Sprite Variables
    static Texture texture;
    Rectangle[] src;
    int imageIndex = 0;
    int imageSpeed = 2;
    int imageCounter = imageSpeed;

    public Ship(Rectangle b, float s, int hp) {
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
        animate();
        move();
    }

    public void draw(Render r) {
        r.drawImage(body, src[imageIndex], texture);
    }

    private void animate() {
        if(imageCounter <= 0) {
            imageCounter = imageSpeed;
            imageIndex = imageIndex == 0 ? 1 : 0;
        }
        else {
            imageCounter--;
        }
    }

    private void move() {
        if (Input.keys[GLFW_KEY_LEFT]) {
            body.x -= speed;
        }
        else if (Input.keys[GLFW_KEY_RIGHT]) {
            body.x += speed;
        }
        body.clamp(0, 0, Galaga.WIDTH, Galaga.HEIGHT);
    }

    private void srcRects() {
        src = new Rectangle[2];
        src[0] = new Rectangle(0, 0, 64, 96);
        src[1] = new Rectangle(64, 0, 64, 96);
    }

}
