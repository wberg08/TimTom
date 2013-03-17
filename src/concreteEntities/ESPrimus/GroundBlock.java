package concreteEntities.ESPrimus;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

import logic.entities.NonPhysicsActor;
import logic.entities.shapedefPrimitives.Polygon;
import logic.entities.shapedefPrimitives.Shapedef;
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

    Polygon hitBox = new Polygon(xLoc, yLoc, xLoc + xDim, yLoc, xLoc + xDim, yLoc + yDim,
                                 xLoc + xDim, yLoc + yDim, xLoc, yLoc + yDim, xLoc, yLoc);
    HashSet<Shapedef> a = new HashSet<Shapedef>();
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
    
////    DEBUG
//    g.setColor(Color.GREEN);
//   
//    for(Shapedef s : getHitBox())
//    {
//      Polygon p = (Polygon) s;
//      for(Triangle t : p.getTriangles()) {
//        g.drawLine(t.getFirst().xLoc, t.getFirst().yLoc, t.getSecond().xLoc, t.getSecond().yLoc);
//        g.drawLine(t.getSecond().xLoc, t.getSecond().yLoc, t.getThird().xLoc, t.getThird().yLoc);
//        g.drawLine(t.getThird().xLoc, t.getThird().yLoc, t.getFirst().xLoc, t.getFirst().yLoc);
//      }
//    }
  }

}
