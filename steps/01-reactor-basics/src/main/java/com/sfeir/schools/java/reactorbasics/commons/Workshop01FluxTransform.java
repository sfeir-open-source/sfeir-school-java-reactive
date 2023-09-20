package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.FigureProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

public class Workshop01FluxTransform {

  /**
   * En source un Flux de formes
   * Il faut les remplacer par leur attribut symbol
   */
  public static Flux<String> mapShapesToSymbols() {
    Flux<Shape> shapes = Flux.just(CIRCLE, CIRCLE, SQUARE, TRIANGLE, SQUARE);

    // transformer le contenu du Flux de Shape en leur attribut de symbole
    return Flux.empty();
  }

  /**
   * En source un Flux de couleurs
   * Il faut les remplacer par leur attribut code hexa
   */
  public static Flux<String> mapColorsToHexaCode() {
    Flux<Color> colors = Flux.just(RED, RED, GREEN, YELLOW);

    // transformer le contenu du Flux de Color leur attribut de code hexa
    return Flux.empty();
  }

  /**
   * En source un Flux de formes
   * Il faut les remplacer par leur attribut code hexa
   * malgré un mapper qui produit lui même un Flux à la transformation
   */
  public static Flux<String> flatMapShapeToFluxOfSymbol() {
    // Utilisation d'une fonction de mapping qui transforme un objet Shape en Flux de son symbole
    Function<Shape, Flux<String>> symbolMapper = shape -> Flux.just(shape.getSymbol());

    Flux<Shape> shapes = ShapeProvider.getConstantShapes();

    // le flatmap désencapsule le contenu du Publisher produit par la fonction de mapping
    return Flux.empty();
  }

  /**
   * En source un Flux de formes
   * Il faut les remplacer par leur attribut code hexa
   * malgré un mapper qui produit lui même un Mono à la transformation
   */
  public static Flux<String> flatMapShapeToFluxOfMonoSymbol() {
    // définit un mapper qui transforme un objet Shape en Mono de son symbole
    Function<Shape, Mono<String>> symbolMapper = shape -> Mono.just(shape.getSymbol());

    Flux<Shape> shapes = ShapeProvider.getConstantShapes();

    // le flatmap désencapsule le contenu du Publisher produit par la fonction de mapping
    return Flux.empty();
  }

  /**
   * En source un Flux de mots
   * Il faut les regrouper par première lettre
   */
  static Flux<GroupedFlux<Character, String>> groupByFirstChar() {
    // on commence avec une séquence de mots
    Flux<String> words = Flux.just("Handle", "Big", "Shoulder", "Something", "Here");

    // on regroupe les mots sur la base de leur première lettre
    return Flux.empty();
  }

  /**
   * En source un Flux de Figure
   * Il faut les regrouper par leur couleur
   */
  static Flux<GroupedFlux<Color, Figure>> groupByFigureColor() {
    // on commence avec une séquence de Figures fixe et prédéfinie
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();
    // on regroupe les éléments par même forme
    return Flux.empty();
  }

  /**
   * En source un Flux de Figure
   * Il faut les regrouper par leur couleur mais changer la valeur par la forme
   */
  static Flux<GroupedFlux<Color, Shape>> groupByFigureColorMapShapeToValue() {
    // on commence avec une séquence de Figures fixe et prédéfinie
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();
    // on regroupe les éléments par même forme, en remplaçant la valeur par une de ses propriétés
    return Flux.empty();
  }

  /**
   * En source un Flux de Forme
   * Il faut les rassembler dans un Mono
   */
  public static Mono<List<Shape>> collectFluxToMono() {
    Flux<Shape> shapes = ShapeProvider.getConstantShapes();

    // transformer le Flux de Shape en un Mono d'une liste de Shape
    return Mono.empty();
  }

}
