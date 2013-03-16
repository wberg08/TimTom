package gui;

import gui.impl.DefaultGuiThemeImpl;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.JPanel;

import logic.entities.ActorEntity;
import logic.entities.BackgroundEntity;
import run.GameFrame;

public class GameWindow extends JPanel {
  private GameFrame     gf;
  private BufferedImage bufferImage;
  private GuiTheme      colourManager;

  private GameWindow() {
    super();
  }

  public GameWindow(GameFrame gf, DefaultGuiThemeImpl cm) {
    this();
    this.gf = gf;
    colourManager = cm;

    bufferImage = new BufferedImage(Init.GAMEWINDOW_XSIZE,
        Init.GAMEWINDOW_YSIZE, BufferedImage.TYPE_INT_ARGB);
  }

  @Override
  public void update(Graphics g) {
    paint(g);
  }

  @Override
  public void paint(Graphics g) {
    paintGameFrame(gf, bufferImage.getGraphics());
    paintHUD(bufferImage.getGraphics());

    g.drawImage(bufferImage, 0, 0, this);
  }

  private void paintHUD(Graphics g) {
    g.setColor(colourManager.getHudColour());
    g.fillRect(0, 0, Init.GAMEWINDOW_XSIZE, 50);
    g.setColor(colourManager.getHudOutlineColour());
    g.drawLine(0, 50, Init.GAMEWINDOW_XSIZE, 50);

    g.setColor(colourManager.getTextColour1());
    g.drawString("100 pts", 200, 25);

    g.setColor(colourManager.getTextColour1());
    g.setFont(new Font("Dialog", Font.ITALIC, 30));
    g.drawString("The Game is Go", 250, 50);
    
    g.drawImage(colourManager.getLogo(), 10, 10, null);
  }

  private void paintGameFrame(GameFrame gf, Graphics g) {
    Set<BackgroundEntity> bges = gf.getBackgroundEntities();
    Set<ActorEntity> actes = gf.getActorEntities();

    for (BackgroundEntity bge : bges)
      bge.paint(g);
    for (ActorEntity acte : actes)
      acte.paint(g);
  }
}
