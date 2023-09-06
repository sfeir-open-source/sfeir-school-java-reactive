package com.sfeir.schools.java.reactorerrors.commons.services;

import com.sfeir.schools.java.reactorerrors.commons.domain.Shape;
import reactor.core.publisher.Flux;

import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorerrors.commons.domain.Shape.*;

public class ShapeProvider {

  public static Flux<Shape> getConstantShapes() {
    return Flux.just(CIRCLE, SQUARE, SQUARE, TRIANGLE);
  }


  public Flux<Shape> getRandomError(int sizeLimit) {
    return Flux
      .create(sink -> {
        for (int i = 0; i < sizeLimit; i++) {
          if (i == 3) { // Simuler une exception
            sink.error(new RuntimeException("Une erreur s'est produite"));
            return;
          }
          sink.next(randomShape());
        }
        sink.complete();
      });
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
}
