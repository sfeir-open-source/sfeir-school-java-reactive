package com.sfeir.schools.java.reactorbasics.commons.services;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import reactor.core.publisher.Flux;

import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;

public class ColorProvider {

  public static Flux<Color> getConstantColors() {
    return Flux.just(RED, GREEN, GREEN, YELLOW);
  }


  public static Flux<Color> getRandomColors(int sizeLimit) {
    return getInfiniteRandomColors()
      .take(sizeLimit);
  }

  public static Flux<Color> getInfiniteRandomColors() {
    return Flux
      .generate(sink -> sink.next(randomColor()))
      .cast(Color.class);
  }


  private static Color randomColor() {
    int randomIndex = RandomGenerator.getDefault()
      .nextInt(Color.values().length);
    return Color.values()[randomIndex];
  }
}
