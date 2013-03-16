package concreteEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import logic.entities.NonPhysicsActor;
import run.GameFrame;

public class FlashingBall extends NonPhysicsActor {
  private int    xDim           = 200, yDim = 200;
  private Color  color          = Color.red;
  private Random rand           = new Random();

  public boolean movesByPhysics = false;

  @SuppressWarnings("unused")
  private FlashingBall() {}

  public FlashingBall(int xLoc, int yLoc) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  public FlashingBall(int xLoc, int yLoc, int xDim, int yDim) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.xDim = xDim;
    this.yDim = yDim;
  }

  @Override
  public void step(GameFrame currentFrame) {
    // int dimChange = rand.nextInt(5) - 2;
    // xDim = xDim < 3 ? xDim + 3 : xDim + dimChange;
    // yDim = yDim < 3 ? yDim + 3 : yDim + dimChange;
    xLoc += rand.nextInt(3) - 1;
    yLoc += rand.nextInt(3) - 1;
    color = new Color(rand.nextInt(156) + 100, 0, 0);
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(color);
    g.fillOval(xLoc, yLoc, xDim, yDim);
  }
}
