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

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;

public class Workshop01FluxTransform {

  public static Flux<String> mapShapesToSymbols() {
    Flux<Shape> shapes = Flux.just(CIRCLE, CIRCLE, SQUARE, TRIANGLE, SQUARE);

    // transformer le contenu du Flux de Shape en leur attribut de symbole
    return shapes.map(Shape::getSymbol);
  }

  public static Flux<String> mapColorsToHexaCode() {
    Flux<Color> colors = Flux.just(RED, RED, GREEN, YELLOW);

    // transformer le contenu du Flux de Color leur attribut de code hexa
    return colors.map(Color::getHex);
  }

  public static Flux<String> flatMapShapeToFluxOfSymbol() {
    // définit un mapper qui transforme un objet Shape en Flux de son symbole
    Function<Shape, Flux<String>> symbolMapper = shape -> Flux.just(shape.getSymbol());

    Flux<Shape> shapes = ShapeProvider.getConstantShapes();

    // le flatmap désencapsule le contenu du Publisher produit par la fonction de mapping
    return shapes.flatMap(symbolMapper);
  }

  public static Flux<String> flatMapShapeToFluxOfMonoSymbol() {
    // définit un mapper qui transforme un objet Shape en Mono de son symbole
    Function<Shape, Mono<String>> symbolMapper = shape -> Mono.just(shape.getSymbol());

    Flux<Shape> shapes = ShapeProvider.getConstantShapes();

    // le flatmap désencapsule le contenu du Publisher produit par la fonction de mapping
    return shapes.flatMap(symbolMapper);
  }

  static Flux<GroupedFlux<Character, String>> groupByFirstChar() {
    // on commence avec une séquence de mots
    Flux<String> words = Flux.just("Handle", "Big", "Shoulder", "Something", "Here");

    // on regroupe les mots sur la base de leur première lettre
    return words.groupBy(word -> word.charAt(0));
  }

  static Flux<GroupedFlux<Color, Figure>> groupByFigureColor() {
    // on commence avec une séquence de Figures fixe et prédéfinie
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();
    // on regroupe les éléments par même forme
    return figures.groupBy(Figure::color);
  }

  static Flux<GroupedFlux<Color, Shape>> groupByFigureColorMapShapeToValue() {
    // on commence avec une séquence de Figures fixe et prédéfinie
    Flux<Figure> figures = FigureProvider.getFiguresConstantFlux();
    // on regroupe les éléments par même forme, en remplaçant la valeur par une de ses propriétés
    return figures.groupBy(Figure::color, Figure::shape);
  }

  public static Mono<List<Shape>> collectFluxToMono() {
    Flux<Shape> shapes = ShapeProvider.getConstantShapes();

    // transformer le Flux de Shape en un Mono d'une liste de Shape
    return shapes.collectList();
  }

}
