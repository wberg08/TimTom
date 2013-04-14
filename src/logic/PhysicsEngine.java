package logic;

import java.util.HashSet;
import java.util.Set;

import logic.entities.ActorEntity;
import logic.entities.NonPhysicsActor;
import logic.entities.PhysicsActor;
import logic.entities.shapedefPrimitives.Circle;
import logic.entities.shapedefPrimitives.Point;
import logic.entities.shapedefPrimitives.Polygon;
import logic.entities.shapedefPrimitives.Shapedef;
import logic.entities.shapedefPrimitives.Triangle;

public class PhysicsEngine {
  private static final double g = 0.25; // px/frame^2

  /*
   * Effects one frame of physics. Note that NonPhysicsActors must be included
   * since collisions with them by PhysicsActors must be calculated.
   * 
   * Warning: the complexity of this method is ridiculous. On an unrelated
   * note, let's multithread it someday.
   */
  public static void step(Set<PhysicsActor> aes,
                          Set<NonPhysicsActor> naes) {
    HashSet<ActorEntity> allActors = new HashSet<ActorEntity>();
    allActors.addAll(aes);
    allActors.addAll(naes);

    /*
     * Handle all intersecting
     */
    next: 
    for (ActorEntity ae1 : allActors) {
      ae1.didCollide = false;
      if (ae1.getHitBox() == null)
        continue;

      // in the case of multiple intersections with X, intersects with the
      // first intersector found (that is not X itself) using this loop (order
      // guaranteed).
      for (ActorEntity ae2 : allActors) {
        if (ae2.getHitBox() == null || ae1 == ae2)
          continue;


        for (Shapedef sae : ae1.getHitBox())
          for (Shapedef spotential : ae2.getHitBox())
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
  
  public static boolean intersects(Set<Shapedef> a, Set<Shapedef> b) {
    for(Shapedef sa : a)
      for(Shapedef sb : b)
        if(intersects(sa, sb))
          return true;
    
    return false;
  }

  private static boolean intersects(Shapedef a, Shapedef b) {
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
    return intersects(c, t.getFirst(), t.getSecond()) ||
        intersects(c, t.getSecond(), t.getThird()) ||
        intersects(c, t.getThird(), t.getFirst());
  }
  
  /**
   * Intersection between a circle and a line.
   * 
   * @param c Circle
   * @param l0 One endpoint of line
   * @param l1 Other endpoint of line
   */
  private static boolean intersects(Circle c, Point l0, Point l1) {
    // TODO: case by case
    // case on orientation of the line
    if(l0.xLoc < l1.xLoc) {
      if(l0.yLoc < l1.yLoc) {
        if(c.xLoc < l0.xLoc) {
//          if(c.xLoc) {
//            
//          }
        }
      }
      else if (l0.yLoc == l1.yLoc) {
        
      }
      else {
        //l0.yLoc > l0.yLoc
        
      }
    }
    else if (l0.yLoc == l1.yLoc) {
      
    }
    else {
      
    }
    
    return intersectsInfiniteLine(c, l0, l1);
  }
  
  private static boolean intersectsInfiniteLine(Circle c, Point l0, Point l1) {
    int xh = c.xLoc - l0.xLoc;
    int yh = c.yLoc - l0.yLoc;
    
    int xl = l1.xLoc - l0.xLoc;
    int yl = l1.yLoc - l0.yLoc;
    
    double arcCosNum = (double) (xl * xh + yl * yh);
    double arcCosDen = Math.sqrt(xl * xl + yl * yl) * Math.sqrt(xh * xh + yh * yh);
    
    double theta = Math.acos(arcCosNum / arcCosDen);
    
    double h = (double) (Math.sqrt(xh * xh + yh * yh));
    
    // why am I using a trig function twice? this method could be improved
    double d = h * Math.sin(theta);
    
    return d < c.getRadius();
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

    for (Shapedef s : e.getHitBox())
    {
      s.xLoc += xChng;
      s.yLoc += yChng;
    }
  }
}
