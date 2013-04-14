package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.swing.JPanel;

import logic.GameFrame;
import logic.entities.ActorEntity;
import logic.entities.BackgroundEntity;

public class GameWindow extends JPanel {

  private GuiTheme guiTheme;
  private GameFrame gameFrame;
  private BufferedImage bufferImage;

  public GameWindow(GameFrame gameFrame, GameDataProvider gameDataProvider) {
    super();
    this.gameFrame = gameFrame;
    guiTheme = gameDataProvider.getGuiTheme();

    bufferImage = new BufferedImage(gameDataProvider.getWindowXSize(),
        gameDataProvider.getWindowYSize(), BufferedImage.TYPE_INT_ARGB);
  }

  @Override
  public void update(Graphics g) {
    paint(g);
  }

  @Override
  public void paint(Graphics g) {
    paintGameFrame(bufferImage.getGraphics());
    paintHUD(bufferImage.getGraphics());

    g.drawImage(bufferImage, 0, 0, this);
  }

  private void paintHUD(Graphics g) {
    guiTheme.paint(g, gameFrame);
  }

  private void paintGameFrame(Graphics g) {
    Set<BackgroundEntity> bges = gameFrame.getBackgroundEntities();
    Set<ActorEntity> actes = gameFrame.getActorEntities();

    for (BackgroundEntity bge : bges)
      bge.paint(g);
    for (ActorEntity acte : actes)
      acte.paint(g);
  }

}
