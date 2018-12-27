package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance () {
    Point p1 = new Point(5, 2);
    Point p2 = new Point(8, 6);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testResult () {
    Point p1 = new Point (5, 2);
    Point p2 = new Point (8, 6);
    assert p1.distance(p2) == 5.0;
  }
}
