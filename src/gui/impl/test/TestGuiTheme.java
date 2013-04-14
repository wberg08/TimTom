package gui.impl.test;

import gui.GuiTheme;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.GameFrame;
import system.FileManager;
import system.impl.SingleDirManager;

public class TestGuiTheme implements GuiTheme {
  private static FileManager fm;
  
  private static final int WINDOW_X_SIZE = 800;
//  private static final int WINDOW_Y_SIZE = 500;
  private static final Color HUD_COLOUR       = Color.orange;
  private static final Color HUD_OUTLINE_COLOUR      = Color.black;
  private static final Color HUD_TEXTCOLOUR_1 = Color.black;
//  private static final Color HUD_TEXT_2_COLOUR = Color.gray;
  private static BufferedImage logo;
  private static String logoFileName = "game\\Logo.png";
  
  {
    fm = new SingleDirManager();
    
    try {
      logo = ImageIO.read(fm.getFile(logoFileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void paint(Graphics g, GameFrame gameFrame) {
    g.setColor(HUD_COLOUR);
    g.fillRect(0, 0, WINDOW_X_SIZE, 50);
    g.setColor(HUD_OUTLINE_COLOUR);
    g.drawLine(0, 50, WINDOW_X_SIZE, 50);

    g.setColor(HUD_TEXTCOLOUR_1);
    g.drawString("100 pts", 200, 25);

    g.setColor(HUD_TEXTCOLOUR_1);
    g.setFont(new Font("Dialog", Font.ITALIC, 30));
    g.drawString("The Game is Go", 250, 50);
    
    g.drawImage(logo, 10, 10, null);
  }
}
