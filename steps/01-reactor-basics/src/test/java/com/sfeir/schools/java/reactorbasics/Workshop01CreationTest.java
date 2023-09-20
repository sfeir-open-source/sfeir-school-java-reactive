package com.sfeir.schools.java.reactorbasics;

import com.sfeir.schools.java.reactorbasics.commons.Workshop01Creation;
import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO to rename or separate more (Subscribes & Creations ? Creation before ? then Manupilations ?)
public class Workshop01CreationTest {

  private ColorProvider colorProvider;
  private ShapeProvider shapeProvider;
  private Workshop01Creation workshop01Creation;

  @BeforeEach
  void setUp() {
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
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    workshop01Creation.createMonoWithDefer();

    // Convertir la sortie en liste de lignes
    List<String> outputLines = Arrays.asList(outputStream.toString().split("\n"));

    // Vérifier que 4 formes ont été émises
    assertEquals(1, outputLines.size());

    // Vérifier que chaque ligne contient le texte "Received shape:"
    for (String line : outputLines) {
      assertTrue(line.contains("La forme émise : "));
    }
  }

}
