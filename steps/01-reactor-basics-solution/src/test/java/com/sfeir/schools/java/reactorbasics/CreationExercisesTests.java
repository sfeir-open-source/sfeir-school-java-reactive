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
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//TODO to rename or separate more (Subscribes & Creations ? Creation before ? then Manupilations ?)
public class CreationExercisesTests {

  @Mock
  private ColorProvider colorProviderMock;

  private WorkshopCreation workshopCreation;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    workshopCreation = new WorkshopCreation();
    workshopCreation.colorProvider = colorProviderMock;
  }

  @Test
  void testCreateFluxColorsWithList() {
    List<Color> mockColors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE);
    when(colorProviderMock.randomListColor(3)).thenReturn(mockColors);

    Flux<Color> resultFlux = workshopCreation.createFluxColorsWithList();

    assertEquals(resultFlux.collectList().block(), mockColors);
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
    List<Shape> shapes = ShapeProvider.getInfiniteRandomShapes()
      .take(10)
      .collectList()
      .block();

    System.out.println(shapes);
  }
}
