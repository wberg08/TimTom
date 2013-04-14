package concreteEntities;


import java.awt.Color;
import java.awt.Graphics;

import logic.GameFrame;
import logic.entities.NonPhysicsActor;
import system.InputProvider;

public class PlayerJumper extends NonPhysicsActor {
  private int cannotJumpCount = 0;
  private int CANNOT_JUMP_COUNT_RESET_VALUE = 200;
  
  public PlayerJumper(int xLoc, int yLoc) {
    super();
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  public void step(GameFrame currentFrame) {
    cannotJumpCount = cannotJumpCount > CANNOT_JUMP_COUNT_RESET_VALUE || isInContactWithPlatform() ? 0 : cannotJumpCount + 1;
    
    if(thisCanJump() && InputProvider.isUpPressed) {
      
    }
    if(InputProvider.isLeftPressed) {
      xLoc -= 1;
    }
    if(InputProvider.isRightPressed) {
      xLoc += 1;
    }
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillOval(xLoc-5, yLoc-5, 10, 10);
  }
  
  private boolean thisCanJump() {
    return isInContactWithPlatform();
  }
  
  private boolean isInContactWithPlatform() {
    return didCollide;
  }
}
