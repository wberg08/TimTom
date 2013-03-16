package gui.impl;

import gui.GuiTheme;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import system.FileManager;
import system.impl.SingleDirManager;

public class DefaultGuiThemeImpl implements GuiTheme {
  private static FileManager fm;
  
  public static final Color HUD_COLOUR       = Color.orange;
  public static final Color HUD_OUTLINE_COLOUR      = Color.black;
  public static final Color HUD_TEXTCOLOUR_1 = Color.black;
  public static final Color HUD_TEXT_2_COLOUR = Color.gray;
  public static BufferedImage logo;
  public static String logoFileName = "game\\Logo.png";
  
  {
    fm = new SingleDirManager();
    
    try {
      logo = ImageIO.read(fm.getFile(logoFileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public Color getHudColour() {
    return HUD_COLOUR;
  }

  @Override
  public Color getHudOutlineColour() {
    return HUD_OUTLINE_COLOUR;
  }

  @Override
  public Color getTextColour1() {
    return HUD_TEXTCOLOUR_1;
  }

  @Override
  public Color getText2Colour() {
    return HUD_TEXT_2_COLOUR;
  }

  @Override
  public BufferedImage getLogo() {
    return logo;
  }
}
