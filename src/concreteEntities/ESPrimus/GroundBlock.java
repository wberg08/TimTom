package concreteEntities.ESPrimus;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

import logic.entities.NonPhysicsActor;
import logic.entities.shapePrimitives.Polygon;
import logic.entities.shapePrimitives.Shape;
import run.GameFrame;

public class GroundBlock extends NonPhysicsActor {
  private int xDim, yDim;

  @SuppressWarnings("unused")
  private GroundBlock() {}

  public GroundBlock(int xLoc, int yLoc, int xDim, int yDim) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.xDim = xDim;
    this.yDim = yDim;

    Polygon hitBox = new Polygon(xLoc, yLoc, xLoc + xDim, yLoc, xLoc + xDim,
        yLoc + yDim, xLoc, yLoc + yDim);
    HashSet<Shape> a = new HashSet<Shape>();
    a.add(hitBox);

    setHitbox(a);
  }

  @Override
  public void step(GameFrame currentFrame) {}

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.LIGHT_GRAY);
    g.fillRect(xLoc, yLoc, xDim, yDim);

    g.setColor(Color.GRAY);
    boolean on = true;
    for (int i = 0; i < xDim; i += 10)
    {
      for (int j = 0; j < yDim; j += 10)
      {
        if (on)
        {
          g.fillRect(xLoc + i, yLoc + j, 10, 10);
        }
        on = !on;
      }
      on = !on;
    }
    
    g.setColor(Color.GREEN);
    g.fillRect(xLoc, yLoc, xDim, yDim);
  }

}
