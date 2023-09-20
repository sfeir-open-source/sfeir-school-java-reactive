package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.FigureProvider;
import reactor.core.publisher.Flux;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;

public class Workshop01FluxFilter {

  /**
   * La source est un Flux de nombre.
   * Il faut filtrer les nombres pour ne garder que les nombres pairs
   */
  static Flux<Integer> filterNumberOnParity() {
    // on débute avec un Flux qui contient des nombres de 1 à 10 (inclus)
    Flux<Integer> numbers = Flux.range(1, 10);

    // on applique un filtre pour renvoyer les nombres pairs uniquement
    return numbers;
  }

  /**
   * La source est un Flux de Figure
   * Il faut les filtrer pour ne garder que celles ayant la couleur rouge
   */
  static Flux<Figure> filterFigureWithColorRed() {
    // on débute avec un Flux qui contient une séquence de Figure
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();

    // on ajoute un filtre pour ne garder que les figure ayant la couleur "RED"
    return figures;
  }

  /**
   * La source est un Flux de Figure
   * Il faut les filtrer pour ne garder que celles ayant une forme ronde
   */
  static Flux<Figure> filterFigureWithShapeCircle() {
    // on débute avec un Flux qui contient une séquence de Figure
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();

    // on ajoute un filtre pour ne garder que les figure ayant la forme "CIRCLE"
    return figures;
  }

  /**
   * La source est un Flux de Figure
   * Il faut appliquer un filtre pour ne garder que celle de couleur bleue et de forme triangulaire
   */
  static Flux<Figure> filterFigureWithColorBlueAndShapeTriangle() {
    // on débute avec un Flux qui contient une séquence de Figure
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();

    // on ajoute un filtre pour ne garder que les figures ayant la couleur "BLUE" et la forme "TRIANGLE"
    return figures;
  }

  /**
   * La source est un Flux de nombres désordonné avec des doublons
   * Il faut supprimer les doublons
   */
  static Flux<Integer> distinctRepeatedNumbers() {
    // on débute avec un flux de nombres avec doublons
    Flux<Integer> numbers = Flux.just(1, 2, 1, 2, 2, 3, 4, 4, 5);
    // on dédoublonne le flux
    return numbers;
  }

  /**
   * La source est un Flux de Figures désordonné avec des doublons
   * Il faut supprimer les doublons
   */
  static Flux<Figure> distinctRepeatedFigure() {
    // on débute avec un flux de nombres avec doublons
    Flux<Figure> figure = FigureProvider.getFiguresConstantFlux();
    // on dédoublonne le flux
    return figure;
  }

  /**
   * La source est un Flux de Figures désordonné avec des doublons
   * Il faut supprimer les doublons en se basant uniquement sur la forme
   */
  static Flux<Figure> distinctRepeatedFiguresBasedOnShape() {
    // on débute avec un flux de figures avec doublons
    Flux<Figure> figure = FigureProvider.getFiguresConstantFlux();
    // on dédoublonne les figures en se basant uniquement sur leur forme
    return figure;
  }

  /**
   * La source est un Flux de Figures désordonné avec des doublons
   * Il faut supprimer les doublons en se basant uniquement sur la couleur
   */
  static Flux<Figure> distinctRepeatedFiguresBasedOnColor() {
    // on débute avec un flux de figures avec doublons
    Flux<Figure> figure = FigureProvider.getFiguresConstantFlux();
    // on dédoublonne les figures en se basant uniquement sur leur couleur
    return figure;
  }

  /**
   * On part d'une séquence de nombre uniques et désordonnés
   * Il faut trier le Flux par ordre naturel
   */
  static Flux<Integer> sortByNaturalOrder() {
    // Partir depuis un flux de nombre désordonné
    Flux<Integer> unorderedNumbers = Flux.just(3, 5, 4, 1, 2, 7, 6);

    // Le tri par défaut s'effectue par ordre naturel, mais sur la base du hashcode pour les objets complexes
    return unorderedNumbers;
  }

  /**
   * On part d'une séquence de couleurs
   * Il faut trier le Flux selon l'attribut `grade` des couleurs par ordre croissant
   */
  static Flux<Color> sortColorsByGradeAscending() {
    Flux<Color> colors = Flux.just(RED, GREEN, BLUE, YELLOW);

    // trier le contenu flux par `grade` de Color croissant
    return colors;
  }

  /**
   * On part d'une séquence de formes
   * Il faut trier le Flux selon l'attribut `sides` des formes, par ordre décroissant
   */
  static Flux<Shape> sortShapesBySidesDescending() {
    Flux<Shape> colors = Flux.just(Shape.values());

    // trier le contenu flux par `sides` de Shape décroissant
    return colors;
  }

}
