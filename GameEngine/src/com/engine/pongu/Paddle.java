package com.engine.pongu;

import com.engine.framework.Input;
import com.engine.framework.math.Rectangle;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class Paddle {

    Rectangle rectangle;
    int player;
    float speed;
    Color color;

    public Paddle(Rectangle rectangle, int player, float speed, Color color) {
        this.rectangle = rectangle;
        this.player = player;
        this.speed = speed;
        this.color = color;
    }

    public void update(Ball ball) {

        contactBall(ball);

        if (player == 1) {
            if (Input.keys[GLFW_KEY_W]) {
                rectangle.y -= speed;
            }
            if (Input.keys[GLFW_KEY_S]) {
                rectangle.y += speed;
            }
        }
        else {
            if (Input.keys[GLFW_KEY_UP]) {
                rectangle.y -= speed;
            }
            if (Input.keys[GLFW_KEY_DOWN]) {
                rectangle.y += speed;
            }
        }

    }

    private void contactBall(Ball ball) {
        if(rectangle.collidesWith(new Rectangle(ball.position.x, ball.position.y,ball.radius*2,ball.radius*2))) {
            if (player == 1) {
                ball.velocity.x = Math.abs(ball.velocity.x);
            }
            else {
                ball.velocity.x = -Math.abs(ball.velocity.x);
            }
        }
    }

}
