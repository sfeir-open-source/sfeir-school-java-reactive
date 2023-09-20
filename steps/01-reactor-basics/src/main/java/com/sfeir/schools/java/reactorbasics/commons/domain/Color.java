package com.sfeir.schools.java.reactorbasics.commons.domain;

public enum Color {
  BLUE("blue", "0000ff"),
  GREEN("green", "00ff00"),
  RED("red", "ff0000"),
  YELLOW("yellow", "ffff00");

  final String label;
  final String hex;

  Color(String label, String hex) {
    this.label = label;
    this.hex = hex;
  }

  public String getLabel() {
    return label;
  }

  public String getHex() {
    return hex;
  }
}
