package com.sfeir.schools.java.reactorbasics.commons.services;

import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import reactor.core.publisher.Flux;

import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

public class ShapeProvider {

  public static Flux<Shape> getConstantShapes() {
    return Flux.just(CIRCLE, SQUARE, SQUARE, TRIANGLE);
  }


  public static Flux<Shape> getRandomShapes(int sizeLimit) {
    return getInfiniteRandomShapes()
      .take(sizeLimit);
  }

  public static Flux<Shape> getInfiniteRandomShapes() {
    return Flux
      .generate(sink -> sink.next(randomShape()))
      .cast(Shape.class);
  }


  private static Shape randomShape() {
    int randomIndex = RandomGenerator.getDefault()
      .nextInt(Shape.values().length);
    return Shape.values()[randomIndex];
  }
}
