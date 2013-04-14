package gui;

import logic.GameFrame;


public interface GameDataProvider {

  GameFrame populateGameFrame(GameFrame gameFrame);
  
  GuiTheme getGuiTheme();

  int getWindowYSize();

  int getWindowXSize();
  
  int getFramesPerSecond();

}
