package com.sfeir.schools.java.reactorerrors.commons.services;

import com.sfeir.schools.java.reactorerrors.commons.domain.Color;
import com.sfeir.schools.java.reactorerrors.commons.domain.Figure;
import com.sfeir.schools.java.reactorerrors.commons.domain.Shape;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorerrors.commons.domain.Shape.*;
@Slf4j

public class ShapeProvider {

  public static Flux<Shape> getConstantShapes() {
    return Flux.just(CIRCLE, SQUARE, SQUARE, TRIANGLE);
  }

  private final AtomicBoolean hasThrown = new AtomicBoolean(false);

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

  public Flux<Shape> getFiveShapesAfterOneError() {
    log.error("test");
    return Flux.create(sink -> {
      for (int i = 0; i < 5; i++) {
        if (i == 3) { // Générer une exception au 4e élément seulement lors du premier appel
          sink.error(new RuntimeException());
          return;
        }

        // Générer une figure aléatoire
        Shape shape = randomShape();

        // Émettre la shape
        sink.next(shape);
      }
      sink.complete();
    });
  }
}
