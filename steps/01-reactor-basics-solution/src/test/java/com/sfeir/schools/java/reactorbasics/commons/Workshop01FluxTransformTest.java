package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

class Workshop01FluxTransformTest {

  /*
    MAP
   */

  // transformer des Shapes en leur symbol
  @Test
  void testMapShapesToSymbols() {
    Flux<String> result = Workshop01FluxTransform.mapShapesToSymbols();

    StepVerifier.create(result)
      .expectNext("◯", "◯", "◻", "△", "◻")
      .verifyComplete();
  }

  // transformer des Colors en leur code hexa
  @Test
  void testMapColorsToHexaCode() {
    Flux<String> result = Workshop01FluxTransform.mapColorsToHexaCode();

    StepVerifier.create(result)
      .expectNext("ff0000", "ff0000", "00ff00", "ffff00")
      .verifyComplete();
  }

  /*
  FLATMAP
   */

  // passer une shapes à un mapper qui renvoie un Flux du symbol
  @Test
  void testFlatMapShapeToFluxOfSymbol() {
    Flux<String> result = Workshop01FluxTransform.flatMapShapeToFluxOfSymbol();

    StepVerifier.create(result)
      .expectNext("◯", "◻", "◻", "△")
      .verifyComplete();
  }

  @Test
  void testFlatMapShapeToFluxOfMonoSymbol() {
    Flux<String> result = Workshop01FluxTransform.flatMapShapeToFluxOfMonoSymbol();

    StepVerifier.create(result)
      .expectNext("◯", "◻", "◻", "△")
      .verifyComplete();
  }

  /*
  GROUPBY
   */

  // grouper par première lettre du mot
  @Test
  void testGroupByFirstChar() {
    Flux<GroupedFlux<Character, String>> result= Workshop01FluxTransform.groupByFirstChar();

    StepVerifier.create(result)
      .expectNextMatches(
        group -> group.key() == 'B'
          && List.of("Big").equals(group.collectList().block()))
      .expectNextMatches(
        group -> group.key() == 'H'
          && List.of("Handle", "Here").equals(group.collectList().block()))
      .expectNextMatches(
        group -> group.key() == 'S'
          && List.of("Shoulder", "Something").equals(group.collectList().block()));
  }

  // grouper Figures par couleur
  @Test
  void testGroupByFigureColor() {
    Flux<GroupedFlux<Color, Figure>> result = Workshop01FluxTransform.groupByFigureColor();

    StepVerifier.create(result)
      .expectNextMatches(
        group -> group.key() == GREEN
          && List.of(new Figure(SQUARE, GREEN), new Figure(SQUARE, GREEN)).equals(group.collectList().block()))
      .expectNextMatches(
        group -> group.key() == BLUE
          && List.of(new Figure(CIRCLE, BLUE), new Figure(TRIANGLE, BLUE)).equals(group.collectList().block()))
      .expectNextMatches(
        group -> group.key() == RED
          && List.of(new Figure(CIRCLE, RED), new Figure(CIRCLE, RED)).equals(group.collectList().block()));
  }

  // grouper Figures par forme
  @Test
  void testGroupByFigureShape() {
    Flux<GroupedFlux<Color, Shape>> result = Workshop01FluxTransform.groupByFigureColorMapShapeToValue();

    StepVerifier.create(result)
      .expectNextMatches(
        group -> group.key() == GREEN
          && List.of(SQUARE, SQUARE).equals(group.collectList().block()))
      .expectNextMatches(
        group -> group.key() == BLUE
          && List.of(CIRCLE, TRIANGLE).equals(group.collectList().block()))
      .expectNextMatches(
        group -> group.key() == RED
          && List.of(CIRCLE, CIRCLE).equals(group.collectList().block()));
  }

  /*
  BONUS
   */

  // transformer Flux into Mono
  @Test
  @Disabled
  void testCollectFluxToMono() {
    Mono<List<Shape>> result = Workshop01FluxTransform.collectFluxToMono();
    StepVerifier.create(result)
      .expectNext(List.of(CIRCLE, SQUARE, SQUARE, TRIANGLE))
      .verifyComplete();
  }

}
