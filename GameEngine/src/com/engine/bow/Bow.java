package com.engine.bow;

import com.engine.framework.Input;
import com.engine.framework.display.Render;
import com.engine.framework.display.Texture;
import com.engine.framework.math.Rectangle;
import com.engine.framework.math.Vector2;

import static org.lwjgl.glfw.GLFW.*;

public class Bow {

    static final float ROTATESPEED = 2;
    static final float OFFSET = 135;
    static final float MAXPOWER = 60;
    static final float INCREMENT = 20;
    static final int CHARGETIME = 5;

    float power;
    float dir;
    Rectangle body;

    static Texture texture;
    Rectangle[] src;
    int imageIndex;
    int counter;

    public Bow() {
        power = 0;
        dir = 0;
        body = new Rectangle(0, 0, 16, 16);
        srcRects();
        imageIndex = 0;
        counter = 0;
    }

    public Bow(Rectangle body) {
        power = 0;
        dir = 0;
        this.body = body;
        srcRects();
        imageIndex = 0;
        counter = 0;
    }

    public static void load() {
        texture = new Texture("assets/bow_and_arrow.png");
    }

    public void update() {
        handleRotation();
        shoot();
    }

    public void draw(Render render) {
        render.drawImage(body, src[imageIndex], dir + OFFSET, texture);
    }

    private void shoot() {
        if (Input.keys[GLFW_KEY_SPACE]) {
            if (counter <= 0 && power < MAXPOWER) {
                power += INCREMENT;
                counter = CHARGETIME;
                if (imageIndex < src.length)
                    imageIndex++;
            }
            else {
                counter--;
            }
        }
        else if (power > 0) {
            imageIndex = 0;
            counter = 0;
            power = 0;
            // TODO: Create Arrow
        }
    }

    private void handleRotation() {
        if (Input.keys[GLFW_KEY_A]) {
            dir -= ROTATESPEED;
        }
        else if (Input.keys[GLFW_KEY_D]) {
            dir += ROTATESPEED;
        }
    }

    private void srcRects() {
        src = new Rectangle[4];
        src[0] = new Rectangle(0, 0, 16, 16);
        src[1] = new Rectangle(32, 0, 16, 16);
        src[2] = new Rectangle(48, 0, 16, 16);
        src[3] = new Rectangle(64, 0, 16, 16);
    }

}
