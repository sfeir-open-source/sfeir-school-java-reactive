package com.sfeir.schools.java.reactorbasics;

import com.sfeir.schools.java.reactorbasics.commons.WorkshopCreation;
import com.sfeir.schools.java.reactorbasics.commons.WorkshopFluxTransformations;
import com.sfeir.schools.java.reactorbasics.commons.WorkshopSubscribe;
import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

//TODO to rename or separate more (Subscribes & Creations ? Creation before ? then Manupilations ?)
public class CreationExercisesTests {

  private ColorProvider colorProvider;
  private ShapeProvider shapeProvider;
  private WorkshopCreation workshopCreation;

  @BeforeEach
  void setUp() {
    colorProvider = new ColorProvider();
    shapeProvider = new ShapeProvider();
    workshopCreation = new WorkshopCreation();
    workshopCreation.colorProvider = colorProvider;
    workshopCreation.shapeProvider = shapeProvider;
  }

  @Test
  public void testCreateMonoEmpty() {
    WorkshopCreation workshop = new WorkshopCreation();

    Mono<Shape> emptyMono = workshop.createMonoEmpty();

    StepVerifier.create(emptyMono)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateMonoColorWithOneColor() {
    Mono<Shape> shapeMono = workshopCreation.createMonoShapeWithOneShape();

    List<Shape> shapeList = shapeProvider.getAllColors();

    StepVerifier.create(shapeMono)
      .expectNextMatches(shapeList::contains)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateFluxEmpty() {
    Flux<Color> emptyFlux = workshopCreation.createFluxEmpty();

    StepVerifier.create(emptyFlux)
      .expectComplete()
      .verify();
  }

  @Test
  public void testCreateFluxColorsWithThreeColors() {
    Flux<Color> resultFlux = workshopCreation.createFluxColorsWithThreeColors();

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
  void testCreateFluxColorsWithList() {
    //List<Color> mockColors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE);
    //when(colorProvider.randomListColor(3)).thenReturn(mockColors);

    Flux<Color> resultFlux = workshopCreation.createFluxColorsWithList();
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
  public void testCreateFluxSequenceInteger() {
    Flux<Integer> integerFlux = workshopCreation.createFluxSequenceInteger();

    StepVerifier.create(integerFlux)
      .expectNext(6, 7, 8, 9, 10, 11)
      .expectComplete()
      .verify();
  }

  @Test
  public void testShapeSubscription() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    workshopCreation.createAndDisplayFluxWithShapes();

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
  void test_subscribe_transform_into_symbol() {
    //to improve ? await for dispable to be done ? require to return list instead but filled from the subscribe(...) ?
    WorkshopSubscribe.subscribeShapeIntoSymbol();
  }

  @Test
  void test_transform_symbol() {
    StepVerifier.create(WorkshopFluxTransformations.transformShapeIntoSymbol())
      .expectNext(CIRCLE.getSymbol(), SQUARE.getSymbol(), SQUARE.getSymbol(), TRIANGLE.getSymbol())
      .verifyComplete();
  }

  @Test
  public void testGetInfiniteRandomShapes() {
    Flux<Shape> infiniteShapes = shapeProvider.getInfiniteRandomShapes();

    StepVerifier.create(infiniteShapes.take(10))
      .expectNextCount(10)
      .expectComplete()
      .verify();
  }

}
