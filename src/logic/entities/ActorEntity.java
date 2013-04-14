package logic.entities;

import java.awt.Graphics;
import java.util.Set;

import logic.GameFrame;
import logic.entities.shapedefPrimitives.Shapedef;

/*
 * An ActorEntity is an Entity that is in the foreground (part of the 'play
 * area'; not part of the background which the player never interacts with
 * directly).
 * 
 * Do not subclass this directly to make a game entity; use one of the existing
 * subclasses in logic.entities. This is because the GameFrame does not handle
 * them - it demands certain sub-behaviour. You should not find this
 * useful limitation restrictive.
 */
public abstract class ActorEntity {
  public boolean       didCollide = false;
  protected int        xLoc, yLoc;
  protected Set<Shapedef> hitbox;

  /*
   * Steps the entity by one frame.
   */
  public abstract void step(GameFrame currentFrame);

  public abstract void paint(Graphics g);

  public Set<Shapedef> getHitBox() {
    return hitbox;
  }

  public void setHitbox(Set<Shapedef> hitPolygon) {
    hitbox = hitPolygon;
  }

  public int getXLoc() {
    return xLoc;
  }

  public void setXLoc(int xLoc) {
    this.xLoc = xLoc;
  }

  public int getYLoc() {
    return yLoc;
  }

  public void setYLoc(int yLoc) {
    this.yLoc = yLoc;
  }
}
