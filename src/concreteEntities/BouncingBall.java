package concreteEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

import logic.entities.PhysicsActor;
import logic.entities.shapedefPrimitives.Circle;
import logic.entities.shapedefPrimitives.Shapedef;
import run.GameFrame;

public class BouncingBall extends PhysicsActor {
  private int   rad   = 100;
  private Color color = Color.red;

  @SuppressWarnings("unused")
  private BouncingBall() {}

  public BouncingBall(int xLoc, int yLoc, int rad) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.rad = rad;

    Circle hitBox = new Circle(xLoc + rad, yLoc + rad, rad);
    HashSet<Shapedef> a = new HashSet<Shapedef>();
    a.add(hitBox);

    setHitbox(a);
  }

  @Override
  public void step(GameFrame currentFrame) {}

  @Override
  public void paint(Graphics g) {
    g.setColor(color);
    g.fillOval(xLoc, yLoc, rad * 2, rad * 2);

     // DEBUG
//     g.setColor(Color.GREEN);
//    
//     for(Shape s : getHitBox())
//     {
//     Circle c = (Circle) s;
//     g.fillOval(
//     (int) (c.xLoc - c.getRadius()),
//     (int) (c.yLoc - c.getRadius()),
//     (int) (c.getRadius() * 2),
//     (int) (c.getRadius() * 2));
//     }
  }
}
