package logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import logic.entities.shapedefPrimitives.Circle;
import logic.entities.shapedefPrimitives.Point;
import logic.entities.shapedefPrimitives.Shapedef;
import logic.entities.shapedefPrimitives.Triangle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PhysicsEngineTest {

//  PhysicsEngine sut = new PhysicsEngine();
  
  @Test
  public void circlesIntersect() {
    Circle a = new Circle(0, 0, 10);
    Circle b = new Circle(5, 5, 10);
    
    Set<Shapedef> setA = new HashSet<Shapedef>();
    setA.add(a);
    Set<Shapedef> setB = new HashSet<Shapedef>();
    setB.add(b);
    
    assertTrue(PhysicsEngine.intersects(setA, setB));
  }
  
  @Test
  public void circlesDontIntersect() {
    Circle a = new Circle(0, 0, 10);
    Circle b = new Circle(20, 20, 10);
    
    Set<Shapedef> setA = new HashSet<Shapedef>();
    setA.add(a);
    Set<Shapedef> setB = new HashSet<Shapedef>();
    setB.add(b);
    
    assertFalse(PhysicsEngine.intersects(setA, setB));
  }
  
  @Test
  public void lineAndCircleIntersect() {
    Circle c = new Circle(10, -10, 20);
    Point first = new Point(0, 0);
    Point second = new Point(20, 0);
    Point third = new Point(0, 20);
    Triangle t = new Triangle(first, second, third);
    
    Set<Shapedef> setC = new HashSet<Shapedef>();
    setC.add(c);
    Set<Shapedef> setT = new HashSet<Shapedef>();
    setT.add(t);
    
    assertTrue(PhysicsEngine.intersects(setC, setT));
  }
  
  @Test
  public void lineAndCircleDontIntersect() {
    Circle c = new Circle(10, -10, 5);
    Point first = new Point(0, 0);
    Point second = new Point(20, 0);
    Point third = new Point(0, 20);
    Triangle t = new Triangle(first, second, third);
    
    Set<Shapedef> setC = new HashSet<Shapedef>();
    setC.add(c);
    Set<Shapedef> setT = new HashSet<Shapedef>();
    setT.add(t);
    
    assertFalse(PhysicsEngine.intersects(setC, setT));
  }

}
