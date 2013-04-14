package gui.impl.test;

import concreteEntities.BouncingBall;
import concreteEntities.PlayerCursor;
import concreteEntities.ESPrimus.GroundBlock;
import concreteEntities.ESPrimus.SineBoxes;
import logic.GameFrame;
import gui.GameDataProvider;
import gui.GuiTheme;


public class TestGameDataProvider implements GameDataProvider {

  public static final int WINDOW_X_SIZE = 800;
  public static final int WINDOW_Y_SIZE = 500;
  public static final int FRAMES_PER_SECOND = 50;

  public GameFrame populateGameFrame(GameFrame gameFrame) {
    gameFrame.addEntity(new SineBoxes(0, -400, 800, 500, 20));
    gameFrame.addEntity(new SineBoxes(0, -200, 800, 500, 30));
    gameFrame.addEntity(new SineBoxes(0, 0, 800, 500, 40));
    gameFrame.addEntity(new SineBoxes(0, 200, 800, 500, 50));
    gameFrame.addEntity(new SineBoxes(0, 400, 800, 500, 60));

    gameFrame.addEntity(new GroundBlock(0, 450, 800, 100));

    gameFrame.addEntity(new BouncingBall(400, 200, 10));
    
    gameFrame.addEntity(new PlayerCursor(200, 200));

    return gameFrame;
  }

  @Override
  public GuiTheme getGuiTheme() {
    return new TestGuiTheme();
  }
  
  @Override
  public int getWindowXSize() {
    return WINDOW_X_SIZE;
  }

  @Override
  public int getWindowYSize() {
    return WINDOW_Y_SIZE;
  }
  
  @Override
  public int getFramesPerSecond() {
    return FRAMES_PER_SECOND;
  }

}
