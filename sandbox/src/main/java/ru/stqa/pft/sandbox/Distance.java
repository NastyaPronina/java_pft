package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {

    Point p1 = new Point();
    p1.x = 4;
    p1.y = 8;
    Point p2 = new Point();
    p2.x = 5;
    p2.y = 3;
    System.out.println("Расстояние между двумя точкам p1 и p2 c координатами: " + p1.x + "," + p1.y + "; " + p2.x + "," + p2.y + " соответственно, равно = " + distance(p1, p2));
  }

    public static double distance (Point a, Point b){
      return Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
    }
  }
