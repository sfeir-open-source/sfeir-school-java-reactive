package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.Workshop01FluxCombine;
import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.temporal.Temporal;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

class Workshop01FluxCombineTest {

  /*
    MERGEWITH
   */
  // merge, ordre non garantie
    @Test
    void testMergeFluxOfColor() {
      Flux<Color> result = Workshop01FluxCombine.mergeFluxOfColor();

      StepVerifier.create(result)
        .expectNext(YELLOW, RED, BLUE, BLUE, RED, GREEN, GREEN, BLUE)
        .verifyComplete();
    }

  /*
    CONCATWITH
   */

  //concat des flux, vérifier ordre
    @Test
    void testConcatFluxOfColors() {
      Flux<Color> result = Workshop01FluxCombine.concatFluxOfColors();

      StepVerifier.create(result)
        .expectNext(RED, GREEN, GREEN, BLUE, YELLOW, RED, BLUE, BLUE)
        .verifyComplete();
    }

  /*
    ZIPWITH
   */

  // zip un flux de Shape et un flux de Color, produit un flux de Figure
  @Test
  void testZipWithShapeAndColorsIntoFigure() {
    Flux<Figure> result = Workshop01FluxCombine.zipWithShapeAndColorsIntoFigure();

    StepVerifier.create(result)
      .expectNext(
        new Figure(CIRCLE, RED),
        new Figure(TRIANGLE, GREEN),
        new Figure(TRIANGLE, GREEN),
        new Figure(SQUARE, YELLOW),
        new Figure(SQUARE, RED),
        new Figure(TRIANGLE, BLUE)
      )
      .verifyComplete();
  }

  @Test
  void testZipShapeAndColorsIntoFigure() {
    Flux<Figure> result = Workshop01FluxCombine.zipShapeAndColorsIntoFigure();
    StepVerifier.create(result)
      .expectNext(
        new Figure(CIRCLE, RED),
        new Figure(TRIANGLE, GREEN),
        new Figure(TRIANGLE, GREEN),
        new Figure(SQUARE, YELLOW),
        new Figure(SQUARE, RED),
        new Figure(TRIANGLE, BLUE)
      )
      .verifyComplete();
  }

}
