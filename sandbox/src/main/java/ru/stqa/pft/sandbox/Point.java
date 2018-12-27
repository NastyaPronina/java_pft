package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point (double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance (Point point) {
    return Math.sqrt(square(this.x - point.x) + square(this.y - point.y));
  }

  private double square (double a) {
    return a*a;
  }
}
