package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface GuiTheme {
  public BufferedImage getLogo();
  public Color getHudColour();
  public Color getHudOutlineColour();
  public Color getTextColour1();
  public Color getText2Colour();
}
