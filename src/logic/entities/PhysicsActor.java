package logic.entities;

import java.awt.Graphics;

import run.GameFrame;

/*
 * An ActorEntity that is subject to the physics engine.
 */
public abstract class PhysicsActor extends ActorEntity {
  protected double mass, xSpd, ySpd; // grams, px/frame (to the right), px/frame
                                     // (downwards)

  public abstract void step(GameFrame currentFrame);

  public abstract void paint(Graphics g);

  public double getMass() {
    return mass;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public double getXSpd() {
    return xSpd;
  }

  public void setXSpd(double xSpd) {
    this.xSpd = xSpd;
  }

  public double getYSpd() {
    return ySpd;
  }

  public void setYSpd(double ySpd) {
    this.ySpd = ySpd;
  }
}
