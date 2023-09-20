package com.sfeir.schools.java.reactorbasics.commons.domain;

public enum Shape {
  CIRCLE("circle", "◯", 0),
  SQUARE("square", "◻", 4),
  TRIANGLE("triangle", "△", 3);

  final String label;
  final String symbol;
  final int sides;

  Shape(String label, String symbol, int sides) {
    this.label = label;
    this.symbol = symbol;
    this.sides = sides;
  }

  public String getLabel() {
    return label;
  }

  public String getSymbol() {
    return symbol;
  }

  public Integer getSides() {
    return sides;
  }
}
