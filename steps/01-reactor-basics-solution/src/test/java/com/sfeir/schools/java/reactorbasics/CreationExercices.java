package com.sfeir.schools.java.reactorbasics;

import com.sfeir.schools.java.reactorbasics.commons.WorkshopFluxTransformations;
import com.sfeir.schools.java.reactorbasics.commons.WorkshopSubscribe;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

//TODO to rename or separate more (Subscribes & Creations ? Creation before ? then Manupilations ?)
public class CreationExercices {

  void test_filter() {

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
