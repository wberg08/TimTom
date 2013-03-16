package logic.entities.shapePrimitives;

public class Circle extends Shape {
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
}
