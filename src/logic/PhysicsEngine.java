package logic;

import java.util.HashSet;
import java.util.Set;

import logic.entities.ActorEntity;
import logic.entities.NonPhysicsActor;
import logic.entities.PhysicsActor;
import logic.entities.shapePrimitives.Circle;
import logic.entities.shapePrimitives.Point;
import logic.entities.shapePrimitives.Polygon;
import logic.entities.shapePrimitives.Shape;
import logic.entities.shapePrimitives.Triangle;

public class PhysicsEngine {
  private static final double g = 0.25; // px/frame^2

  /*
   * Effects one frame of physics. Note that NonPhysicsActors must be included
   * since collisions with them by PhysicsActors must be calculated.
   * 
   * Warning: the complexity of this method is ridiculous.
   */
  public static void step(
    Set<PhysicsActor> aes,
    Set<NonPhysicsActor> naes
  ) {
    HashSet<ActorEntity> allActors = new HashSet<ActorEntity>(aes);
    allActors.addAll(naes);

    /*
     * Handle all intersecting
     */
    next: 
    for (ActorEntity ae1 : allActors) {
      ae1.didCollide = false;
      if (ae1.getHitBox() == null)
        continue;

//      if(ae1 instanceof BouncingBall) {
//        System.out.println("Eclipse is TOTAL SHIT");
//      }

      // in the case of multiple intersections with X, intersects with the
      // first intersector found (that is not X itself) using this loop (order
      // guaranteed).
      for (ActorEntity ae2 : allActors) {
        if (ae2.getHitBox() == null || ae1 == ae2)
          continue;


        for (Shape sae : ae1.getHitBox())
          for (Shape spotential : ae2.getHitBox())
            if (intersects(sae, spotential)) {
              applyIntersectionPhysics(ae1, ae2);
              ae1.didCollide = true;
              break next;
            }
        
      }
    }

    /*
     * Other things to be done
     */
    for (PhysicsActor ae : aes) {
      // Ground physics
      applyGroundPhysics(ae);
      // Translate locations according to speed values
      locationTranslation(ae);
    }
  }
  
  public static boolean intersects(Set<Shape> a, Set<Shape> b) {
    for(Shape sa : a)
      for(Shape sb : b)
        if(intersects(sa, sb))
          return true;
    
    return false;
  }

  private static boolean intersects(Shape a, Shape b) {
    if (a instanceof Circle)
    {
      Circle castedA = (Circle) a;

      if (b instanceof Circle)
        return intersects(castedA, (Circle) b);
      else if (b instanceof Triangle)
        return intersects(castedA, (Triangle) b);
      else if (b instanceof Polygon)
        return intersects(castedA, (Polygon) b);
    } else if (a instanceof Triangle)
    {
      Triangle castedA = (Triangle) a;

      if (b instanceof Circle)
        return intersects((Circle) b, castedA);
      else if (b instanceof Triangle)
        return intersects(castedA, (Triangle) b);
      else if (b instanceof Polygon)
        return intersects(castedA, (Polygon) b);
    } else if (a instanceof Polygon)
    {
      Polygon castedA = (Polygon) a;

      if (b instanceof Circle)
        return intersects((Circle) b, castedA);
      else if (b instanceof Triangle)
        return intersects((Triangle) b, castedA);
      else if (b instanceof Polygon)
        return intersects(castedA, (Polygon) b);
    }

    // One of the two shapes not recognised
    return false;
  }

  private static boolean intersects(Circle a, Circle b) {
    double xDiff = a.xLoc - b.xLoc;
    double yDiff = a.yLoc - b.yLoc;
    double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    return distance <= a.getRadius() + b.getRadius();
  }

