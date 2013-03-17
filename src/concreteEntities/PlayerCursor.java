package concreteEntities;


import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import logic.entities.NonPhysicsActor;
import logic.entities.shapedefPrimitives.Circle;
import logic.entities.shapedefPrimitives.Shapedef;
import run.GameFrame;
import system.InputProvider;

public class PlayerCursor extends NonPhysicsActor {
  
  public PlayerCursor(int xLoc, int yLoc) {
    super();
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    
    Set<Shapedef> hitbox = new HashSet<Shapedef>();
    hitbox.add(new Circle(xLoc, yLoc, 5));
    setHitbox(hitbox);
  }

  public void step(GameFrame currentFrame) {
    if(InputProvider.isUpPressed) {
      yLoc -= 3;
      moveHitboxy(-3);
    }
    if(InputProvider.isDownPressed) {
      yLoc += 3;
      moveHitboxy(3);
    }
    if(InputProvider.isLeftPressed) {
      xLoc -= 3;
      moveHitboxx(-3);
    }
    if(InputProvider.isRightPressed) {
      xLoc += 3;
      moveHitboxx(3);
    }
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillOval(xLoc-5, yLoc-5, 10, 10);
  }
  
  private void moveHitboxx(int d) {
    for(Shapedef s : hitbox) {
      Circle c = (Circle) s;
      c.xLoc += d;
    }
  }
  
  private void moveHitboxy(int d) {
    for(Shapedef s : hitbox) {
      Circle c = (Circle) s;
      c.yLoc += d;
    }
  }
}
