package com.sfeir.schools.java.reactorbasics;

import com.sfeir.schools.java.reactorbasics.commons.Workshop01Creation;
import com.sfeir.schools.java.reactorbasics.commons.WorkshopFluxTransformations;
import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class Workshop01CreationTest {

  private ColorProvider colorProvider;
  private ShapeProvider shapeProvider;
  private Workshop01Creation workshop01Creation;
  @Mock
  private ShapeProvider shapeProviderMock;
  private Workshop01Creation workshop01CreationMock;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    workshop01CreationMock = new Workshop01Creation();
    workshop01CreationMock.shapeProvider = shapeProviderMock;

    colorProvider = new ColorProvider();
    shapeProvider = new ShapeProvider();
    workshop01Creation = new Workshop01Creation();
    workshop01Creation.colorProvider = colorProvider;
    workshop01Creation.shapeProvider = shapeProvider;
  }

  @Test
  public void testCreateMonoEmpty_returnEmpty() {
    Workshop01Creation workshop = new Workshop01Creation();

    Mono<Shape> emptyMono = workshop.createMonoEmpty();

    StepVerifier.create(emptyMono)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateMonoColorWithOneColor_returnFluxWithShape() {
    Mono<Shape> shapeMono = workshop01Creation.createMonoShapeWithOneShape();

    List<Shape> shapeList = shapeProvider.getAllColors();

    StepVerifier.create(shapeMono)
      .expectNextMatches(shapeList::contains)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateFluxEmpty_returnFluxEmpty() {
    Flux<Color> emptyFlux = workshop01Creation.createFluxEmpty();

    StepVerifier.create(emptyFlux)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateFluxColorsWithThreeColors_returnThreeColors() {
    Flux<Color> resultFlux = workshop01Creation.createFluxColorsWithThreeColors();

    List<Color> colorList = colorProvider.getAllColors();

    // Création du StepVerifier
    StepVerifier.create(resultFlux)
      .expectNextMatches(colorList::contains)
      .expectNextMatches(colorList::contains)
      .expectNextMatches(colorList::contains)
      .expectComplete()
      .verify();
  }

  @Test
  void testCreateFluxColorsWithList_returnFlux() {
    Flux<Color> resultFlux = workshop01Creation.createFluxColorsWithList();
    List<Color> colorList = colorProvider.getAllColors();

    // Création du StepVerifier
    StepVerifier.create(resultFlux)
      .expectNextMatches(colorList::contains)
      .expectNextMatches(colorList::contains)
      .expectNextMatches(colorList::contains)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateFluxSequenceInteger_returnFluxSequence() {
    Flux<Integer> integerFlux = workshop01Creation.createFluxSequenceInteger();

    StepVerifier.create(integerFlux)
      .expectNext(6, 7, 8, 9, 10, 11)
      .expectComplete()
      .verify();
  }

  @Test
  public void testShapeSubscription() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    workshop01Creation.createAndDisplayFluxWithShapes();

    // Convertir la sortie en liste de lignes
    List<String> outputLines = Arrays.asList(outputStream.toString().split("\n"));

    // Vérifier que 4 formes ont été émises
    assertEquals(4, outputLines.size());

    // Vérifier que chaque ligne contient le texte "Received shape:"
    for (String line : outputLines) {
      assertTrue(line.contains("Received shape:"));
    }
  }

  @Test
  public void testCreateMonoWithDefer_printConsole() {

    when(shapeProviderMock.randomShape()).thenReturn(SQUARE);

    // 1ère souscription
    StepVerifier.create(workshop01CreationMock.createMonoWithDefer())
      .expectNextMatches(shape -> "square".equals(shape.getLabel()))
      .verifyComplete();

    when(shapeProviderMock.randomShape()).thenReturn(CIRCLE);

    // 2ème souscription, doit renvoyer une nouvelle forme
    StepVerifier.create(workshop01CreationMock.createMonoWithDefer())
      .expectNextMatches(shape -> "circle".equals(shape.getLabel()))
      .verifyComplete();

    // Vérifie que randomShape() a été appelé deux fois (une fois par chaque souscription)
    verify(shapeProviderMock, times(4)).randomShape();
  }

}
