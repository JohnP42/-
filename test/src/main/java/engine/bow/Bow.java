package engine.bow;

import engine.framework.Input;
import engine.framework.display.Render;
import engine.framework.display.Texture;
import engine.framework.math.Rectangle;
import engine.framework.math.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;

public class Bow {

    static final float OFFSET = 135;
    static final float MAXPOWER = 45;
    static final float INCREMENT = 15;
    static final int CHARGETIME = 8;

    float power;
    float dir;
    Rectangle body;
    ArrayList<Arrow> arrows = new ArrayList<>();
    Random rand = new Random();

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
        texture = new Texture("assets/bow_and_arrow.png", new Color(0, 128, 128));
    }

    public void update() {
        handleRotation();
        shoot();
        arrows.forEach((Arrow a) -> {
            a.update();
        });
    }

    public void draw(Render render) {
        render.drawImage(body, src[imageIndex], dir + OFFSET, texture);
        arrows.forEach((Arrow a) -> {
            render.drawImage(
                    new Rectangle(a.pos.x, a.pos.y, 48, 48),
                    new Rectangle(16, 0, 16, 16),
                    a.dir + OFFSET, texture);
        });
    }

    private void shoot() {
        if (Input.mouseButtons[GLFW_MOUSE_BUTTON_1]) {
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
            for (int i = 0; i < 10; i++) {
                float rd = (float) Math.toRadians(dir + rand.nextInt(10) - 5);
                Vector2 p = new Vector2(body.x, body.y);
                Vector2 v = new Vector2((float) Math.cos(rd) * power, (float) Math.sin(rd) * power);
                arrows.add(new Arrow(p, v, dir));
            }
            imageIndex = 0;
            counter = 0;
            power = 0;
        }
    }

    private void handleRotation() {
        float rise = (float) (Input.mouseY - body.center().y);
        float run = (float) (Input.mouseX - body.center().x);
        dir = (float) Math.toDegrees(Math.atan2(rise, run));
    }

    private void srcRects() {
        src = new Rectangle[4];
        src[0] = new Rectangle(0, 0, 16, 16);
        src[1] = new Rectangle(32, 0, 16, 16);
        src[2] = new Rectangle(48, 0, 16, 16);
        src[3] = new Rectangle(64, 0, 16, 16);
    }

}