  private static boolean intersects(Circle c, Triangle t) {
    /*
     * Iterates over each line of the triangle and tests intersection according
     * to
     * 
     * http://stackoverflow.com/questions/1073336/circle-line-collision-detection?rq=1
     */
    
    Point movedFirst = new Point(t.getFirst().x, t.getFirst().y);
    Point movedSecond = new Point(t.getSecond().x, t.getSecond().y);
    Point movedThird = new Point(t.getThird().x, t.getThird().y);

    Point[] tPoints = { movedFirst, movedSecond, movedSecond,
        movedThird, movedThird, movedFirst };

    for (int i = 0; i < tPoints.length; i += 2)
    {
      /*
       * For each line, we must solve the quadratic. We therefore require a, b
       * and c, which require d and f.
       * 
       * a = ||d||^2
       * b = 2*f.d [the tricky one]
       * c = ||f||^2 - r^2
       */

      // Line defined by E (start vector) -> L (end vector)
      // direction vector d
      // C and r are properties of the circle
      // f = E - C
      double dX, dY, magD, gradD, fX, fY, magF, gradF;

      // clean transition into double is performed here
      dX = tPoints[i + 1].x - tPoints[i].x;
      dY = tPoints[i + 1].y - tPoints[i].y;
      magD = Math.sqrt(dX * dX + dY * dY);
      gradD = dY / dX;
      // and here
      fX = c.xLoc - tPoints[i].x;
      fY = c.yLoc - tPoints[i].y;
      magF = Math.sqrt(fX * fX + fY * fY);
      gradF = fY / fX;

      double aCalc, bCalc, cCalc;

      aCalc = magD * magD;
      bCalc = 2 * magF * magD * Math.atan(gradD - gradF / (1 + gradD * gradF));
      cCalc = magF * magF - c.getRadius() * c.getRadius();

      double discriminant = bCalc * bCalc - 4 * aCalc * cCalc;

      if (discriminant < 0)
        continue;
      else
      {
        discriminant = Math.sqrt(discriminant);

        double t1 = (-bCalc + discriminant) / (2 * aCalc);
        double t2 = (-bCalc - discriminant) / (2 * aCalc);

        if (t1 >= 0 && t1 <= 1)
        {
          return true;
        } else if (t2 >= 0 && t2 <= 1)
        {
          return true;
        }

        /*
         * This comment should never be reached. If the discriminant is
         * negative, there are no solutions and the line never intersects;
         * otherwise, one or both of t1 and t2 should contain the solution(s).
         */
      }
    }

    // no intersections were found
    return false;
  }

  private static boolean intersects(Circle c, Polygon ps) {
    for(Triangle p : ps.getTriangles())
      if(intersects(c, p))
        return true;
    
    return false;
  }

  private static boolean intersects(Triangle a, Triangle b) {
    // TODO
    return false;
  }

  private static boolean intersects(Triangle t, Polygon ps) {
    for(Triangle p : ps.getTriangles())
      if(intersects(t, p))
        return true;
    
    return false;
  }

  private static boolean intersects(Polygon as, Polygon bs) {
    /*
     * Just thought this code was fun to keep hanging around since it's no
     * longer available on the internet. It's an efficient method for testing
     * the intersection of two convex shapes each defined by a list of vertices.
     * I believe I had to convert this from C.
     * 
     * 
     * By Randolph Franklin.
     * http://local.wasp.uwa.edu.au/~pbourke/geometry/insidepoly/
     * 
     * int i, j; boolean c;
     * 
     * for(Point p : pg.getVertices()) { i = 0; j = 0; c = false; for (i = 0, j
     * = vertices.size()-1; i < vertices.size(); j = i++) { if
     * ((((vertices.get(i).y <= p.y) && (p.y < vertices.get(j).y)) ||
     * ((vertices.get(j).y <= p.y) && (p.y < vertices.get(i).y))) && (p.x <
     * (vertices.get(j).x - vertices.get(i).x) * (p.y - vertices.get(i).y) /
     * (vertices.get(j).y - vertices.get(i).y) + vertices.get(i).x)) c = !c; }
     * 
     * if(c) return true; }
     * 
     * return false;
     */
    
    for(Triangle a : as.getTriangles())
      for(Triangle b : bs.getTriangles())
        if(intersects(a, b))
          return true;

    return false;
  }

  private static void applyIntersectionPhysics(ActorEntity a, ActorEntity b) {
    double globalCoeffOfElasticity = 0.8;
    
    if (a instanceof PhysicsActor)
    {
      PhysicsActor castedA = (PhysicsActor) a;

      castedA.setXSpd(-castedA.getXSpd() * globalCoeffOfElasticity);
      castedA.setYSpd(-castedA.getYSpd() * globalCoeffOfElasticity);
    }

    if (b instanceof PhysicsActor)
    {
      PhysicsActor castedB = (PhysicsActor) b;

      castedB.setXSpd(-castedB.getXSpd() * globalCoeffOfElasticity);
      castedB.setYSpd(-castedB.getYSpd() * globalCoeffOfElasticity);
    }
  }

  private static void applyGroundPhysics(PhysicsActor e) {
    applyGravity(e);
  }

  private static void applyGravity(PhysicsActor e) {
    e.setYSpd(e.getYSpd() + g);
  }

  private static void locationTranslation(PhysicsActor e) {
    int xChng = (int) e.getXSpd();
    int yChng = (int) e.getYSpd();

    e.setXLoc(e.getXLoc() + xChng);
    e.setYLoc(e.getYLoc() + yChng);

    for (Shape s : e.getHitBox())
    {
      s.xLoc += xChng;
      s.yLoc += yChng;
    }
  }
}
