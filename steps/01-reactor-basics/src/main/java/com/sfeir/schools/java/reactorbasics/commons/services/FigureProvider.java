package com.sfeir.schools.java.reactorbasics.commons.services;

import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import reactor.core.publisher.Flux;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

public class FigureProvider {

  /**
   * Provide a Flux of Figure, always the same sequence
   *
   * @return [(SQUARE, GREEN),
   * (CIRCLE, BLUE),
   * (CIRCLE, RED),
   * (TRIANGLE, BLUE),
   * (CIRCLE, RED),
   * (SQUARE, GREEN)]
   */
  public static Flux<Figure> getFiguresConstantFlux() {
    return Flux.just(
      new Figure(SQUARE, GREEN),
      new Figure(CIRCLE, BLUE),
      new Figure(CIRCLE, RED),
      new Figure(TRIANGLE, BLUE),
      new Figure(CIRCLE, RED),
      new Figure(SQUARE, GREEN)
    );
  }
}
