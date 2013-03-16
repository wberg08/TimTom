package concreteEntities;


import java.awt.Color;
import java.awt.Graphics;

import logic.entities.NonPhysicsActor;
import run.GameFrame;
import system.InputProvider;

public class PlayerCursor extends NonPhysicsActor {
  
  public PlayerCursor(int xLoc, int yLoc) {
    super();
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  public void step(GameFrame currentFrame) {
    if(InputProvider.isUpPressed) {
      yLoc -= 3;
    }
    if(InputProvider.isDownPressed) {
      yLoc += 3;
    }
    if(InputProvider.isLeftPressed) {
      xLoc -= 3;
    }
    if(InputProvider.isRightPressed) {
      xLoc += 3;
    }
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillOval(xLoc-5, yLoc-5, 10, 10);
  }
}
