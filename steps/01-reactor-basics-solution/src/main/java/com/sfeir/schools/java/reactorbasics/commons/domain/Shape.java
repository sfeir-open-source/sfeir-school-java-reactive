package com.sfeir.schools.java.reactorbasics.commons.domain;

public enum Shape {
  CIRCLE("circle", "◯"),
  SQUARE("square", "◻"),
  TRIANGLE("triangle", "△");

  final String label;
  final String symbol;

  Shape(String label, String symbol) {
    this.label = label;
    this.symbol = symbol;
  }

  public String getLabel() {
    return label;
  }

  public String getSymbol() {
    return symbol;
  }
}
