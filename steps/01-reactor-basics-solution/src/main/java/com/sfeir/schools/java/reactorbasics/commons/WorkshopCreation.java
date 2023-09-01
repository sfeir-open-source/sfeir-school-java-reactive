package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class WorkshopCreation {

  public ColorProvider colorProvider;
  public ShapeProvider shapeProvider;

  // Création d'un mono vide
  public Mono<Shape> createMonoEmpty() {
    // Retourner un Mono vide
    return Mono.empty();
  }

  // Création d'un mono à partir d'un élément indépendant
  public Mono<Shape> createMonoShapeWithOneShape() {
    // Création d'une forme générée aléatoirement à partir d'un enum
    Shape randomShape = shapeProvider.randomShape();

    // Retourner un Mono qui contient cette couleur
    return Mono.just(randomShape);
  }

  // Création d'un flux vide
  public Flux<Color> createFluxEmpty() {
    // Retourner un Flux vide
    return Flux.empty();
  }

  // Création d'un flux à partir d'éléments indépendant
  public Flux<Color> createFluxColorsWithThreeColors() {
    // Création de plusieurs couleurs générées aléatoirement à partir d'un enum
    Color randomColor1 = colorProvider.randomColor();
    Color randomColor2 = colorProvider.randomColor();
    Color randomColor3 = colorProvider.randomColor();

    // Retourner un FLux qui contient ces 3 couleurs
    return Flux.just(randomColor1, randomColor2, randomColor3);
  }

  // Création d'un flux à partir d'une liste d'élément
  public Flux<Color> createFluxColorsWithList() {
    // Création d'une liste de 3 couleurs générées aléatoirement à partir d'un enum
    List<Color> listColors = colorProvider.randomListColor(3);

    // Créer un Flux à partir de cette liste et le retourner
    return Flux.fromIterable(listColors);
  }

  // TODO : corriger la diapo l'utilisation de range est confuse
  // Création d'un flux émettant une séquence d'entier de 6 à 11
  public Flux<Integer> createFluxSequenceInteger() {
    // Création d'un flux émettant une séquence d'entier de 6 à 11
    return Flux.range(6, 6);
  }

  // Méthode pour créer un Mono utilisant Mono.defer()
  public Mono<Shape> createMonoWithDefer() {
    // Utilisation de Mono.defer() pour créer un Mono
    return Mono.defer(() -> {
      // À l'intérieur de la lambda, nous générons un Shape aléatoire
      Shape randomShape = new ShapeProvider().randomShape();

      // Retourne un Mono contenant le Shape aléatoire
      return Mono.just(randomShape);
    });
  }

  public Mono<Shape> callMonoWithDefer() {
    // Appel de la méthode createMonoWithDefer() pour obtenir le Mono
    Mono<Shape> shapeMono = createMonoWithDefer();

    // Souscription pour observer les émissions du Mono
    shapeMono.subscribe(shape -> System.out.println("Emitted Shape: " + shape.getLabel()));

    return shapeMono;
  }
}
