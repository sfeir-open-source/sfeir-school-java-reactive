package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Figure;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Workshop01Creation {

  public ColorProvider colorProvider;
  public ShapeProvider shapeProvider;

  /**
   * Création d'un mono vide
   * Corriger le return pour retourner un Mono vide
   * @return Mono<Shape>
   */
  public Mono<Shape> createMonoEmpty() {
    return Mono.just(Shape.SQUARE);
  }

  /**
   * Création d'un mono à partir d'un élément indépendant
   * Création d'une forme générée aléatoirement à partir d'un enum
   * Retourner un Mono qui contient cette couleur
   * @return Mono<Shape>
   */
  public Mono<Shape> createMonoShapeWithOneShape() {
    Shape randomShape = shapeProvider.randomShape();

    return Mono.empty();
  }

  /**
   * Création d'un flux vide
   * @return Flux<Color>
   */
  public Flux<Color> createFluxEmpty() {
    return Flux.just(Color.BLUE);
  }

  /**
   * Création d'un flux à partir d'éléments indépendant
   * Retourner un FLux qui contient ces 3 couleurs
   * @return Flux<Color>
   */
  public Flux<Color> createFluxColorsWithThreeColors() {
    // Création de plusieurs couleurs générées aléatoirement à partir d'un enum
    Color randomColor1 = colorProvider.randomColor();
    Color randomColor2 = colorProvider.randomColor();
    Color randomColor3 = colorProvider.randomColor();

    return Flux.empty();
  }

  /**
   * Création d'un flux à partir d'une liste d'élément
   * Créer un Flux à partir de cette liste et le retourner
   * @return Flux<Color>
   */
  public Flux<Color> createFluxColorsWithList() {
    // Création d'une liste de 3 couleurs générées aléatoirement à partir d'un enum
    List<Color> listColors = colorProvider.randomListColor(3);

    return Flux.empty();
  }

  /**
   * Création d'un flux émettant une séquence d'entier de 6 à 11
   * @return Flux<Integer>
   */
  public Flux<Integer> createFluxSequenceInteger() {
    return Flux.empty();
  }

  /**
   * Créer un Flux contenant des shapes
   * Souscrire à ce Flux
   * Afficher en console pour chaque shape par exemple : "Received shape: circle"
   */
  public void createAndDisplayFluxWithShapes() {
    // Création d'un flux de formes aléatoires de taille 4
    Flux<Shape> randomShapesFlux = shapeProvider.getRandomShapes(4);

    // Souscription au flux pour afficher chaque forme

  }

  /**
   * Créer un Mono utilisant Mono.defer()
   * Qui émet une randomShape()
   * Puis souscrivez à ce mono afin d'afficher dans la console "La forme émise : " suivis du label de cette shape
   * @return Mono<Shape>
   */
  public Mono<Shape> createMonoWithDefer() {
    return Mono.empty();
  }

  /**
   * Vous avez un Flux passé en paramètre
   * Souscrivez à ce flux
   * Puis pour chaque objet émis, affichez "Figure suivante : {shape_symbol} {color_label}"
   * Puis si une erreur survient : "Une erreur s'est produite : {error}"
   * Si pas d'erreur et que le Flux est terminé affichez "Flux terminé"
   * Ne prendre que les 4 premiers éléments émis
   */
  public void subscribeFluxWithLambdas(Flux<Figure> figureFlux) {


  }
}
