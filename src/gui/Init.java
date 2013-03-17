package gui;

import gui.impl.DefaultGuiThemeImpl;

import java.awt.Dimension;

import javax.swing.JFrame;

import run.GameFrame;
import system.InputProvider;
import concreteEntities.BouncingBall;
import concreteEntities.PlayerCursor;
import concreteEntities.ESPrimus.GroundBlock;
import concreteEntities.ESPrimus.SineBoxes;

/*
 * Makes the initial game frame. Will require a file reader
 * to take game options and instructions to do.
 */
public class Init {
  public static final int GAMEWINDOW_XSIZE = 800;
  public static final int GAMEWINDOW_YSIZE = 500;

  private static JFrame   top;

  public static GameWindow startGUI(GameFrame gf) {
    // Create and set up the window.
    top = new JFrame("TimTom demo");
    top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    top.setResizable(false);

    GameWindow gameWindow = new GameWindow(gf, new DefaultGuiThemeImpl());
    gameWindow.setDoubleBuffered(false);
    
    new InputProvider(gameWindow);

    top.getContentPane().add(gameWindow);

    top.setPreferredSize(new Dimension(GAMEWINDOW_XSIZE, GAMEWINDOW_YSIZE));
    top.pack();
    top.setVisible(true);
    
    return gameWindow;
  }

  /*
   * Put game init code here.
   */
  public static GameFrame makeNewGameFrame() {
    GameFrame ret = new GameFrame();

//    ret.addEntity(new UTFlatTile(450, 150, new Color(33, 33, 33)));
//    ret.addEntity(new UTFlatTile(400, 100, new Color(233, 233, 233)));

    // grid ln count (each axis)
    // int gls = 10;
    // //grid extent (px)
    // int gms = 1000;
    // //grid space
    // int gps = gms/gls;

    // for(int i=0; i<gls; i++)
    // {
    // for(int j=0; j<gls; j++)
    // {
    // ret.addEntity(new LocationMarker(gps*i, gps*j));
    // }
    // }

    // ret.addEntity(new LocationMarker(0, 0, Color.red));
    // ret.addEntity(new LocationMarker(100, 100, Color.red));
    // ret.addEntity(new LocationMarker(200, 200, Color.red));
    // ret.addEntity(new LocationMarker(300, 300, Color.red));
    // ret.addEntity(new LocationMarker(400, 400, Color.red));

    ret.addEntity(new SineBoxes(0, -400, 800, 500, 20));
    ret.addEntity(new SineBoxes(0, -200, 800, 500, 30));
    ret.addEntity(new SineBoxes(0, 0, 800, 500, 40));
    ret.addEntity(new SineBoxes(0, 200, 800, 500, 50));
    ret.addEntity(new SineBoxes(0, 400, 800, 500, 60));

    ret.addEntity(new GroundBlock(0, 450, 800, 100));

    // ret.addEntity(new GroundBlock(200, 200, 100, 100));
    // ret.addEntity(new GroundBlock(250, 200, 100, 100));

    // ret.addEntity(new FlashingBall(200, 200, 8, 8));
    ret.addEntity(new BouncingBall(400, 200, 10));
    
    ret.addEntity(new PlayerCursor(200, 200));

    return ret;
  }
}
