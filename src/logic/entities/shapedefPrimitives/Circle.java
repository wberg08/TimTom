package logic.entities.shapedefPrimitives;

public class Circle extends Shapedef {
  private int radius;

  public Circle(int x, int y, int r) {
    xLoc = x;
    yLoc = y;
    radius = r;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }
  
  @Override
  public String toString() {
    return "(" + xLoc + ", " + yLoc + ", " + getRadius() + ")";
  }
}
