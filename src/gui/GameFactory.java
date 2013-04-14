package gui;

import logic.GameFrame;

/*
 * Makes the initial game frame.
 */
public interface GameFactory {
  
  GameWindow getGui(GameFrame gameFrame, GameDataProvider gameDataProvider);

  GameFrame makeGameFrame();

}
