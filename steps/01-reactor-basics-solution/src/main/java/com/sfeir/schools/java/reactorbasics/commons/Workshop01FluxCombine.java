package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

public class Workshop01FluxCombine {

  /**
   * on a 2 flux, le premier étant retardé
   * Il faut étendre le premier avec le 2e
   * Le plus rapide sortira de toute façon en premier
   */
  static Flux<Color> mergeFluxOfColor() {
    Flux<Color> firstColors = Flux.just(RED, GREEN, GREEN, BLUE).delayElements(Duration.ofMillis(2));
    Flux<Color> secondColors = Flux.just(YELLOW, RED, BLUE, BLUE);

    return firstColors.mergeWith(secondColors);
  }

  /**
   * on a 2 flux, le premier étant retardé
   * Il faut étendre le premier avec le 2e
   * L'opérateur respecte strictement l'ordre des flux
   */
  static Flux<Color> concatFluxOfColors() {
    Flux<Color> firstColors = Flux.just(RED, GREEN, GREEN, BLUE).delayElements(Duration.ofMillis(2));
    Flux<Color> secondColors = Flux.just(YELLOW, RED, BLUE, BLUE);

    return firstColors.concatWith(secondColors);
  }

  /**
   * à partir d'un flux de couleur et un autre de formes
   * Il faut combiner le 2nd au 1er, et transformer les pairs de valeurs en objet Figure
   */
  static Flux<Figure> zipWithShapeAndColorsIntoFigure() {
    // les flux à combiner pour pouvoir créer des Figure à partir de pairs de valeur
    Flux<Color> colors = Flux.just(RED, GREEN, GREEN, YELLOW, RED, BLUE);
    Flux<Shape> shapes = Flux.just(CIRCLE, TRIANGLE, TRIANGLE, SQUARE, SQUARE, TRIANGLE);

    // utiliser l'opérateur zipWith pour manipuler un Color et un Shape à la fois
    return colors.zipWith(shapes, (color, shape) -> new Figure(shape, color));

    /* Peut également s'écrire avec un map sur un objet Tuple<T1, T2>
    Flux<Figure> result = colors.zipWith(shapes)
      .map(tuple -> new Figure(tuple.getT2(), tuple.getT1()));
     */
  }

  /**
   * à partir d'un flux de couleur et un autre de formes
   * Il faut appairer les 2 flux pour combiner les pairs de valeurs en objet Figure
   * Cette fois utiliser la méthode static de la classe Flux
   */
  static Flux<Figure> zipShapeAndColorsIntoFigure() {
    // les flux à combiner pour pouvoir créer des Figure à partir de pairs de valeur
    Flux<Color> colors = Flux.just(RED, GREEN, GREEN, YELLOW, RED, BLUE);
    Flux<Shape> shapes = Flux.just(CIRCLE, TRIANGLE, TRIANGLE, SQUARE, SQUARE, TRIANGLE);

    // utiliser l'opérateur static Flux.zip pour combiner les flux et manipuler un Color et un Shape à la fois
    return Flux.zip(shapes, colors,
      (shape, color) -> new Figure(shape, color));

    /* Peut également s'écrire avec un map sur un objet Tuple<T1, T2>
    Flux<Figure> result = Flux.zip(shapes, colors)
      .map(tuple -> new Figure(tuple.getT1(), tuple.getT2()));
     */
  }

}
