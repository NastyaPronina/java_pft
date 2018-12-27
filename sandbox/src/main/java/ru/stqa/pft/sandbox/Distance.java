package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {

    Point p1 = new Point(4, 8);
    Point p2 = new Point(5, 3);

    System.out.println("Расстояние между двумя точкам p1 и p2 c координатами: " + p1.x + "," + p1.y + "; " + p2.x + "," + p2.y +
            " соответственно, равно = " + p1.distance(p2));
  }


  }
