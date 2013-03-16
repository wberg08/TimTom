package logic.entities.shapePrimitives;

import java.util.HashSet;
import java.util.Set;

/*
 * A polygon composed of triangles.
 */
public class Polygon extends Shape {
  private Set<Triangle> triangles = new HashSet<Triangle>();

  /*
   * First vertex connects to last (don't duplicate).
   */
  public Polygon(Point... vertices) {
    for (int i = 0; i < vertices.length; i += 3)
    {
      triangles.add(new Triangle(vertices[i], vertices[i+1], vertices[i+2]));
    }
  }

  /*
   * x1, y1, x2, y2, ...
   * 
   * Ignores last value if odd number of arguments given.
   */
  public Polygon(int... vertices) {
    for (int i = 0; i <= vertices.length - 6; i += 6)
    {
      triangles.add(new Triangle(new Point(vertices[i], vertices[i + 1]),
          new Point(vertices[i + 2], vertices[i + 3]), new Point(
              vertices[i + 4], vertices[i + 5])));
    }
  }

  public Set<Triangle> getTriangles() {
    return triangles;
  }

  public void setTriangles(Set<Triangle> triangles) {
    this.triangles = triangles;
  }
}
