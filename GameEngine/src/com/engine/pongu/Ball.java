package com.engine.pongu;

import com.engine.framework.math.Vector2;

import java.awt.*;

public class Ball {
    Vector2 position;
    Vector2 velocity;
    float radius;
    Color color;

    public Ball() {
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        radius = 5;
        color = Color.white;
    }

    public Ball(Vector2 position, Vector2 velocity, float radius, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.color = color;
    }

    public void update() {
        position.add(velocity);

        if (position.x >= Pongu.WIDTH - radius*2 || position.x <= 0) {
            velocity.x *= -1;
        }
        if (position.y >= Pongu.HEIGHT - radius*2 || position.y <= 0) {
            velocity.y *= -1;
        }

    }

}
