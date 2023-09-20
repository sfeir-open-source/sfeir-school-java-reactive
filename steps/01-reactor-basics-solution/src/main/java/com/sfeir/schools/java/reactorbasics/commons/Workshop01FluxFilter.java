package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.FigureProvider;
import reactor.core.publisher.Flux;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.YELLOW;

public class Workshop01FluxFilter {

  static Flux<Integer> filterNumberOnParity() {
    // on débute avec un Flux qui contient des nombres de 1 à 10 (inclus)
    Flux<Integer> numbers = Flux.range(1, 10);

    // on applique un filtre pour renvoyer les nombres pairs uniquement
    return numbers.filter(n -> n % 2 == 0);
  }

  static Flux<Figure> filterFigureWithColorRed() {
    // on débute avec un Flux qui contient une séquence de Figure
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();

    // on ajoute un filtre pour ne garder que les figure ayant la couleur "RED"
    return figures.filter(figure -> figure.color() == Color.RED);
  }


  static Flux<Figure> filterFigureWithShapeCircle() {
    // on débute avec un Flux qui contient une séquence de Figure
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();

    // on ajoute un filtre pour ne garder que les figure ayant la forme "CIRCLE"
    return figures.filter(figure -> figure.shape() == Shape.CIRCLE);
  }

  static Flux<Figure> filterFigureWithColorBlueAndShapeTriangle() {
    // on débute avec un Flux qui contient une séquence de Figure
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();

    // on ajoute un filtre pour ne garder que les figures ayant la couleur "BLUE" et la forme "TRIANGLE"
    return figures.filter(figure -> figure.color() == Color.BLUE && figure.shape() == Shape.TRIANGLE);
    /* variante possible, combiner plusieurs filter() avec un prédicat chacun
    return figures
      .filter(figure -> figure.color() == Color.BLUE)
      .filter(figure -> figure.shape() == Shape.TRIANGLE)
     */
  }


  static Flux<Integer> distinctRepeatedNumbers() {
    // on débute avec un flux de nombres avec doublons
    Flux<Integer> numbers = Flux.just(1, 2, 1, 2, 2, 3, 4, 4, 5);
    // on dédoublonne le flux
    return numbers.distinct();
  }

  static Flux<Figure> distinctRepeatedFigure() {
    // on débute avec un flux de nombres avec doublons
    Flux<Figure> figure = FigureProvider.getFiguresConstantFlux();
    // on dédoublonne le flux
    return figure.distinct();
  }

  static Flux<Figure> distinctRepeatedFiguresBasedOnShape() {
    // on débute avec un flux de figures avec doublons
    Flux<Figure> figure = FigureProvider.getFiguresConstantFlux();
    // on dédoublonne les figures en se basant uniquement sur leur forme
    return figure.distinct(Figure::shape);
  }

  static Flux<Figure> distinctRepeatedFiguresBasedOnColor() {
    // on débute avec un flux de figures avec doublons
    Flux<Figure> figure = FigureProvider.getFiguresConstantFlux();
    // on dédoublonne les figures en se basant uniquement sur leur couleur
    return figure.distinct(Figure::color);
  }

  static Flux<Integer> sortByNaturalOrder() {
    // Partir depuis un flux de nombre désordonné
    Flux<Integer> unorderedNumbers = Flux.just(3, 5, 4, 1, 2, 7, 6);

    // Le tri par défaut s'effectue par ordre naturel, mais sur la base du hashcode pour les objets complexes
    return unorderedNumbers.sort();
  }

  static Flux<Color> sortColorsByGradeAscending() {
    Flux<Color> colors = Flux.just(RED, GREEN, BLUE, YELLOW);

    // trier le contenu flux par `grade` de Color croissant
    return colors.sort((a, b) -> a.getGrade() - b.getGrade());
  }

  static Flux<Shape> sortShapesBySidesDescending() {
    Flux<Shape> colors = Flux.just(Shape.values());

    // trier le contenu flux par `sides` de Shape décroissant
    return colors.sort((a, b) -> b.getSides() - a.getSides());
  }

}
