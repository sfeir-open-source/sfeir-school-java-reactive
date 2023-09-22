package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
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

    List<GroupedFlux<Character, String>> collected = result.collectList().block();
    Assertions.assertNotNull(collected);
    Assertions.assertTrue(collected.size() > 0, "Collected content should not be empty");

    Mono<List<String>> hCollection = collected.get(0).collectList();  // first signal is H group, not B
    Mono<List<String>> bCollection = collected.get(1).collectList();
    Mono<List<String>> sCollection = collected.get(2).collectList();

    StepVerifier.create(bCollection)
      .expectNext(List.of("Big"))
      .verifyComplete();

    StepVerifier.create(hCollection)
      .expectNext(List.of("Handle", "Here"))
      .verifyComplete();

    StepVerifier.create(sCollection)
      .expectNext(List.of("Shoulder", "Something"))
      .verifyComplete();
  }

  // grouper Figures par couleur
  @Test
  void testGroupByFigureColor() {
    Flux<GroupedFlux<Color, Figure>> result = Workshop01FluxTransform.groupByFigureColor();

    List<GroupedFlux<Color, Figure>> collected = result.collectList().block();
    Assertions.assertNotNull(collected);
    Assertions.assertTrue(collected.size() > 0, "Collected content should not be empty");


    Mono<List<Figure>> greenCollection = collected.get(0).collectList();
    Mono<List<Figure>> blueCollection = collected.get(1).collectList();
    Mono<List<Figure>> redCollection = collected.get(2).collectList();

    StepVerifier.create(greenCollection)
      .expectNext(List.of(new Figure(SQUARE, GREEN), new Figure(SQUARE, GREEN)))
      .verifyComplete();

    StepVerifier.create(blueCollection)
      .expectNext(List.of(new Figure(CIRCLE, BLUE), new Figure(TRIANGLE, BLUE)))
      .verifyComplete();

    StepVerifier.create(redCollection)
      .expectNext(List.of(new Figure(CIRCLE, RED), new Figure(CIRCLE, RED)))
      .verifyComplete();
  }

  // grouper Figures par forme
  @Test
  void testGroupByFigureColorMapShapeToValue() {
    Flux<GroupedFlux<Color, Shape>> result = Workshop01FluxTransform.groupByFigureColorMapShapeToValue();

    List<GroupedFlux<Color, Shape>> collected = result.collectList().block();
    Assertions.assertNotNull(collected);
    Assertions.assertTrue(collected.size() > 0, "Collected content should not be empty");

    Mono<List<Shape>> greenCollection = collected.get(0).collectList();
    Mono<List<Shape>> blueCollection = collected.get(1).collectList();
    Mono<List<Shape>> redCollection = collected.get(2).collectList();

    StepVerifier.create(greenCollection)
      .expectNext(List.of(SQUARE, SQUARE))
      .verifyComplete();

    StepVerifier.create(blueCollection)
      .expectNext(List.of(CIRCLE, TRIANGLE))
      .verifyComplete();

    StepVerifier.create(redCollection)
      .expectNext(List.of(CIRCLE, CIRCLE))
      .verifyComplete();
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
