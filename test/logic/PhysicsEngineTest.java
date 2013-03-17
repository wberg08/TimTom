package logic;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import logic.entities.shapedefPrimitives.Circle;
import logic.entities.shapedefPrimitives.Shapedef;

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
    setA.add(b);
    
    assertTrue(PhysicsEngine.intersects(setA, setB));
  }

}
