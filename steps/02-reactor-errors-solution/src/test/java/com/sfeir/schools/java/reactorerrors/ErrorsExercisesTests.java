package com.sfeir.schools.java.reactorerrors;

import com.sfeir.schools.java.reactorerrors.commons.WorkshopErrors;
import com.sfeir.schools.java.reactorerrors.commons.domain.Color;
import com.sfeir.schools.java.reactorerrors.commons.domain.Figure;
import com.sfeir.schools.java.reactorerrors.commons.domain.Shape;
import com.sfeir.schools.java.reactorerrors.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorerrors.commons.services.FigureProvider;
import com.sfeir.schools.java.reactorerrors.commons.services.ShapeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class ErrorsExercisesTests {

  ColorProvider colorProvider;
  ShapeProvider shapeProvider;
  FigureProvider figureProvider;
  private WorkshopErrors workshopErrors;

  @BeforeEach
  public void setUp() {
    colorProvider = new ColorProvider();
    shapeProvider = new ShapeProvider();
    figureProvider = new FigureProvider();
    workshopErrors = new WorkshopErrors();
    workshopErrors.colorProvider = colorProvider;
    workshopErrors.shapeProvider = shapeProvider;
    workshopErrors.figureProvider = figureProvider;
  }

  @Test
  public void testOnErrorReturnRed() {
    Flux<Color> colorFlux = workshopErrors.onErrorReturnRed();
    StepVerifier.create(colorFlux)
      .expectNextCount(3)
      .expectNext(Color.RED)
      .expectComplete()
      .verify();
  }

  @Test
  public void testOnErrorResumeFlux() {
    List<Shape> defaultShapesFlux = Arrays.asList(Shape.SQUARE, Shape.SQUARE, Shape.SQUARE, Shape.SQUARE, Shape.SQUARE);

    Flux<Shape> shapeFlux = workshopErrors.onErrorResumeFlux();
    StepVerifier.create(shapeFlux)
      .expectNextCount(3)
      .expectNextSequence(defaultShapesFlux)
      .expectComplete()
      .verify();
  }

  @Test
  public void testReturnFigureAfterRetry() {
    Flux<Figure> figuresFlux = workshopErrors.return5FigureAfterRetry();

    StepVerifier.create(figuresFlux)
      .expectNextCount(8)
      .expectComplete()
      .verify();
  }
}
