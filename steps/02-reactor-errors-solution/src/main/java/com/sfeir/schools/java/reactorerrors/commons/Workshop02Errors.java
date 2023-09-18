package com.sfeir.schools.java.reactorerrors.commons;

import com.sfeir.schools.java.reactorerrors.commons.domain.Color;
import com.sfeir.schools.java.reactorerrors.commons.domain.Figure;
import com.sfeir.schools.java.reactorerrors.commons.domain.Shape;
import com.sfeir.schools.java.reactorerrors.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorerrors.commons.services.FigureProvider;
import com.sfeir.schools.java.reactorerrors.commons.services.ShapeProvider;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
public class Workshop02Errors {

  public ColorProvider colorProvider;
  public ShapeProvider shapeProvider;
  public FigureProvider figureProvider;

  /**
   * Appeler la méthode getRandomError de ColorProvider
   * Mais elle génére une erreur
   * Dans ce cas retourner la couleur RED
   * @return Flux<Color>
   */
  public Flux<Color> onErrorReturnRed() {
    // Appelle la méthode getRandomError pour obtenir un flux de couleurs aléatoires.
    // La méthode a été modifiée pour émettre une exception après l'émission de 3 couleurs.
    return colorProvider.getRandomError(5)
      // Utilise onErrorReturn pour spécifier une valeur de retour en cas d'erreur.
      // Ici, nous avons choisi de retourner la couleur ROUGE si une exception est levée.
      .onErrorReturn(Color.RED);
  }

  /**
   * Appeler la méthode getRandomError de ShapeProvider
   * Mais elle génére une erreur
   * Dans ce cas retourner le flux default
   * @return Flux<Color>
   */
  public Flux<Shape> onErrorReturnDefaultFlux() {
    Flux<Shape> defaultShapesFlux = Flux.just(Shape.SQUARE,Shape.SQUARE,Shape.SQUARE,Shape.SQUARE,Shape.SQUARE);

    // Appelle la méthode getRandomError pour obtenir un flux de shaoes aléatoires.
    // La méthode a été modifiée pour émettre une exception après l'émission de 3 shapes.
    return shapeProvider.getRandomError(5)
      // Utilise onErrorResume pour spécifier une valeur de retour en cas d'erreur.
      // Ici, nous avons choisi de retourner le flux defaultShapesFlux si une exception est levée.
      .onErrorResume(e -> {
        // System.out.println("Erreur interceptée : " + e.getMessage());
        return defaultShapesFlux;
      });
  }

  /**
   * Appeler la méthode getFiveFiguresAfterOneError()
   * si elle génére une erreur, relancé la
   * @return Flux<Figure>
   */
  public Flux<Figure> onErrorRetryOneTime() {
    return figureProvider.getFiveFiguresAfterOneError()
      .retry(1); // Relancer une fois en cas d'erreur
  }

  /**
   * Appeler la méthode getFiveFiguresAfterOneError()
   * si elle génère une erreur, attendez 3 secondes puis relancez la
   * @return Flux<Figure>
   */
  public Flux<Figure> onErrorRetryAfterOneSecond() {
    return figureProvider.getFiveFiguresAfterOneError()
      .retryWhen(Retry.backoff(1, Duration.ofSeconds(1)));
  }

  /**
   * Appeler la méthode getFiveFiguresAfterOneError()
   * si elle génère une erreur, retourner une CustomException contenant "Erreur sur le flux de figures"
   * @return Flux<Figure>
   */
  public Flux<Figure> onErrorReturnCustomException() {
    return figureProvider.getFiveFiguresAfterOneError()
      .onErrorMap(error -> new Exception("Erreur sur le flux de figures", error));
  }

  /**
   * Appeler la méthode getFiveFiguresAfterOneError()
   * si elle génère une erreur, afficher simplement une erreur dans les logs "Erreur rencontrée lors de l'émission des figures"
   * @return Flux<Figure>
   */
  public Flux<Figure> onErrorPrintLogError() {
    return figureProvider.getFiveFiguresAfterOneError()
      .doOnError(e -> log.error("Erreur rencontrée", e));
  }

  /**
   * Appeler la méthode getFiveShapesAfterOneError de shapeProvider
   * Si il y a une erreur tenter de la rappeler après 1 seconde puis après 2 secondes
   * Si elle tombe toujours en erreur lever l'exception "Erreur lors de l'émission du Flux de Shape"
   * et logger l'erreur "Erreur loggée : " + e.getMessage()
   * @return Flux<Shape>
   */
  public Flux<Shape> onErrorRetryThenExceptionAndLog() {
    return shapeProvider.getFiveShapesOnError()
      .retryWhen(Retry.backoff(2, Duration.ofSeconds(1)))
      .onErrorMap(error -> new Exception("Erreur lors de l'émission du Flux de Shape"))
      .doOnError(e -> log.error("Erreur loggée : " + e.getMessage()));
  }

  /**
   * Appeler la méthode getFiveShapesAfterOneError de shapeProvider
   * Si il y a une erreur créer une log erreur "Une erreur s'est produite"
   * Puis tenter de la rappeler 1 fois
   * Si elle tombe toujours en erreur créer une log erreur "Tentative échouée, retour du Flux par défaut"
   * et enfin retourner le Flux par défaut defaultShapeFlux
   * @return Flux<Shape>
   */
  public Flux<Shape> onErrorLogRetryElseLogAndDefaultFlux() {
    Flux<Shape> defaultShapeFlux = Flux.just(Shape.CIRCLE, Shape.SQUARE, Shape.SQUARE, Shape.TRIANGLE, Shape.SQUARE);
    return shapeProvider.getFiveShapesOnError()
      .doOnError(e -> log.error("Une erreur s'est produite"))
      .retry(1)
      .onErrorResume(e -> {
        log.error("Tentative échouée, retour du Flux par défaut");
        return defaultShapeFlux;
      });

  }
}
