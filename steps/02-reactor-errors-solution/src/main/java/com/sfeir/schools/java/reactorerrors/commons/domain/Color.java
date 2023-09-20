package com.sfeir.schools.java.reactorerrors.commons.domain;

public enum Color {
  BLUE("blue", "0000ff", 1),
  GREEN("green", "00ff00", 2),
  RED("red", "ff0000", 4),
  YELLOW("yellow", "ffff00", 3);

  final String label;
  final String hex;
  final int grade;

  Color(String label, String hex, int grade) {
    this.label = label;
    this.hex = hex;
    this.grade = grade;
  }

  public String getLabel() {
    return label;
  }

  public String getHex() {
    return hex;
  }

  public Integer getGrade() {
    return grade;
  }
}
