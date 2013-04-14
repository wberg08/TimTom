package gui.impl;

import gui.GameDataProvider;
import gui.GameFactory;
import gui.GameWindow;

import java.awt.Dimension;

import javax.swing.JFrame;

import logic.GameFrame;
import system.InputProvider;


public class GameFactoryImpl implements GameFactory {
  
  /**
   *  Create and set up the window.
   */
  public GameWindow getGui(GameFrame gameFrame, GameDataProvider gameDataProvider) {
    JFrame top = new JFrame("TimTom demo");
    top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    top.setResizable(false);

    GameWindow gameWindow = new GameWindow(gameFrame, gameDataProvider);
    gameWindow.setDoubleBuffered(false);
    
    new InputProvider(gameWindow);

    top.getContentPane().add(gameWindow);

    top.setPreferredSize(new Dimension(gameDataProvider.getWindowXSize(), gameDataProvider.getWindowYSize()));
    top.pack();
    top.setVisible(true);
    
    return gameWindow;
  }

  @Override
  public GameFrame makeGameFrame() {
    return new GameFrame();
  }
  
}
