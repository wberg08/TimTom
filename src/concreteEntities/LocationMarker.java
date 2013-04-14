package concreteEntities;

import java.awt.Color;
import java.awt.Graphics;

import logic.GameFrame;
import logic.entities.NonPhysicsActor;

public class LocationMarker extends NonPhysicsActor {
  private static Color DEFAULT_COLOUR = Color.BLACK;

  private Color        drawColour     = DEFAULT_COLOUR;

  @SuppressWarnings("unused")
  private LocationMarker() {}

  public LocationMarker(int xLoc, int yLoc) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  public LocationMarker(int xLoc, int yLoc, Color drawColour) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.drawColour = drawColour;
  }

  @Override
  public void step(GameFrame currentFrame) {}

  @Override
  public void paint(Graphics g) {
    g.setColor(drawColour);
    // horiz
    g.drawLine(xLoc - 5, yLoc, xLoc + 5, yLoc);
    // vert
    g.drawLine(xLoc, yLoc - 5, xLoc, yLoc + 5);
  }
}
