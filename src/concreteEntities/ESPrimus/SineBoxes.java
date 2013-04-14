package concreteEntities.ESPrimus;

import java.awt.Color;
import java.awt.Graphics;

import logic.GameFrame;
import logic.entities.BackgroundEntity;

public class SineBoxes extends BackgroundEntity {
  private int xDim, yDim, boxcount = 12, boxwidth = 100, boxheight = 250,
      bottomColour = Settings.bottomColour, topColour = Settings.topColour;

  @SuppressWarnings("unused")
  private SineBoxes() {}

  public SineBoxes(int xLoc, int yLoc, int xDim, int yDim, int numBoxes) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.xDim = xDim;
    this.yDim = yDim;

    this.boxcount = numBoxes;
  }

  @Override
  public void step(GameFrame currentFrame) {
    bottomColour = bottomColour == 0xFFFFFF ? -1 : bottomColour;
    topColour = topColour == 0xFFFFFF ? -1 : topColour;

    bottomColour += 2;
    topColour += 2;
  }

  @Override
  public void paint(Graphics g) {
    double twoPi = 2 * Math.PI;

    for (int i = 0; i < boxcount; i++)
    {
      double fraction = (double) i / (double) boxcount;

      int boxXLoc = (int) (xLoc + (fraction * (double) xDim));
      int boxYLoc = (int) (yLoc + 0.5 * yDim
          * (1.0 + Math.sin(twoPi * fraction)) - 0.5 * boxheight);

      int colourStep = (topColour - bottomColour) / boxcount;
      g.setColor(new Color(bottomColour + (colourStep * i)));
      g.fillRoundRect(boxXLoc, boxYLoc, boxwidth, boxheight, 10, 10);
    }
  }

}
