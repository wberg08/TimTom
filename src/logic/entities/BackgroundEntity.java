package logic.entities;

import java.awt.Graphics;

import run.GameFrame;

public abstract class BackgroundEntity {
  protected int xLoc, yLoc;

  /*
   * Steps the BackgroundEntity by one frame.
   */
  public abstract void step(GameFrame currentFrame);

  public abstract void paint(Graphics g);

  public int getXLoc() {
    return xLoc;
  }

  public void setXLoc(int x) {
    this.xLoc = x;
  }

  public int getYLoc() {
    return yLoc;
  }

  public void setYLoc(int y) {
    this.yLoc = y;
  }
}
