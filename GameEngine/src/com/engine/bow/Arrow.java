package com.engine.bow;

import com.engine.framework.math.Vector2;


public class Arrow {

    static final float gravity = 0.5f;

    Vector2 pos;
    Vector2 velocity;
    float dir;

    public Arrow() {
        pos = new Vector2();
        velocity = new Vector2();
        dir = 0;
    }

    public Arrow(Vector2 pos, Vector2 velocity, float dir) {
        this.pos = pos;
        this.velocity = velocity;
        this.dir = dir;
    }

    public void update() {
        Vector2 prevPos = new Vector2(pos.x, pos.y);
        velocity.y += gravity;
        pos.add(velocity);

        float rise = pos.y - prevPos.y;
        float run = pos.x - prevPos.x;
        dir = (float) Math.toDegrees(Math.atan2(rise, run));
    }

}
