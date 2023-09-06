package com.sfeir.schools.java.reactorbasics.commons.services;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Shape.*;

public class ShapeProvider {

  public static Flux<Shape> getConstantShapes() {
    return Flux.just(CIRCLE, SQUARE, SQUARE, TRIANGLE);
  }

  public Flux<Shape> getRandomShapes(int sizeLimit) {
    return getInfiniteRandomShapes()
      .take(sizeLimit);
  }

  public Flux<Shape> getInfiniteRandomShapes() {
    return Flux
      .generate(sink -> sink.next(randomShape()))
      .cast(Shape.class);
  }

  public Shape randomShape() {
    int randomIndex = RandomGenerator.getDefault()
      .nextInt(Shape.values().length);
    return Shape.values()[randomIndex];
  }

  public List<Shape> getAllColors() {
    return Arrays.asList(Shape.values());
  }
}
