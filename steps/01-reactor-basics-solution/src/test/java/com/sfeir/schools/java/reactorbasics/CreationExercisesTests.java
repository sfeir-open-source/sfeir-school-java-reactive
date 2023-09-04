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
import org.mockito.Mock;
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
import static org.mockito.Mockito.*;

//TODO to rename or separate more (Subscribes & Creations ? Creation before ? then Manupilations ?)
public class CreationExercisesTests {

  @Mock
  private ColorProvider colorProviderMock;

  @Mock
  private ShapeProvider shapeProviderMock;

  private WorkshopCreation workshopCreation;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    workshopCreation = new WorkshopCreation();
    workshopCreation.colorProvider = colorProviderMock;
    workshopCreation.shapeProvider = shapeProviderMock;
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
    when(shapeProviderMock.randomShape()).thenReturn(SQUARE);
    Mono<Shape> shapeMono = workshopCreation.createMonoShapeWithOneShape();

    StepVerifier.create(shapeMono)
      .expectNextMatches(color -> color.equals(SQUARE))
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
    Color blue = Color.BLUE;
    Color green = Color.GREEN;
    Color red = Color.RED;

    when(colorProviderMock.randomColor()).thenReturn(blue, green, red);

    Flux<Color> resultFlux = workshopCreation.createFluxColorsWithThreeColors();

    List<Color> expectedColors = Arrays.asList(blue, green, red);
    List<Color> actualColors = resultFlux.collectList().block();
    assertEquals(expectedColors, actualColors);

    verify(colorProviderMock, times(3)).randomColor();
  }

  @Test
  void testCreateFluxColorsWithList() {
    List<Color> mockColors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE);
    when(colorProviderMock.randomListColor(3)).thenReturn(mockColors);

    Flux<Color> resultFlux = workshopCreation.createFluxColorsWithList();

    assertEquals(resultFlux.collectList().block(), mockColors);
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

    List<Shape> shapeList = Arrays.asList(CIRCLE, SQUARE, SQUARE, TRIANGLE);
    when(shapeProviderMock.getRandomShapes(4)).thenReturn(Flux.fromIterable(shapeList));
    workshopCreation.createAndDisplayFluxWithShapes();
    System.setOut(originalOut);

    String consoleOutput = outputStream.toString();
    String[] lines = consoleOutput.split(System.lineSeparator());

    List<String> expectedShapes = List.of("circle", "square", "square", "triangle");

    for (int i = 0; i < expectedShapes.size(); i++) {
      assertEquals("Received shape: " + expectedShapes.get(i), lines[i].trim());
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
  void generateShapes() {
    List<Shape> shapeList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      shapeList.add(SQUARE);
    }

    when(shapeProviderMock.getInfiniteRandomShapes()).thenReturn(Flux.fromIterable(shapeList));

    List<Shape> shapes = shapeProviderMock.getInfiniteRandomShapes()
      .take(10)
      .collectList()
      .block();

    System.out.println(shapes);
  }

}