package logic.entities.shapedefPrimitives;

public class Triangle extends Shapedef {
  private Point a, b, c;

  @SuppressWarnings("unused")
  private Triangle() {}

  public Triangle(Point a, Point b, Point c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public Point getFirst() {
    return a;
  }

  public void setFirst(Point p) {
    this.a = p;
  }

  public Point getSecond() {
    return b;
  }

  public void setSecond(Point p) {
    this.b = p;
  }

  public Point getThird() {
    return c;
  }

  public void setThird(Point p) {
    this.c = p;
  }
  
  @Override
  public String toString() {
    return "Triangle: " +
        "(" + a.x + ", " + a.y + "), " +
        "(" + b.x + ", " + b.y + "), " +
        "(" + c.x + ", " + c.y + "), ";
  }

}
