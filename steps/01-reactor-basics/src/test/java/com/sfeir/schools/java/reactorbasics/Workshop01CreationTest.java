package com.sfeir.schools.java.reactorbasics;

import com.sfeir.schools.java.reactorbasics.commons.Workshop01Creation;
import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.FigureProvider;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.CIRCLE;
import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.SQUARE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Workshop01CreationTest {

  private ColorProvider colorProvider;
  private ShapeProvider shapeProvider;
  private Workshop01Creation workshop01Creation;
  @Mock
  private ShapeProvider shapeProviderMock;
  @InjectMocks
  private Workshop01Creation workshop01CreationMock = new Workshop01Creation();

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

    // Vérifie que randomShape() a été appelé 4 fois (une fois par chaque souscription et par mock)
    verify(shapeProviderMock, times(4)).randomShape();
  }

  @Test
  public void testSubscribeFluxWithLambdas_printError() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    workshop01Creation.subscribeFluxWithLambdas(FigureProvider.getFiguresConstantFluxWithError());

    // Convertir la sortie en liste de lignes
    List<String> outputLines = Arrays.asList(outputStream.toString().split("\n"));

    // Vérifier que 4 formes ont été émises
    assertEquals(4, outputLines.size());

    assertEquals("Figure suivante : ◻ green", outputLines.get(0));
    assertEquals("Figure suivante : ◯ blue", outputLines.get(1));
    assertEquals("Figure suivante : ◯ red", outputLines.get(2));
    assertEquals("Une erreur s'est produite : java.lang.RuntimeException", outputLines.get(3));
  }

  @Test
  public void testSubscribeFluxWithLambdas_printSuccess() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    workshop01Creation.subscribeFluxWithLambdas(FigureProvider.getThreeFiguresConstantFlux());

    // Convertir la sortie en liste de lignes
    List<String> outputLines = Arrays.asList(outputStream.toString().split("\n"));

    // Vérifier que 4 formes ont été émises
    assertEquals(4, outputLines.size());

    assertEquals("Figure suivante : ◻ green", outputLines.get(0));
    assertEquals("Figure suivante : ◯ blue", outputLines.get(1));
    assertEquals("Figure suivante : ◯ red", outputLines.get(2));
    assertEquals("Flux terminé", outputLines.get(3));
  }

  @Test
  public void testSubscribeFluxWithLambdas_printFourFigures() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    workshop01Creation.subscribeFluxWithLambdas(FigureProvider.getFiguresConstantFlux());

    // Convertir la sortie en liste de lignes
    List<String> outputLines = Arrays.asList(outputStream.toString().split("\n"));

    // Vérifier que 4 formes ont été émises
    assertEquals(4, outputLines.size());

    assertEquals("Figure suivante : ◻ green", outputLines.get(0));
    assertEquals("Figure suivante : ◯ blue", outputLines.get(1));
    assertEquals("Figure suivante : ◯ red", outputLines.get(2));
    assertEquals("Figure suivante : △ blue", outputLines.get(3));
  }

}
