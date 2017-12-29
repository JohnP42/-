package com.engine.jumpu;

import com.engine.framework.Input;
import com.engine.framework.math.Rectangle;
import com.engine.framework.math.Vector2;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class Player {

    Rectangle rectangle;
    Color color;
    float speed;
    Vector2 velocity;
    float jumpPower;
    float gravity;

    public Player(Rectangle rectangle, Color color,
                  float speed, Vector2 velocity,
                  float jumpPower, float gravity) {
        this.rectangle = rectangle;
        this.color = color;
        this.speed = speed;
        this.velocity = velocity;
        this.jumpPower = jumpPower;
        this.gravity = gravity;
    }

    public void update() {
        velocity.y += gravity;
        move();
        jump();
        rectangle.add(velocity);
        contactFloor();
        rectangle.clamp(0, 0, Jumpu.WIDTH, Jumpu.HEIGHT);
    }

    private void contactFloor() {
        if (rectangle.bottom() >= Jumpu.HEIGHT) {
            rectangle.y = Jumpu.HEIGHT - rectangle.height;
        }
    }

    private void move() {
        if (Input.keys[GLFW_KEY_A]) {
            velocity.x = -speed;
        }
        else if (Input.keys[GLFW_KEY_D]) {
            velocity.x = speed;
        }
        else {
            velocity.x = 0;
        }
    }

    private void jump() {
        if (Input.keys[GLFW_KEY_SPACE] && rectangle.bottom() == Jumpu.HEIGHT) {
            velocity.y = -jumpPower;
        }
    }
}
