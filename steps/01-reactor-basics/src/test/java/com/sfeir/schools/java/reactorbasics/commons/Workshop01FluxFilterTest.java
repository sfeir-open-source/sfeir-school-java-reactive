package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

class Workshop01FluxFilterTest {

  /*
    FILTER
   */
  // filter numbers on parity
  @Test
  void testFilterNumberOnParity() {
    Flux<Integer> result = Workshop01FluxFilter.filterNumberOnParity();

    StepVerifier.create(result)
      .expectNext(2, 4, 6, 8, 10)
      .verifyComplete();
  }

  // filter Figures on color
  @Test
  void testFilterFigureWithColorRed() {
    Flux<Figure> result = Workshop01FluxFilter.filterFigureWithColorRed();

    StepVerifier.create(result)
      .expectNext(new Figure(CIRCLE, RED), new Figure(CIRCLE, RED))
      .verifyComplete();
  }

  // filter Figures on shapes
  @Test
  void testFilterFigureWithShapeCircle() {
    Flux<Figure> result = Workshop01FluxFilter.filterFigureWithShapeCircle();

    StepVerifier.create(result)
      .expectNext(new Figure(CIRCLE, BLUE), new Figure(CIRCLE, RED), new Figure(CIRCLE, RED))
      .verifyComplete();
  }

  @Test
  void testFilterFigureWithColorBlueAndShapeTriangle() {
    Flux<Figure> result = Workshop01FluxFilter.filterFigureWithColorBlueAndShapeTriangle();

    StepVerifier.create(result)
      .expectNext(new Figure(TRIANGLE, BLUE))
      .verifyComplete();
  }

  /*
    DISTINCT
   */
  // distinct on a flux of number
  @Test
  void testDistinctRepeatedNumbers() {
    Flux<Integer> result = Workshop01FluxFilter.distinctRepeatedNumbers();

    StepVerifier.create(result)
      .expectNext(1, 2, 3, 4, 5)
      .verifyComplete();
  }

  // distinct on a flux of figure
  @Test
  void testDistinctRepeatedFigures() {
    Flux<Figure> result = Workshop01FluxFilter.distinctRepeatedFigure();

    StepVerifier.create(result)
      .expectNext(
        new Figure(SQUARE, GREEN),
        new Figure(CIRCLE, BLUE),
        new Figure(CIRCLE, RED),
        new Figure(TRIANGLE, BLUE)
      )
      .verifyComplete();
  }

  // distinct figure with a specified key
  @Test
  void testDistinctRepeatedFiguresBasedOnShape() {
    Flux<Figure> result = Workshop01FluxFilter.distinctRepeatedFiguresBasedOnShape();

    StepVerifier.create(result)
      .expectNext(
        new Figure(SQUARE, GREEN),
        new Figure(CIRCLE, BLUE),
        new Figure(TRIANGLE, BLUE)
      )
      .verifyComplete();
  }

  @Test
  void testDistinctRepeatedFiguresBasedOnColor() {
    Flux<Figure> result = Workshop01FluxFilter.distinctRepeatedFiguresBasedOnColor();

    StepVerifier.create(result)
      .expectNext(
        new Figure(SQUARE, GREEN),
        new Figure(CIRCLE, BLUE),
        new Figure(CIRCLE, RED)
      )
      .verifyComplete();
  }

  /*
    BONUS
   */

  // sort flux of unordered numbers
  @Test
  @Disabled
  void testSortByNaturalOrder() {
    Flux<Integer> result = Workshop01FluxFilter.sortByNaturalOrder();

    StepVerifier.create(result)
      .expectNext(1, 2, 3, 4, 5, 6, 7)
      .verifyComplete();
  }

  // sort flux of item by using a weight attribute
  @Test
  @Disabled
  void testSortColorsByGradeAscending() {
    Flux<Color> result = Workshop01FluxFilter.sortColorsByGradeAscending();

    StepVerifier.create(result)
      .expectNext(BLUE, GREEN, YELLOW, RED)
      .verifyComplete();
  }

  @Test
  @Disabled
  void testSortShapesBySidesDescending() {
    Flux<Shape> result = Workshop01FluxFilter.sortShapesBySidesDescending();

    StepVerifier.create(result)
      .expectNext(SQUARE, TRIANGLE, CIRCLE)
      .verifyComplete();
  }

}
