package concreteEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;

import logic.GameFrame;
import logic.entities.NonPhysicsActor;
import logic.entities.shapedefPrimitives.Point;
import logic.entities.shapedefPrimitives.Polygon;
import logic.entities.shapedefPrimitives.Shapedef;

public class UTFlatTile extends NonPhysicsActor {
  private int WIDTH = 200;
  private int HEIGHT = 200;
  private Color drawColour = Color.black;

  @SuppressWarnings("unused")
  private UTFlatTile() {}

  public UTFlatTile(int xLoc, int yLoc) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    
    HashSet<Shapedef> hitbox = new HashSet<Shapedef>();
    Polygon polygon = new Polygon(
        new Point(xLoc, yLoc),
        new Point(xLoc + WIDTH, yLoc),
        new Point(xLoc, yLoc + HEIGHT),
        new Point(xLoc, yLoc + HEIGHT),
        new Point(xLoc + WIDTH, yLoc + HEIGHT),
        new Point(xLoc + WIDTH, yLoc)
    );
    hitbox.add(polygon);
    
    setHitbox(hitbox);
  }

  public UTFlatTile(int xLoc, int yLoc, Color drawColour) {
    this(xLoc, yLoc);
    this.drawColour = drawColour;
  }
  
  @Override
  public void step(GameFrame currentFrame) {}

  @Override
  public void paint(Graphics g) {
    g.setColor(drawColour);
    g.fillRect(xLoc, yLoc, WIDTH, HEIGHT);
  }
}
