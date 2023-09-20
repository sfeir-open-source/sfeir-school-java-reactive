package com.sfeir.schools.java.reactorerrors;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.sfeir.schools.java.reactorerrors.commons.Workshop02Errors;
import com.sfeir.schools.java.reactorerrors.commons.domain.Color;
import com.sfeir.schools.java.reactorerrors.commons.domain.Figure;
import com.sfeir.schools.java.reactorerrors.commons.domain.Shape;
import com.sfeir.schools.java.reactorerrors.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorerrors.commons.services.FigureProvider;
import com.sfeir.schools.java.reactorerrors.commons.services.ShapeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Workshop02ErrorsTest {

  ColorProvider colorProvider;
  ShapeProvider shapeProvider;
  FigureProvider figureProvider;
  private Workshop02Errors workshop02errors;

  @BeforeEach
  public void setUp() {
    colorProvider = new ColorProvider();
    shapeProvider = new ShapeProvider();
    figureProvider = new FigureProvider();
    workshop02errors = new Workshop02Errors();
    workshop02errors.colorProvider = colorProvider;
    workshop02errors.shapeProvider = shapeProvider;
    workshop02errors.figureProvider = figureProvider;
  }

  @Test
  public void testOnErrorReturnRed() {
    Flux<Color> colorFlux = workshop02errors.onErrorReturnRed();
    StepVerifier.create(colorFlux)
      .expectNextCount(3)
      .expectNext(Color.RED)
      .expectComplete()
      .verify();
  }

  @Test
  public void testOnErrorReturnDefaultFlux_FiveSquareFlux() {
    List<Shape> defaultShapesFlux = Arrays.asList(Shape.SQUARE, Shape.SQUARE, Shape.SQUARE, Shape.SQUARE, Shape.SQUARE);

    Flux<Shape> shapeFlux = workshop02errors.onErrorReturnDefaultFlux();
    StepVerifier.create(shapeFlux)
      .expectNextCount(3)
      .expectNextSequence(defaultShapesFlux)
      .expectComplete()
      .verify();
  }

  @Test
  public void testOnErrorRetryOneTime_returnAfterRetry() {
    Flux<Figure> figuresFlux = workshop02errors.onErrorRetryOneTime();

    StepVerifier.create(figuresFlux)
      .expectNextCount(8)
      .expectComplete()
      .verify(Duration.ofSeconds(1)); // Vérifie que le flux se termine en moins de 1 seconde
  }

  @Test
  public void testOnErrorRetryAfterOneSecond_returnDurationOneSec() {
    Flux<Figure> figuresFlux = workshop02errors.onErrorRetryAfterOneSecond();

    StepVerifier.create(figuresFlux)
      .expectNextCount(8)
      .expectComplete()
      .verify(Duration.ofSeconds(2)); // Vérifie que le flux se termine en moins de 1 seconde
  }

  @Test
  public void testOnErrorReturnCustomException_returnCorrectException() {
    Flux<Figure> figuresFlux = workshop02errors.onErrorReturnCustomException();

    StepVerifier.create(figuresFlux)
      .expectNextCount(3) // Le flux devrait émettre 4 éléments avant de générer une exception
      .expectErrorMatches(error -> error instanceof Exception &&
        "Erreur sur le flux de figures".equals(error.getMessage()))
      .verify();
  }

  @Test
  public void testOnErrorPrintLogError_printCorrectLog() {
    Flux<Figure> figuresFlux = workshop02errors.onErrorPrintLogError();

    Logger logger = (Logger) LoggerFactory.getLogger(Workshop02Errors.class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);

    StepVerifier.create(figuresFlux)
      .expectNextCount(3) // Le flux devrait émettre 4 éléments avant de générer une exception
      .expectError(RuntimeException.class)
      .verify();

    boolean hasLoggedError = listAppender.list.stream()
      .anyMatch(event -> event.getLevel().equals(Level.ERROR) &&
        "Erreur rencontrée".equals(event.getFormattedMessage()));

    assert(hasLoggedError);
  }

  @Test
  public void testOnErrorRetryThenExceptionAndLog_returnExceptionAfterRetry() {
    Flux<Shape> shapeFlux = workshop02errors.onErrorRetryThenExceptionAndLog();

    Logger logger = (Logger) LoggerFactory.getLogger(Workshop02Errors.class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);

    StepVerifier.create(shapeFlux)
      .expectNextCount(9)
      .expectErrorMatches(error -> error instanceof Exception &&
        "Erreur lors de l'émission du Flux de Shape".equals(error.getMessage()))
      .verify(Duration.ofSeconds(5));

    boolean hasLoggedError = listAppender.list.stream()
      .anyMatch(event -> event.getLevel().equals(Level.ERROR) &&
        "Erreur loggée : Erreur lors de l'émission du Flux de Shape".equals(event.getFormattedMessage()));

    assert(hasLoggedError);
  }

  @Test
  public void testOnErrorLogRetryElseLogAndDefaultFlux_returnDefaultFluxAndLog() {
    List<Shape> defaultShapesFlux = Arrays.asList(Shape.CIRCLE, Shape.SQUARE, Shape.SQUARE, Shape.TRIANGLE, Shape.SQUARE);

    Flux<Shape> shapeFlux = workshop02errors.onErrorLogRetryElseLogAndDefaultFlux();

    Logger logger = (Logger) LoggerFactory.getLogger(Workshop02Errors.class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);

    StepVerifier.create(shapeFlux)
      .expectNextCount(6)
      .expectNextSequence(defaultShapesFlux)
      .expectComplete()
      .verify();

    boolean hasLoggedError = listAppender.list.stream()
      .anyMatch(event -> event.getLevel().equals(Level.ERROR) &&
        "Tentative échouée, retour du Flux par défaut".equals(event.getFormattedMessage()));

    assert(hasLoggedError);
  }
}
